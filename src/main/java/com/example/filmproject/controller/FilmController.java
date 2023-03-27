package com.example.filmproject.controller;

import com.example.filmproject.model.Film;
import com.example.filmproject.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/film")
public class FilmController {

    private final FilmService filmService;
    @PostMapping
    public ResponseEntity<?> saveFilm(@RequestBody Film film){
        Film resultFilm = filmService.saveFilm(film);
        return ResponseEntity.ok(resultFilm);
    }

    @GetMapping
    public ResponseEntity<?> getFilm(@RequestParam("filmUuid")UUID filmUuid){
        Film film = filmService.getFilm(filmUuid);
        return ResponseEntity.ok(film);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getFilms(){
        List<Film> films = filmService.getFilms();
        return ResponseEntity.ok(films);
    }
}