package com.shopping.paymentservice.dto;


import com.shopping.exception.GeneralResponse;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PaymentDto {

    private GeneralResponse generalResponse;
    
    @NotNull(message = "Order ID cannot be null")
    @Schema(description = "The unique identifier of the order", example = "1")
    private Long orderId;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    @Schema(description = "The amount of the payment", example = "49.99")
    private Double amount;

    @NotNull(message = "Payment method cannot be null")
    @Size(min = 1, max = 20, message = "Payment method must be between 1 and 20 characters")
    @Schema(description = "The payment method used", example = "Credit Card")
    private String paymentMethod; // e.g., "Credit Card", "PayPal"

    @Schema(description = "The status of the payment", example = "Processed")
    private String status; // e.g., "Processed", "Failed"
}
