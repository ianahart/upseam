package com.backend.fitters.invoice;

import com.backend.fitters.invoice.dto.InvoiceDto;
import com.backend.fitters.invoice.dto.InvoiceExistDto;
import com.backend.fitters.invoice.dto.InvoicePaginationDto;
import com.backend.fitters.invoice.request.CreateInvoiceRequest;
import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.order.Order;
import com.backend.fitters.order.OrderRepository;
import com.backend.fitters.user.User;
import com.backend.fitters.user.UserService;
import com.backend.fitters.util.MyUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;

    @Autowired
    public InvoiceService(
            InvoiceRepository invoiceRepository,
            UserService userService,
            OrderRepository orderRepository) {
        this.invoiceRepository = invoiceRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    public Boolean updateInvoice(Long invoiceId) {
        Invoice invoice = this.invoiceRepository.findById(invoiceId).orElseThrow(
                () -> new NotFoundException("Invoice id not found"));

        invoice.setPaid(true);

        return this.invoiceRepository.save(invoice).getPaid();

    }

    public InvoicePaginationDto getInvoices(String direction, String sort, int page, long userId, int pageSize) {
        int currentPage = MyUtils.paginate(page, direction);
        Sort sortBy = sort.equals("DESC") ? Sort.by("id").descending() : Sort.by("id").ascending();

        Pageable paging = PageRequest.of(currentPage, pageSize, sortBy);

        Page<InvoiceDto> invoices = this.invoiceRepository.getInvoices(userId, paging);

        return new InvoicePaginationDto(
                invoices.getTotalPages(),
                currentPage,
                invoices.getContent(),
                sort,
                pageSize);

    }

    public void createInvoice(CreateInvoiceRequest request) {
        Order order = this.orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new NotFoundException("Order not found"));

        User user = this.userService.getUserById(request.getSeamsterId());

        User owner = this.userService.getUserById(order.getReceiverUser().getId());

        InvoiceExistDto invoiceExists = this.invoiceRepository.existingInvoice(user.getId(), order.getId());

        if (invoiceExists == null) {
            this.invoiceRepository.save(
                    new Invoice(
                            user,
                            order,
                            owner,
                            request.getBid(),
                            false));

        }
    }
}
