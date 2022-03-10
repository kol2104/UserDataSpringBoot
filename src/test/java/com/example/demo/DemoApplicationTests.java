package com.example.demo;

import com.example.demo.util.AppUtility;
import com.example.demo.util.TestValidationUtility;
import com.example.domain.User;
import com.example.domain.UserWish;
import com.example.exception.UserNotFoundException;
import com.example.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DemoApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UserService userService;

	@Test
	void getUserByIdTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/users/1"))
				.andExpect(status().isOk())
				.andReturn();

		String expectedResponse = AppUtility.getContentFromResourceFile("json/getUserByIdTest_response.json");

		TestValidationUtility.validateResponse(mvcResult.getResponse(), expectedResponse);
	}

	@Test
	void getUsersTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/users"))
				.andExpect(status().isOk())
				.andReturn();

		String expectedResponse = AppUtility.getContentFromResourceFile("json/getUsersTest_response.json");

		TestValidationUtility.validateResponse(mvcResult.getResponse(), expectedResponse);
	}

	@Test
	void postUserTest() throws Exception {
		User user = new User().builder()
				.name("Ivan")
				.email("ivan@mail.ru")
				.userWishes(new HashSet<>(Arrays.asList(
						new UserWish().builder().userId("1").priceSettingsId("1").item2FindId("1").values("100;120").build(),
						new UserWish().builder().userId("1").priceSettingsId("4").item2FindId("5").values("500").build()
				)))
				.build();
		MvcResult mvcResult = mockMvc.perform(post("/api/v1/users")
					.content(objectMapper.writeValueAsString(user))
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andReturn();

		String expectedResponse = AppUtility.getContentFromResourceFile("json/postUserTest_response.json");

		TestValidationUtility.validateResponse(mvcResult.getResponse(), expectedResponse);
	}

	@Test
	void putUsersTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(put("/api/v1/users")
						.content(AppUtility.getContentFromResourceFile("json/putUsersTest.json"))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String expectedResponse = AppUtility.getContentFromResourceFile("json/putUsersTest.json");

		TestValidationUtility.validateResponse(mvcResult.getResponse(), expectedResponse);
	}

	@Test
	void putUsersBadRequestTest () throws Exception {
		User user = new User().builder()
				.id("10")
				.name("Ivan")
				.email("ivan@mail.ru")
				.build();
		mockMvc.perform(put("/api/v1/users")
				.content(objectMapper.writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());

		Assertions.assertThrows(UserNotFoundException.class, () -> userService.updateUsers(Arrays.asList(user)));
	}

}
