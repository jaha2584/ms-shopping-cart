/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.orderservice.service;

import com.shopping.orderservice.dto.OrderDto;
import com.shopping.orderservice.dto.OrderItemDto;
import com.shopping.orderservice.entity.OrderEntity;
import com.shopping.orderservice.entity.OrderItemEntity;
import com.shopping.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDto createOrder(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerName(orderDto.getCustomerName());
        orderEntity.setCustomerEmail(orderDto.getCustomerEmail());
        orderEntity.setItems(orderDto.getItems().stream().map(this::mapToEntity).collect(Collectors.toList()));
        orderEntity.setTotalAmount(orderDto.getTotalAmount());
        orderEntity.setStatus("PENDING");

        orderEntity = orderRepository.save(orderEntity);
        return mapToDto(orderEntity);
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public OrderDto getOrderById(Long id) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return mapToDto(order);
    }

    private OrderDto mapToDto(OrderEntity orderEntity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setCustomerName(orderEntity.getCustomerName());
        orderDto.setCustomerEmail(orderEntity.getCustomerEmail());
        orderDto.setItems(orderEntity.getItems().stream().map(this::mapToDto).collect(Collectors.toList()));
        orderDto.setTotalAmount(orderEntity.getTotalAmount());
        orderDto.setStatus(orderEntity.getStatus());
        return orderDto;
    }

    private OrderItemDto mapToDto(OrderItemEntity itemEntity) {
        OrderItemDto itemDto = new OrderItemDto();
        itemDto.setProductId(itemEntity.getProductId());
        itemDto.setProductName(itemEntity.getProductName());
        itemDto.setQuantity(itemEntity.getQuantity());
        itemDto.setPrice(itemEntity.getPrice());
        return itemDto;
    }

    private OrderItemEntity mapToEntity(OrderItemDto itemDto) {
        OrderItemEntity itemEntity = new OrderItemEntity();
        itemEntity.setProductId(itemDto.getProductId());
        itemEntity.setProductName(itemDto.getProductName());
        itemEntity.setQuantity(itemDto.getQuantity());
        itemEntity.setPrice(itemDto.getPrice());
        return itemEntity;
    }
}
