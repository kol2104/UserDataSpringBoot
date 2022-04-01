package com.example.demo;

import com.example.demo.util.AppUtility;
import com.example.demo.util.TestValidationUtility;
import com.example.domain.User;
import com.example.domain.UserWish;
import com.example.persistence.UserRepository;
import com.example.persistence.UserWishesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserDataMockitoTests {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserWishesRepository userWishesRepository;

    @BeforeEach
    void initialize () throws Exception {
        Mockito.when(userRepository.findAll()).thenReturn(buildUserList());

        Mockito.when(userRepository.findById("1")).thenReturn(Optional.of(buildUserById("1")));
        Mockito.when(userRepository.findById("2")).thenReturn(Optional.of(buildUserById("2")));

        Mockito.when(userWishesRepository.saveAll(Mockito.anyCollection()))
                .thenReturn(buildUserWishes(2));
        Mockito.when(userRepository.save(Mockito.argThat(arg -> arg.getId() == null))).thenReturn(buildUserById("1"));

    }

    @Test
    public void getUsersMockitoTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andReturn();

        String expectedResponse = AppUtility.getContentFromResourceFile("json/mockito/getUsersMockitoTest_response.json");

        TestValidationUtility.validateResponse(mvcResult.getResponse(), expectedResponse);
    }

    @Test
    public void getUserByIdMockitoTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andReturn();

        String expectedResponse = AppUtility.getContentFromResourceFile("json/mockito/getUserByIdMockitoTest_response.json");

        TestValidationUtility.validateResponse(mvcResult.getResponse(), expectedResponse);
    }

    @Test
    public void getUserByIdBadRequestMockitoTest() throws Exception {
        mockMvc.perform(get("/api/v1/users/10"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postUserMockitoTest() throws Exception {
        String request = AppUtility.getContentFromResourceFile("json/mockito/postUserMockitoTest_request.json");

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/users")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String expectedResponse = AppUtility.getContentFromResourceFile("json/mockito/postUserMockitoTest_response.json");

        TestValidationUtility.validateResponse(mvcResult.getResponse(), expectedResponse);
    }

    private List<User> buildUserList() {
        List<User> userList = new ArrayList<>(Arrays.asList(
                buildUserById("1"),
                buildUserById("2")
        ));
        return userList;
    }

    private User buildUserById(String id) {
        return User.builder()
                .id(id)
                .name("User" + id)
                .email("user" + id + "@mail.ru")
                .userWishes(new HashSet<>(Arrays.asList(
                        new UserWish().builder().id("1").userId("1").priceSettingsId("1").item2FindId("1").values("100;120").build(),
                        new UserWish().builder().id("2").userId("1").priceSettingsId("4").item2FindId("5").values("500").build()
                ))).build();
    }

    private Iterable<UserWish> buildUserWishes(int countElements) {
        List<UserWish> result = new ArrayList<>();
        for (int i = 1; i <= countElements; i++) {
            result.add(buildUserWish(Integer.toString(i)));
        }
        return result;
    }

    private UserWish buildUserWish (String id) {
        return UserWish.builder()
                .id(id)
                .userId("1")
                .priceSettingsId(id)
                .item2FindId(id)
                .values(id + ";100").build();
    }
}
