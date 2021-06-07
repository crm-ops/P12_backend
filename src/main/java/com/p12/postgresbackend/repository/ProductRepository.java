package com.p12.postgresbackend.repository;


import com.p12.postgresbackend.model.Contract;
import com.p12.postgresbackend.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {





}







