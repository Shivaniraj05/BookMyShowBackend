package com.example.BookMyShowBackend.Repository;


import com.example.BookMyShowBackend.Models.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {
}
