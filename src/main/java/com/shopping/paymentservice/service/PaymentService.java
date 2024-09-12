package com.shopping.paymentservice.service;

import com.shopping.exception.GeneralResponse;
import com.shopping.orderservice.entity.OrderEntity;
import com.shopping.orderservice.repository.OrderRepository;
import com.shopping.paymentservice.dto.PaymentDto;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final OrderRepository orderRepository;

    // Mensajes de error como constantes
    private static final String ORDER_NOT_FOUND = "Order does not exist";
    private static final String PAYMENT_AMOUNT_MISMATCH = "Payment amount does not match the order total";

    // Constructor para inyección de dependencias
    public PaymentService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

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
                .orElseThrow(() -> new IllegalArgumentException(ORDER_NOT_FOUND));

        // Validate that the payment amount matches the order total
        if (!paymentDto.getAmount().equals(order.getTotalAmount())) {
            throw new IllegalArgumentException(PAYMENT_AMOUNT_MISMATCH);
        }

        // Simulate payment processing
        paymentDto.setStatus("Processed");

        // Update the status of the order in the database
        order.setStatus("Paid");
        orderRepository.save(order);

        // Create the general response
        GeneralResponse generalResponse = GeneralResponse.builder()
                .numeroLinea(0)
                .tipoMensajeRespuesta("OK")
                .codigoMensajeRespuesta("0000")
                .descripcionMensajeRespuesta("Successful transaction")
                .build();

        paymentDto.setGeneralResponse(generalResponse);

        return paymentDto;
    }
}
