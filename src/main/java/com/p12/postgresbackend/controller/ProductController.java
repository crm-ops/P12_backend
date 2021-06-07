package com.p12.postgresbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p12.postgresbackend.model.Contract;
import com.p12.postgresbackend.model.Product;
import com.p12.postgresbackend.service.IContractService;
import com.p12.postgresbackend.service.IProductService;
import com.p12.postgresbackend.service.ProductService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private IProductService ProductService;

    @GetMapping("/showProducts")
    public String showProducts(Model model) {

        var products = (List<Product>) ProductService.findAll();

        model.addAttribute("products", products);

        return "showProducts";

    }


    @GetMapping("/getProducts")
    @ResponseBody
    public List<Product> getProducts(Model model) throws JSONException {

        var Products = (List<Product>) ProductService.findAll();
        return Products;
    }












}
