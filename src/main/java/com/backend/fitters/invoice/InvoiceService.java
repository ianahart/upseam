package com.backend.fitters.invoice;

import com.backend.fitters.invoice.dto.InvoiceExistDto;
import com.backend.fitters.invoice.request.CreateInvoiceRequest;
import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.order.Order;
import com.backend.fitters.order.OrderRepository;
import com.backend.fitters.user.User;
import com.backend.fitters.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
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
         request.getBid()));
        

        }
    }
}
