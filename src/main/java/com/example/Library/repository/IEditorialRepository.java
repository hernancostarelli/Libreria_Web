package com.example.Library.repository;

import com.example.Library.model.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEditorialRepository extends JpaRepository<Editorial, String> {
}