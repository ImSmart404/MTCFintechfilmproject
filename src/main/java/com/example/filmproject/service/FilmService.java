package com.example.filmproject.service;

import com.example.filmproject.model.Film;

import java.util.UUID;

public interface FilmService {
    Film getFilm(UUID filmUuid);
    Film saveFilm(Film film);
}
