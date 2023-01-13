package com.example.Library.repository;

import com.example.Library.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, String> {
}