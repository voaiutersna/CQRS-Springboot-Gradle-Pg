package com.example.demo.ThreeLayer.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.ThreeLayer.model.UserData;

@Repository
public interface UserRepo extends JpaRepository<UserData, UUID> {
    Optional<UserData> findByemail(String email);
    Optional<UserData> findByname(String name);
}