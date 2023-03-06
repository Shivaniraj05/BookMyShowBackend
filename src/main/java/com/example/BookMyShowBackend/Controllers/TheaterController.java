package com.example.BookMyShowBackend.Controllers;

import com.example.BookMyShowBackend.Dtos.TheaterRequestDto;
import com.example.BookMyShowBackend.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    TheaterService theaterService;
    @PostMapping("/add")
    public String addTheater(@RequestBody TheaterRequestDto theaterRequestDto){
        return theaterService.addTheater(theaterRequestDto);
    }
    //theater by theaterid

    //get all theaters
}
