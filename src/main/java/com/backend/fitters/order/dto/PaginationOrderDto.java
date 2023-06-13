package com.backend.fitters.order.dto;

import java.util.List;

import org.springframework.data.domain.Page;

public class PaginationOrderDto {
    private List<OrderDto> orders;
    private int totalPages;
    private int page;

    public PaginationOrderDto() {

    }

    public PaginationOrderDto(
            List<OrderDto> orders,
            int totalPages,
            int page) {

        this.orders = orders;
        this.totalPages = totalPages;
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
