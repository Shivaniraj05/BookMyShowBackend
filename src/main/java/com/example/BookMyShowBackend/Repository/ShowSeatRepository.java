package com.example.BookMyShowBackend.Repository;

import com.example.BookMyShowBackend.Models.ShowSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowSeatRepository extends JpaRepository<ShowSeatEntity, Integer> {
}
