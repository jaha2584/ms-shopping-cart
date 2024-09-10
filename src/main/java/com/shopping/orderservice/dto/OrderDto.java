/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.orderservice.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * DTO representing an order with customer and item details.
 */
@Data
public class OrderDto {

    @ApiModelProperty(value = "Unique order ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "Name of the customer who placed the order", example = "John Doe")
    private String customerName;

    @ApiModelProperty(value = "Email of the customer", example = "john.doe@example.com")
    private String customerEmail;

    @ApiModelProperty(value = "List of items in the order")
    private List<OrderItemDto> items;

    @ApiModelProperty(value = "Total amount for the order", example = "150.75")
    private Double totalAmount;

    @ApiModelProperty(value = "Status of the order (e.g., PENDING, COMPLETED)", example = "PENDING")
    private String status;
}


