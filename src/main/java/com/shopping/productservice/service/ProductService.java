/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.productservice.service;

import com.shopping.productservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    private static final String PRODUCTS_URL = "https://fakestoreapi.com/products";
    private static final String PRODUCT_BY_ID_URL = "https://fakestoreapi.com/products/{id}";

    @Autowired
    private RestTemplate restTemplate;

    public ProductDto[] getAllProducts() {
        return restTemplate.getForObject(PRODUCTS_URL, ProductDto[].class);
    }

    public ProductDto getProductById(int id) {
        return restTemplate.getForObject(PRODUCT_BY_ID_URL, ProductDto.class, id);
    }
}
