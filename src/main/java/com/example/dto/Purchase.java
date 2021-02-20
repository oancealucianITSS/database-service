package com.example.dto;

import com.example.entity.Address;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order order;

    private Set<OrderItem> orderItems;


}
