package com.example.BookMyShowBackend.Services;

import com.example.BookMyShowBackend.Dtos.BookTicketRequestDto;
import com.example.BookMyShowBackend.Models.ShowEntity;
import com.example.BookMyShowBackend.Models.ShowSeatEntity;
import com.example.BookMyShowBackend.Models.TicketEntity;
import com.example.BookMyShowBackend.Models.UserEntity;
import com.example.BookMyShowBackend.Repository.ShowRepository;
import com.example.BookMyShowBackend.Repository.TicketRepository;
import com.example.BookMyShowBackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TicketRepository ticketRepository;
    public String bookTicket(BookTicketRequestDto bookTicketRequestDto)  throws Exception{
        List<String> requestedSeats = bookTicketRequestDto.getRequestSeats();
        ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowId()).get();
        UserEntity userEntity = userRepository.findById(bookTicketRequestDto.getUserId()).get();

        List<ShowSeatEntity> showSeats = showEntity.getListOfSeats();
        List<ShowSeatEntity> bookedSeats = new ArrayList<>();
        for(ShowSeatEntity showSeat: showSeats){
            String seatNo = showSeat.getSeatNo();

            if(showSeat.isBooked()==false && requestedSeats.contains(seatNo)){
                bookedSeats.add(showSeat);
            }
        }

        //FAILURE
        if(bookedSeats.size()!=requestedSeats.size()){
            throw new Exception("Requested seats are not available");
        }

        //SUCCESS
        TicketEntity ticketEntity = new TicketEntity();

        double totalAmount =0;
        double multiplier = showEntity.getMultiplier();
        int rate=0;

        String allotedSeats = "";

        for(ShowSeatEntity bookSeat: bookedSeats){
            bookSeat.setBooked(true);
            bookSeat.setBookedAt(new Date());
            bookSeat.setTicket(ticketEntity);
            bookSeat.setShow(showEntity);

            String seatNo = bookSeat.getSeatNo();
            allotedSeats = allotedSeats+seatNo+",";

            if(seatNo.charAt(0)=='1'){
                rate =100;
            }
            else{
                rate = 200;
            }
            totalAmount = totalAmount + multiplier*rate;
        }
        ticketEntity.setBooked_at(new Date());
        ticketEntity.setAmount((int) totalAmount);
        ticketEntity.setShow(showEntity);
        ticketEntity.setUser(userEntity);
        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAllotedSeats(allotedSeats);

        ticketRepository.save(ticketEntity);
        return "Successfully created a ticket";

    }
}
