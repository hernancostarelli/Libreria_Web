package com.example.Library.repository;

import com.example.Library.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<String, Client> {
}