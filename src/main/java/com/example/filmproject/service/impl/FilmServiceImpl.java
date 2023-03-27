package com.example.filmproject.service.impl;

import com.example.filmproject.model.Director;
import com.example.filmproject.model.Film;
import com.example.filmproject.repository.FilmRepository;
import com.example.filmproject.service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    @Override
    @SneakyThrows(ChangeSetPersister.NotFoundException.class)
    public Film getFilm(UUID filmUuid) {
        Optional<Film> film = filmRepository.findById(filmUuid);
        return film.orElseThrow(()->new ChangeSetPersister.NotFoundException());
    }

    @Override
    public List<Film> getFilms(){
        List<Film> films = filmRepository.findAll();
        return films;
    }

    @Override
    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }
}
