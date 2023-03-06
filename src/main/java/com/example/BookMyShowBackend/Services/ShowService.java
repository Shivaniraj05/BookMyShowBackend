package com.example.BookMyShowBackend.Services;

import com.example.BookMyShowBackend.Dtos.ShowRequestDto;
import com.example.BookMyShowBackend.Models.*;
import com.example.BookMyShowBackend.Repository.MovieRepository;
import com.example.BookMyShowBackend.Repository.ShowRepository;
import com.example.BookMyShowBackend.Repository.ShowSeatRepository;
import com.example.BookMyShowBackend.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ShowSeatRepository showSeatRepository;
    public String addShow(ShowRequestDto showRequestDto){

        ShowEntity showEntity = ShowEntity.builder().showDate(showRequestDto.getShowDate()).showTime(showRequestDto.getShowTime()).multiplier(showRequestDto.getMultiplier()).build();

        MovieEntity movieEntity = movieRepository.findByMovieName(showRequestDto.getMovieName());

        TheaterEntity theaterEntity = theaterRepository.findById(showRequestDto.getTheaterId()).get();

        showEntity.setTheater(theaterEntity);
        showEntity.setMovie(movieEntity);

        movieEntity.getListOfShows().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);

        List<ShowSeatEntity> seatEntityList = createShowSeats(theaterEntity.getTheaterSeatEntityList());
        showEntity.setListOfSeats(seatEntityList);

        for(ShowSeatEntity showSeat: seatEntityList){
            showSeat.setShow(showEntity);
        }
        movieRepository.save(movieEntity);
        theaterRepository.save(theaterEntity);
       // showRepository.save(showEntity);

        return "Show added successfully";
    }
    public List<ShowSeatEntity> createShowSeats(List<TheaterSeatEntity> theaterSeatEntityList){
        List<ShowSeatEntity> seats = new ArrayList<>();
        for(TheaterSeatEntity theaterSeat: theaterSeatEntityList){
            ShowSeatEntity showSeat = ShowSeatEntity.builder().seatNo(theaterSeat.getSeatNo()).seatType(theaterSeat.getSeatType()).build();
            seats.add(showSeat);
        }
        showSeatRepository.saveAll(seats);
        return seats;
    }
}
