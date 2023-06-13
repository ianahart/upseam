package com.backend.fitters.order;

import com.backend.fitters.cloth.Cloth;
import com.backend.fitters.cloth.ClothRepository;
import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.order.dto.OrderDto;
import com.backend.fitters.order.dto.PaginationOrderDto;
import com.backend.fitters.order.request.CreateOrderRequest;
import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.user.User;
import com.backend.fitters.user.UserRepository;
import com.backend.fitters.util.MyUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClothRepository clothRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
            ClothRepository clothRepository,
            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.clothRepository = clothRepository;
        this.userRepository = userRepository;
    }

    public void updateOrder(Boolean complete, Long orderId) {
        Order order = this.orderRepository
                .findById(orderId)
                .orElseThrow(() -> new NotFoundException("order Id not found"));

        order.setComplete(complete);
        this.orderRepository.save(order);

    }

    public PaginationOrderDto getOrders(Long userId, int page) {
        int currentPage = MyUtils.paginate(page, "next");
        Pageable paging = PageRequest.of(currentPage, 10, Sort.by("id"));

        Page<OrderDto> orders = this.orderRepository.getOrders(userId, paging);

        return new PaginationOrderDto(orders.getContent(), orders.getTotalPages(), currentPage);
    }

    public void createOrder(CreateOrderRequest request) {
        User user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("user not found"));
        User bidUser = this.userRepository.findById(request.getBidUserId())
                .orElseThrow(() -> new NotFoundException("bid user not found"));

        Cloth cloth = this.clothRepository.findById(request.getClothId())
                .orElseThrow(() -> new NotFoundException("cloth not found"));
        this.orderRepository.save(
                new Order(user, cloth, bidUser, false));
    }
}
