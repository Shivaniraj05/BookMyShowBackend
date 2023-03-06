package com.example.BookMyShowBackend.Services;

import com.example.BookMyShowBackend.Dtos.MovieRequestDto;
import com.example.BookMyShowBackend.Models.MovieEntity;
import com.example.BookMyShowBackend.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public String addMovie(MovieRequestDto movieRequestDto){
        MovieEntity movieEntity = MovieEntity.builder().movieName(movieRequestDto.getMovieName()).releaseDate(movieRequestDto.getReleaseDate()).duration(movieRequestDto.getDuration()).build();
        movieRepository.save(movieEntity);
        return "Movie added successfully";
    }
}
