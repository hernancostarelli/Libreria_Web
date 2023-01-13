package com.example.Library.repository;

import com.example.Library.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAuthorRepository extends JpaRepository<Author, String> {
    
    @Query("SELECT a FROM Author a WHERE a.name LIKE :value OR a.surname LIKE :value " +
            "ORDER BY a.surname ASC")
    List<Author> getByValue(@Param("value")String value);

    @Query("SELECT a FROM Author a WHERE a.deleted = false ORDER BY a.surname ASC")
    List<Author> getForEnable();

    @Query("SELECT a FROM Author a WHERE a.deleted = true ORDER BY a.surname ASC")
    List<Author> getForDisable();
}