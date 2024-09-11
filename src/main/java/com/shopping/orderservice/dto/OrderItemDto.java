/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.orderservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * DTO representing an item within an order.
 */
@Data
public class OrderItemDto {

    @ApiModelProperty(value = "Product ID of the item", example = "101")
    private Long productId;

    @ApiModelProperty(value = "Name of the product", example = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")
    private String productName;

    @ApiModelProperty(value = "Quantity of the product ordered", example = "2")
    private Integer quantity;

   @ApiModelProperty(value = "Price per unit of the product", example = "109.95")
   private Double price;
}
