package com.spring.techpractica.UI.Rest.Controller.User.Auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.techpractica.Application.User.LoginAccount.LoginAccountUseCase;
import com.spring.techpractica.Core.User.Exception.UserAuthenticationException;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.UI.Rest.Resources.User.Authentication.Request.LoginAccountRequest;
import com.spring.techpractica.infrastructure.Jwt.JwtGeneration;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserAuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserAuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LoginAccountUseCase loginAccountUseCase;

    @Mock
    private JwtGeneration jwtGeneration;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String BASE_PATH = "http://localhost:8080";

    private static final String LOGIN_PATH = BASE_PATH + "/api/v1/auth/login";

    @Test
    void should_login_successfully_and_return_token() throws Exception {
        // Arrange
        LoginAccountRequest request = new LoginAccountRequest("email@example.com", "password");

        User user = User.builder()
                .name("Test User")
                .email("email@example.com")
                .password("hashed")
                .build();

        String token = "mocked.jwt.token";

        when(loginAccountUseCase.execute(any())).thenReturn(user);
        when(jwtGeneration.generateToken(user.getId(), user.getName())).thenReturn(token);

        // Act & Assert
        mockMvc.perform(post(LOGIN_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Login account successful"))
                .andExpect(jsonPath("$.data.token").value(token))
                .andExpect(jsonPath("$.data.name").value("Test User"))
                .andExpect(jsonPath("$.data.email").value("email@example.com"));

        verify(loginAccountUseCase).execute(any());
        verify(jwtGeneration).generateToken(user.getId(), user.getName());
    }

    @Test
    void should_return_401_when_invalid_credentials() throws Exception {
        // Arrange
        LoginAccountRequest request = new LoginAccountRequest("wrong@example.com", "wrong-password");

        when(loginAccountUseCase.execute(any()))
                .thenThrow(new UserAuthenticationException("Invalid email or password"));

        // Act & Assert
        mockMvc.perform(post("LOGIN_PATH")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid email or password"));

        verify(loginAccountUseCase).execute(any());
        verify(jwtGeneration, never()).generateToken(any(), any());
    }

    @Test
    void should_return_400_when_request_is_invalid() throws Exception {
        // Missing email and password
        String invalidJson = """
                {
                  "email": "",
                  "password": ""
                }
                """;

        // Act & Assert
        mockMvc.perform(post("LOGIN_PATH")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(loginAccountUseCase);
        verifyNoInteractions(jwtGeneration);
    }
}