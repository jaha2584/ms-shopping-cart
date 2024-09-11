package com.shopping.orderservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * DTO representing an order with customer and item details.
 */
@Data
public class OrderDto {

    @ApiModelProperty(value = "Unique order ID", example = "1", hidden = true)
    private Long orderId;

    private ClientDto customer;

    private List<OrderItemDto> items;

    @ApiModelProperty(value = "Total amount for the order", example = "150.75", hidden = true)
    private Double totalAmount;

    @ApiModelProperty(value = "Status of the order (e.g., PENDING, COMPLETED)", example = "PENDING", hidden = true)
    private String status;
}
