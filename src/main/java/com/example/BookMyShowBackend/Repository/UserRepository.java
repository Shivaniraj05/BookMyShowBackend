package com.example.BookMyShowBackend.Repository;

import com.example.BookMyShowBackend.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
