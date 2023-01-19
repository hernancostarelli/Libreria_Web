package com.example.library.repository;

import com.example.library.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, String> {

    boolean existsByEmail(String email);

    Admin findUserByEmail(String email);
}