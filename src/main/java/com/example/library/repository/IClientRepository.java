package com.example.library.repository;

import com.example.library.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, String> {
}