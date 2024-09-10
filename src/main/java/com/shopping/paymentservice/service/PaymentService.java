/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.paymentservice.service;

import com.shopping.orderservice.repository.OrderRepository;
import com.shopping.paymentservice.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private OrderRepository orderRepository;

    public PaymentDto processPayment(PaymentDto paymentDto) {
        if (!orderRepository.existsById(paymentDto.getOrderId())) {
            throw new IllegalArgumentException("Order does not exist");
        }

        // Simula el procesamiento del pago
        paymentDto.setStatus("Processed");
        return paymentDto;
    }
}
