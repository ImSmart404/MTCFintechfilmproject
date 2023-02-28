package com.example.filmproject.service;

import com.example.filmproject.model.Director;

import java.util.UUID;

public interface DirectorService {
    Director getDirector(UUID directorUuid);
    Director saveDirector(Director director);
}
