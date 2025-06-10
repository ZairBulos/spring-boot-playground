package com.zair.infrastructure.adapter.in.rest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class UserRestAdapterIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void getAll_shouldReturnList() throws Exception {
        String userRequest1 = "{\"name\": \"John Doe\", \"email\": \"j.doe@example.com\"}";
        String userRequest2 = "{\"name\": \"Jane Doe\", \"email\": \"jane.doe@example.com\"}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRequest1)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRequest2)
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    @Order(2)
    void createAndGet_shouldReturnSuccess() throws Exception {
        String userRequest = "{\"name\": \"Bob Doe\", \"email\": \"bob.doe@example.com\"}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRequest)
                ).andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bob Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("bob.doe@example.com"));
    }

    @Test
    @Order(3)
    void getById_whenNotExists_shouldReturn404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Order(4)
    void create_whenEmailExists_shouldReturn409() throws Exception {
        String userRequest = "{\"name\": \"Alice\", \"email\": \"alice@example.com\"}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRequest)
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRequest)
        ).andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    @Order(5)
    void update_shouldModifyUser() throws Exception {
        String userRequest = "{\"name\": \"Carl\", \"email\": \"carl@example.com\"}";
        String updatedRequest = "{\"name\": \"Carl Updated\", \"email\": \"carl.updated@example.com\"}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRequest)
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/users/5")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updatedRequest)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Carl Updated"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("carl.updated@example.com"));
    }

    @Test
    @Order(6)
    void delete_shouldRemoveUser() throws Exception {
        String userRequest = "{\"name\": \"Delete Me\", \"email\": \"deleteme@example.com\"}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRequest)
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/6"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/6"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Order(7)
    void create_whenInvalidRequest_shouldReturn400() throws Exception {
        String invalidRequest = "{\"name\": \"\", \"email\": \"not-an-email\"}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequest)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
