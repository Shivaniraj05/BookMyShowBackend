package com.example.BookMyShowBackend.Services;

import com.example.BookMyShowBackend.Dtos.TheaterRequestDto;
import com.example.BookMyShowBackend.Enums.SeatType;
import com.example.BookMyShowBackend.Models.TheaterEntity;
import com.example.BookMyShowBackend.Models.TheaterSeatEntity;
import com.example.BookMyShowBackend.Repository.TheaterRepository;
import com.example.BookMyShowBackend.Repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterSeatRepository theaterSeatRepository;
    @Autowired
    TheaterRepository theaterRepository;
    public String addTheater(TheaterRequestDto theaterRequestDto){
        TheaterEntity theater= TheaterEntity.builder().name(theaterRequestDto.getName()).city(theaterRequestDto.getCity()).address(theaterRequestDto.getAddress()).build();
        List<TheaterSeatEntity> theaterSeats = createTheaterSeats();
        theater.setTheaterSeatEntityList(theaterSeats);

        for(TheaterSeatEntity theaterSeat: theaterSeats){
            theaterSeat.setTheater(theater);
        }
        theaterRepository.save(theater);
        return "Theater added successfully";
    }
    private List<TheaterSeatEntity> createTheaterSeats(){
        List<TheaterSeatEntity> seats = new ArrayList<>();

        for(int i=0;i<5;i++){
            char ch=(char) ('A'+i);
            String seatNo = "1"+ch;
            TheaterSeatEntity theaterSeat = new TheaterSeatEntity(seatNo, SeatType.CLASSIC, 100);
            seats.add(theaterSeat);
        }
//        TheaterSeatEntity theaterSeat1 = new TheaterSeatEntity("1A", SeatType.CLASSIC, 100);
//        TheaterSeatEntity theaterSeat2 = new TheaterSeatEntity("1B", SeatType.CLASSIC, 100);
//        TheaterSeatEntity theaterSeat3 = new TheaterSeatEntity("1C", SeatType.CLASSIC, 100);
//        TheaterSeatEntity theaterSeat4 = new TheaterSeatEntity("1D", SeatType.CLASSIC, 100);
//        TheaterSeatEntity theaterSeat5 = new TheaterSeatEntity("1E", SeatType.CLASSIC, 100);
//        TheaterSeatEntity theaterSeat6 = new TheaterSeatEntity("2A", SeatType.PLATINUM, 200);
//        TheaterSeatEntity theaterSeat7 = new TheaterSeatEntity("2B", SeatType.PLATINUM, 200);
//        TheaterSeatEntity theaterSeat8 = new TheaterSeatEntity("2C", SeatType.PLATINUM, 200);
//        TheaterSeatEntity theaterSeat9 = new TheaterSeatEntity("2D", SeatType.PLATINUM, 200);
//        TheaterSeatEntity theaterSeat10 = new TheaterSeatEntity("2E", SeatType.PLATINUM, 200);

        for(int i=0;i<5;i++){
            char ch=(char) ('A'+i);
            String seatNo = "2"+ch;
            TheaterSeatEntity theaterSeat = new TheaterSeatEntity(seatNo, SeatType.PLATINUM, 200);
            seats.add(theaterSeat);
        }

//        seats.add(theaterSeat1);
//        seats.add(theaterSeat2);
//        seats.add(theaterSeat3);
//        seats.add(theaterSeat4);
//        seats.add(theaterSeat5);
//        seats.add(theaterSeat6);
//        seats.add(theaterSeat7);
//        seats.add(theaterSeat8);
//        seats.add(theaterSeat9);
//        seats.add(theaterSeat10);

        theaterSeatRepository.saveAll(seats);
        return  seats;
    }
}
