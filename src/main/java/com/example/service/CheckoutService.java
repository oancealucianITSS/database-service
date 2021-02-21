package com.example.service;


import com.example.dto.Purchase;
import com.example.dto.PurchaseResponse;


public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
