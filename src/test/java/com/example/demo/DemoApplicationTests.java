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
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

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
	void getUserByIdBadRequestTest () throws Exception {
		mockMvc.perform(get("/api/v1/users/10"))
				.andExpect(status().isBadRequest());
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
		String request = AppUtility.getContentFromResourceFile("json/postUserTest_request.json");

		MvcResult mvcResult = mockMvc.perform(post("/api/v1/users")
					.content(request)
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andReturn();

		String expectedResponse = AppUtility.getContentFromResourceFile("json/postUserTest_response.json");

		TestValidationUtility.validateResponse(mvcResult.getResponse(), expectedResponse);
	}

	@Test
	void putUsersTest() throws Exception {
		String request = AppUtility.getContentFromResourceFile("json/putUsersTest.json");

		MvcResult mvcResult = mockMvc.perform(put("/api/v1/users")
						.content(request)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		TestValidationUtility.validateResponse(mvcResult.getResponse(), request);
	}

	@Test
	void putUsersBadRequestTest () throws Exception {
		User user = new User().builder()
				.id("10")
				.name("Ivan")
				.email("ivan@mail.ru")
				.build();
		MvcResult mvcResult = mockMvc.perform(put("/api/v1/users")
				.content(objectMapper.writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
				.andReturn();

		Assertions.assertThrows(UserNotFoundException.class, () -> userService.updateUsers(Arrays.asList(user)));
	}

}
