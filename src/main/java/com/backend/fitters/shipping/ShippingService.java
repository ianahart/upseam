package com.backend.fitters.shipping;

import com.backend.fitters.shipping.dto.ShippingDto;
import com.backend.fitters.shipping.request.CreateShippingRequest;

import java.util.List;

import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.user.User;
import com.backend.fitters.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    private final ShippingRepository shippingRepository;
    private final UserRepository userRepository;

    @Autowired
    public ShippingService(
            ShippingRepository shippingRepository,
            UserRepository userRepository) {
        this.shippingRepository = shippingRepository;
        this.userRepository = userRepository;
    }

    public void removeShipping(Long shippingId) {
        boolean exists = this.shippingRepository.existsById(shippingId);
        if (!exists) {
            throw new NotFoundException("Shipping id not found");
        }
        this.shippingRepository.deleteById(shippingId);
    }

    public List<ShippingDto> getShipping(Long userId) {
        return this.shippingRepository.limitedShipping(userId);
    }

    public void createShipping(CreateShippingRequest request) {
        User user = this.userRepository
                .findById(request.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        this.shippingRepository.save(
                new Shipping(
                        user,
                        request.getZipCode(),
                        request.getState(),
                        request.getCountry(),
                        request.getAddress(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getShippingType(),
                        request.getShippingValue()));

    }
}
