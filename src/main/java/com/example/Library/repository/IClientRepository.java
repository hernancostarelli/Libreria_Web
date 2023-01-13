package com.example.Library.repository;

import com.example.Library.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, String> {
}