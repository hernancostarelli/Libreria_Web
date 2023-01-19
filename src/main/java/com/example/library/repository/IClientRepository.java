package com.example.library.repository;

import com.example.library.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClientRepository extends JpaRepository<Client, String> {

    Client findUserByEmail(String email);

    @Query("SELECT (count(c) > 0) FROM Client c WHERE c.email = :email")
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT (count(c) > 0) FROM Client c WHERE c.document = :document")
    boolean existsByDocument(@Param("document") String document);

    @Query("SELECT c FROM Client c WHERE c.name LIKE :value OR c.surname LIKE :value ORDER BY c.surname ASC")
    List<Client> getByValue(@Param("value")String value);

    @Query("SELECT c FROM Client c WHERE c.deleted = false ORDER BY c.surname ASC")
    List<Client> getForEnable();

    @Query("SELECT c FROM Client c WHERE c.deleted = true ORDER BY c.surname ASC")
    List<Client> getForDisable();
}