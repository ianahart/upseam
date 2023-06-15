package com.backend.fitters.invoice;

import com.backend.fitters.invoice.request.CreateInvoiceRequest;
import com.backend.fitters.invoice.response.CreateInvoiceResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<CreateInvoiceResponse> createInvoice(@RequestBody CreateInvoiceRequest request) {
        this.invoiceService.createInvoice(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateInvoiceResponse("success"));
    }
}
