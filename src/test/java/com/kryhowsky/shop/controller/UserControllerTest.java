package com.kryhowsky.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kryhowsky.shop.model.dao.User;
import com.kryhowsky.shop.model.dto.UserDto;
import com.kryhowsky.shop.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveUser() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserDto.builder()
                                .email("test123@email.com")
                                .firstName("Adam")
                                .lastName("Nowak")
                                .password("1234Pass")
                                .confirmPassword("1234Pass")
                                .build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.firstName").value("Adam"))
                .andExpect(jsonPath("$.lastName").value("Nowak"))
                .andExpect(jsonPath("$.email").value("test123@email.com"))
                .andExpect(jsonPath("$.confirmPassword").doesNotExist());
    }

    @Test
    void shouldDontSaveUserWhenNotValidBody() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserDto.builder()
                                .email("   ")
                                .firstName("    ")
                                .lastName("    ")
                                .password("1234Pass")
                                .confirmPassword("1234Pass")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*].field", containsInAnyOrder("email", "email", "firstName", "lastName")))
                .andExpect(jsonPath("$[*].message", containsInAnyOrder("must not be blank", "must not be blank", "must not be blank", "must be a well-formed email address")));
    }

    @Test
    void shouldDontSaveUserWhenPasswordAndConfirmPasswordAreDifferent() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserDto.builder()
                                .email("test123@email.com")
                                .firstName("Adam")
                                .lastName("Nowak")
                                .password("1234Pass")
                                .confirmPassword("1234Pa")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*].field", containsInAnyOrder("saveUser.user")))
                .andExpect(jsonPath("$[*].message", containsInAnyOrder("Password and ConfirmPassword should be the same")));
    }

    @Test
    void shouldDontSaveUserWhenEmailIsDuplicated() throws Exception {
        userRepository.save(User.builder()
                .email("test123@email.com")
                .firstName("Adam")
                .lastName("Nowak")
                .password("1234Pass")
                .build());

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserDto.builder()
                                .email("test123@email.com")
                                .firstName("Adam")
                                .lastName("Nowak")
                                .password("1234Pass")
                                .confirmPassword("1234Pass")
                                .build())))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void shouldDontSaveUserWhenPasswordIsEmpty() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserDto.builder()
                                .email("test123@email.com")
                                .firstName("Adam")
                                .lastName("Nowak")
                                .password("")
                                .confirmPassword("")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*].field", containsInAnyOrder("saveUser.user.password", "saveUser.user.password")))
                .andExpect(jsonPath("$[*].message", containsInAnyOrder("must not be blank", "length must be between 5 and 2147483647")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnPageOfUsers() throws Exception {
        userRepository.save(User.builder()
                .email("test123@email.com")
                .firstName("Adam")
                .lastName("Nowak")
                .password("1234Pass")
                .build());

        mockMvc.perform(get("/api/users")
                        .queryParam("page", "0")
                        .queryParam("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.pageable.pageSize").value(10))
                .andExpect(jsonPath("$.pageable.pageNumber").value(0))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnUserByIdWhenUserExists() throws Exception {
        User user = userRepository.save(User.builder()
                .email("test123@email.com")
                .firstName("Adam")
                .lastName("Nowak")
                .password("1234Pass")
                .build());

        mockMvc.perform(get("/api/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.email").value("test123@email.com"))
                .andExpect(jsonPath("$.firstName").value("Adam"))
                .andExpect(jsonPath("$.lastName").value("Nowak"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldNotReturnUserByIdWhenUserNotFound() throws Exception {
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void shouldNotReturnUserByIdWhenUnauthorized() throws Exception {
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());
    }

    // TODO: użytkownik jest zalogowany i próbuje pobrać innego użytkownika
    // TODO: użytkownik jest zalogowany i próbuje pobrć siebie (username element w adnotacji

    @Test
    @WithMockUser(roles = "USER")
    void shouldNotReturnPageOfUsers() throws Exception {
        userRepository.save(User.builder()
                .email("test123@email.com")
                .firstName("Adam")
                .lastName("Nowak")
                .password("1234Pass")
                .build());

        mockMvc.perform(get("/api/users")
                        .queryParam("page", "0")
                        .queryParam("size", "10"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void shouldUpdateUser() throws Exception { // TODO: zabezpieczyć, pozmieniać na wartości z user
        User user = userRepository.save(User.builder()
                .email("test123@email.com")
                .firstName("Adam")
                .lastName("Nowak")
                .password("1234Pass")
                .build());

        mockMvc.perform(put("/api/users/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserDto.builder()
                                .email("test123@email.com")
                                .firstName("Jan")
                                .lastName("Kowalski")
                                .password("Pass1234")
                                .confirmPassword("Pass1234")
                                .build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.email").value("test123@email.com"))
                .andExpect(jsonPath("$.firstName").value("Jan"))
                .andExpect(jsonPath("$.lastName").value("Kowalski"));

    }

}
