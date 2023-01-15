package com.example.library.repository;

import com.example.library.model.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEditorialRepository extends JpaRepository<Editorial, String> {

    @Query("SELECT e FROM Editorial e WHERE e.name LIKE :value ORDER BY e.name ASC")
    List<Editorial> getByValue(@Param("value")String value);

    @Query("SELECT e FROM Editorial e WHERE e.deleted = false ORDER BY e.name ASC")
    List<Editorial> getForEnable();

    @Query("SELECT e FROM Editorial e WHERE e.deleted = true ORDER BY e.name ASC")
    List<Editorial> getForDisable();
}