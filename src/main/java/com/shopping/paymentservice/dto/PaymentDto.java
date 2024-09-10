/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.paymentservice.dto;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class PaymentDto {

    @Schema(description = "The unique identifier of the order", example = "1")
    private Long orderId;

    @Schema(description = "The amount of the payment", example = "49.99")
    private Double amount;

    @Schema(description = "The payment method used", example = "Credit Card")
    private String paymentMethod; // e.g., "Credit Card", "PayPal"

    @Schema(description = "The status of the payment", example = "Processed")
    private String status; // e.g., "Processed", "Failed"
}
