package com.example.Library.repository;

import com.example.Library.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<String, Author> {
}