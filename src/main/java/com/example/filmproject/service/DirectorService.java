package com.example.filmproject.service;

import com.example.filmproject.model.Director;

import java.util.List;
import java.util.UUID;

public interface DirectorService {
    Director getDirector(UUID directorUuid);
    List<Director> getDirectors();
    Director saveDirector(Director director);
}
