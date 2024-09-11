package com.shopping.paymentservice.service;

import com.shopping.orderservice.entity.OrderEntity;
import com.shopping.orderservice.repository.OrderRepository;
import com.shopping.paymentservice.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Processes a payment and updates the status of the associated order.
     * 
     * @param paymentDto The details of the payment to process.
     * @return The updated paymentDto with the status set to "Processed".
     * @throws IllegalArgumentException if the order does not exist or the payment amount does not match the order total.
     */
    public PaymentDto processPayment(PaymentDto paymentDto) {
        // Fetch the order from the repository
        OrderEntity order = orderRepository.findById(paymentDto.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Order does not exist"));

        // Validate that the payment amount matches the order total
        if (!paymentDto.getAmount().equals(order.getTotalAmount())) {
            throw new IllegalArgumentException("Payment amount does not match the order total");
        }

        // Simulate payment processing
        paymentDto.setStatus("Processed");

        // Update the status of the order in the database
        order.setStatus("Paid"); // Change the status of the order to "Paid" or another appropriate status
        orderRepository.save(order);

        return paymentDto;
    }
}
