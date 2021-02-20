package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity(name="order_item")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="unit_price")
    private Double unitPrice;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="product_id")
    private Integer product_id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;



}
