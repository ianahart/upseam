package com.backend.fitters.invoice;

import com.backend.fitters.invoice.request.CreateInvoiceRequest;
import com.backend.fitters.invoice.response.CreateInvoiceResponse;
import com.backend.fitters.invoice.response.GetInvoicesResponse;
import com.backend.fitters.invoice.response.UpdateInvoiceResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PatchMapping("/{invoiceId}")
    public ResponseEntity<UpdateInvoiceResponse> updateInvoice(@PathVariable("invoiceId") Long invoiceId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new UpdateInvoiceResponse("success", this.invoiceService.updateInvoice(invoiceId)));

    }

    @GetMapping
    public ResponseEntity<GetInvoicesResponse> getInvoices(
            @RequestParam("direction") String direction,
            @RequestParam("sort") String sort,
            @RequestParam("page") int page,
            @RequestParam("userId") Long userId,
            @RequestParam("pageSize") int pageSize) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new GetInvoicesResponse("success",
                        this.invoiceService.getInvoices(direction, sort, page, userId, pageSize)));
    }

    @PostMapping
    public ResponseEntity<CreateInvoiceResponse> createInvoice(@RequestBody CreateInvoiceRequest request) {
        this.invoiceService.createInvoice(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateInvoiceResponse("success"));
    }
}
