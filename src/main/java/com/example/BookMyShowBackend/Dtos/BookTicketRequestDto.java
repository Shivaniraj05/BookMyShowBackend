package com.example.BookMyShowBackend.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class BookTicketRequestDto {
    private List<String> requestSeats;
    private int userId;
    private int showId;

}
