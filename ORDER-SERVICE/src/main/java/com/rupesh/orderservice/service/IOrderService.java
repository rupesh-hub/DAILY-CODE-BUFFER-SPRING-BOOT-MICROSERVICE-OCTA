package com.rupesh.orderservice.service;

import com.rupesh.orderservice.model.OrderRequest;
import com.rupesh.orderservice.model.OrderResponse;

public interface IOrderService {
    OrderResponse placeOrder(OrderRequest request);
}
