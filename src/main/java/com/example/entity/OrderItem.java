package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="order_item")
@Getter
@Setter
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
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}








