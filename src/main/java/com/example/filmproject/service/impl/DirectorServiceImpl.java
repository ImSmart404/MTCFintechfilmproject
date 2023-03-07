package com.example.filmproject.service.impl;

import com.example.filmproject.model.Director;
import com.example.filmproject.repository.DirectorRepository;
import com.example.filmproject.service.DirectorService;
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
public class DirectorServiceImpl implements DirectorService {


   private final DirectorRepository directorRepository;
    @Override
    @SneakyThrows(ChangeSetPersister.NotFoundException.class)
    public Director getDirector(UUID directorUuid)  {
        Optional<Director> directorOptional = directorRepository.findById(directorUuid);
        return directorOptional.orElseThrow(()->new ChangeSetPersister.NotFoundException());
    }

    @Override
    public List<Director> getDirectors() {
        List<Director> directors = directorRepository.findAll();
        return directors;
    }

    @Override
    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }
}
