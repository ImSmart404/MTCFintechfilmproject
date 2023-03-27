package com.example.filmproject;
import com.example.filmproject.constants.GenreEnum;
import com.example.filmproject.controller.FilmController;
import com.example.filmproject.model.Film;
import com.example.filmproject.service.FilmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = FilmprojectApplication.class)
public class FilmControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    @Test
    public void testSaveFilm() throws Exception {
        Film film = new Film();
        film.setUuid(UUID.randomUUID());
        film.setTitle("Rambo");
        film.setGenre(GenreEnum.ACTION);
        film.setRating(10);
        when(filmService.saveFilm(any(Film.class))).thenReturn(film);
        mockMvc.perform(post("/film")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Rambo\", \"genre\": \"ACTION\", \"rating\": 10}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", equalTo("Rambo")))
                .andExpect(jsonPath("$.genre", equalTo("ACTION")))
                .andExpect(jsonPath("$.rating", equalTo(10)));
    }


    @Test
    public void testGetFilm() throws Exception {
        UUID uuid = UUID.randomUUID();
        Film film = new Film();
        film.setUuid(uuid);
        film.setTitle("Rambo");
        film.setGenre(GenreEnum.ACTION);
        film.setRating(10);
        when(filmService.getFilm(uuid)).thenReturn(film);
        mockMvc.perform(get("/film")
                        .param("filmUuid", uuid.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", equalTo("Rambo")))
                .andExpect(jsonPath("$.genre", equalTo("ACTION")))
                .andExpect(jsonPath("$.rating", equalTo(10)));
    }
}