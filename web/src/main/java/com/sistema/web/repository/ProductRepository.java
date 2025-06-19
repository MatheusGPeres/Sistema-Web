package com.sistema.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.web.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}


