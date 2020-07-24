package com.siva.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siva.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
