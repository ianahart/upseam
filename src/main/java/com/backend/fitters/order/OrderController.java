package com.backend.fitters.order;

import com.backend.fitters.order.request.CreateOrderRequest;
import com.backend.fitters.order.request.UpdateOrderRequest;
import com.backend.fitters.order.response.CreateOrderResponse;
import com.backend.fitters.order.response.GetOrdersResponse;
import com.backend.fitters.order.response.UpdateOrderResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<GetOrdersResponse> getOrders(
            @RequestParam("userId") Long userId,
            @RequestParam("page") int page) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new GetOrdersResponse("success", this.orderService.getOrders(userId, page)));

    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        this.orderService.createOrder(request);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(new CreateOrderResponse("success"));

    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<UpdateOrderResponse> updateOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody UpdateOrderRequest request) {
        this.orderService.updateOrder(request.getComplete(), orderId);
        return ResponseEntity.status(HttpStatus.OK).body(new UpdateOrderResponse("success"));
    }

}
