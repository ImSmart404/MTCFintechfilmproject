package com.example.filmproject;
import com.example.filmproject.controller.DirectorController;
import com.example.filmproject.model.Director;
import com.example.filmproject.service.DirectorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DirectorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DirectorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DirectorService directorService;

    @Test
    @WithMockUser(username="Mikhail",password = "Mokl54")
    public void testSaveDirector() throws Exception {
        Director director = new Director();
        director.setUuid(UUID.randomUUID());
        director.setName("Mikhail");
        director.setAge(21);
        director.setCountry("Russia");
        when(directorService.saveDirector(any(Director.class))).thenReturn(director);
        mockMvc.perform(post("/director")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Mikhail\", \"age\": 21, \"country\": \"Russia\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo("Mikhail")))
                .andExpect(jsonPath("$.age", equalTo(21)))
                .andExpect(jsonPath("$.country", equalTo("Russia")));
    }
    @Test
    @WithMockUser(username="Mikhail",password = "Mokl54")
    public void testGetDirector() throws Exception {
        UUID uuid = UUID.randomUUID();
        Director director = new Director();
        director.setUuid(uuid);
        director.setName("Mikhail");
        director.setAge(21);
        director.setCountry("Russia");
        when(directorService.getDirector(uuid)).thenReturn(director);
        mockMvc.perform(get("/director")
                        .param("directorUuid", uuid.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo("Mikhail")))
                .andExpect(jsonPath("$.age", equalTo(21)))
                .andExpect(jsonPath("$.country", equalTo("Russia")));
    }

}