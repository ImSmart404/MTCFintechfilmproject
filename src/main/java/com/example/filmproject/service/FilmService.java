package com.example.filmproject.service;

import com.example.filmproject.model.Film;

import java.util.List;
import java.util.UUID;

public interface FilmService {
    Film getFilm(UUID filmUuid);

    List<Film> getFilms();
    Film saveFilm(Film film);
}
