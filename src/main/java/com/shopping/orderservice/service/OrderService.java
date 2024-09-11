package com.shopping.orderservice.service;

import com.shopping.orderservice.dto.ClientDto;
import com.shopping.orderservice.dto.OrderDto;
import com.shopping.orderservice.dto.OrderItemDto;
import com.shopping.orderservice.entity.ClientEntity;
import com.shopping.orderservice.entity.OrderEntity;
import com.shopping.orderservice.entity.OrderItemEntity;
import com.shopping.orderservice.repository.ClientRepository;
import com.shopping.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for handling order-related operations.
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * Creates a new order and calculates the total amount based on order items.
     * 
     * @param orderDto The DTO containing order details.
     * @return The created OrderDto.
     */
    public OrderDto createOrder(OrderDto orderDto) {
        ClientEntity clientEntity = findOrCreateClient(orderDto.getCustomer());

        List<OrderItemEntity> items = orderDto.getItems().stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());

        double totalAmount = calculateTotalAmount(items);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomer(clientEntity);
        orderEntity.setItems(items);
        orderEntity.setTotalAmount(totalAmount);
        orderEntity.setStatus("PENDING");

        orderEntity = orderRepository.save(orderEntity);
        return mapToDto(orderEntity);
    }

    /**
     * Retrieves all orders and maps them to OrderDto.
     * 
     * @return List of OrderDto.
     */
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an order by its ID and maps it to OrderDto.
     * 
     * @param id The ID of the order.
     * @return The OrderDto.
     * @throws IllegalArgumentException if the order is not found.
     */
    public OrderDto getOrderById(Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return mapToDto(order);
    }

    private ClientEntity findOrCreateClient(ClientDto clientDto) {
        return clientRepository.findById(clientDto.getCustomerId())
                .orElseGet(() -> saveNewClient(clientDto));
    }

    private ClientEntity saveNewClient(ClientDto clientDto) {
        ClientEntity clientEntity = new ClientEntity();
        BeanUtils.copyProperties(clientDto, clientEntity);
        return clientRepository.save(clientEntity);
    }

    private double calculateTotalAmount(List<OrderItemEntity> items) {
        return items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    private OrderDto mapToDto(OrderEntity orderEntity) {
        OrderDto orderDto = new OrderDto();
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(orderEntity.getCustomer(), clientDto);

        orderDto.setOrderId(orderEntity.getOrderId());
        orderDto.setCustomer(clientDto);
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
