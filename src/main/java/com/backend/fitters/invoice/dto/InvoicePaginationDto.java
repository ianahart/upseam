package com.backend.fitters.invoice.dto;

import java.util.List;

import org.springframework.data.domain.Page;

public class InvoicePaginationDto {
    private int totalPages;
    private int page;
    private List<InvoiceDto> invoices;
    private String sort;
    private int pageSize;

    public InvoicePaginationDto() {

    }

    public InvoicePaginationDto(
            int totalPages,
            int page,
            List<InvoiceDto> invoices,
            String sort,
            int pageSize) {
        this.totalPages = totalPages;
        this.page = page;
        this.invoices = invoices;
        this.sort = sort;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public List<InvoiceDto> getInvoices() {
        return invoices;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public String getSort() {
        return sort;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setInvoices(List<InvoiceDto> invoices) {
        this.invoices = invoices;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
