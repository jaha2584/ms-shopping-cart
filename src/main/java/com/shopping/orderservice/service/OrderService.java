package com.shopping.orderservice.service;

import com.shopping.orderservice.dto.ClientDto;
import com.shopping.orderservice.dto.OrderDto;
import com.shopping.orderservice.dto.OrderItemDto;
import com.shopping.orderservice.entity.ClientEntity;
import com.shopping.orderservice.entity.OrderEntity;
import com.shopping.orderservice.entity.OrderItemEntity;
import com.shopping.orderservice.repository.ClientRepository;
import com.shopping.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    private static final String ORDER_NOT_FOUND = "Order not found";
    private static final String NO_ORDERS_FOUND = "No orders have been created.";

    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

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

    public List<OrderDto> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();

        if (orders.isEmpty()) {
            throw new IllegalArgumentException(NO_ORDERS_FOUND);
        }

        return orders.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public OrderDto getOrderById(Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(ORDER_NOT_FOUND));
        return mapToDto(order);
    }

    private ClientEntity findOrCreateClient(ClientDto clientDto) {
        return clientRepository.findById(clientDto.getCustomerId())
                .orElseGet(() -> saveNewClient(clientDto));
    }

    private ClientEntity saveNewClient(ClientDto clientDto) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setCustomerId(clientDto.getCustomerId());
        clientEntity.setFirstName(clientDto.getFirstName());
        clientEntity.setLastName(clientDto.getLastName());
        clientEntity.setEmail(clientDto.getEmail());
        clientEntity.setPhoneNumber(clientDto.getPhoneNumber());
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
        clientDto.setCustomerId(orderEntity.getCustomer().getCustomerId());
        clientDto.setFirstName(orderEntity.getCustomer().getFirstName());
        clientDto.setLastName(orderEntity.getCustomer().getLastName());
        clientDto.setEmail(orderEntity.getCustomer().getEmail());
        clientDto.setPhoneNumber(orderEntity.getCustomer().getPhoneNumber());

        orderDto.setOrderId(orderEntity.getOrderId());
        orderDto.setCustomer(clientDto);
        orderDto.setItems(orderEntity.getItems().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList()));
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
