package com.backend.fitters.order.response;

import com.backend.fitters.order.dto.OrderDto;
import com.backend.fitters.order.dto.PaginationOrderDto;

import org.springframework.data.domain.Page;

public class GetOrdersResponse {
    private PaginationOrderDto orders;
    private String message;

    public GetOrdersResponse() {

    }

    public GetOrdersResponse(String message, PaginationOrderDto orders) {
        this.message = message;
        this.orders = orders;
    }

    public PaginationOrderDto getOrders() {
        return orders;
    }

    public String getMessage() {
        return message;
    }

    public void setOrders(PaginationOrderDto orders) {
        this.orders = orders;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
