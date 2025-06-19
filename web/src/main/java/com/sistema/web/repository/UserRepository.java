package com.sistema.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.web.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
   User findByUsername(String nome);
}

