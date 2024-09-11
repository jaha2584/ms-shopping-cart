/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.client;

import com.shopping.config.FeignConfig;
import com.shopping.productservice.dto.ProductDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "products", url = "https://fakestoreapi.com", configuration = FeignConfig.class)
public interface ProductClient {
    @GetMapping("/products")
    List<ProductDto> getAllProducts();
}
