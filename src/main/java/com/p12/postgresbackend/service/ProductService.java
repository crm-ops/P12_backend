package com.p12.postgresbackend.service;


import com.p12.postgresbackend.model.Contract;
import com.p12.postgresbackend.model.Product;
import com.p12.postgresbackend.repository.ContractRepository;
import com.p12.postgresbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {

        var Products = (List<Product>) repository.findAll();

        return Products;
    }



}