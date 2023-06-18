package com.backend.fitters.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;

import com.backend.fitters.payment.request.CreatePaymentRequest;
import com.backend.fitters.user.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.PostConstruct;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserService userService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, UserService userService) {
        this.paymentRepository = paymentRepository;
        this.userService = userService;
    }

    @Value("${Stripe.apiKey}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    private Customer createCustomer(CreatePaymentRequest request) {
        Customer customer = null;
        Map<String, Object> params = new HashMap<>();
        params.put("description", "Clothing");
        params.put("email", request.getEmail());
        try {
            customer = Customer.create(params);
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println(customer);
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            this.paymentRepository.save(
                    new Payment(
                            request.getAmount(),
                            request.getEmail(),
                            customer.getId(),
                            userService.getUserById(request.getCustomerUserId()),
                            userService.getUserById(request.getBillerUserId())));

        } catch (StripeException e) {
            e.printStackTrace();
        }
        System.out.println(customer);
        return customer;
    }

    public String createPayment(CreatePaymentRequest request) {
        String intent = "";
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("usd")
                .setCustomer(createCustomer(request).getId())
                .setAmount(request.getAmount().intValue() * 100L)
                .build();

        try {
            intent = PaymentIntent.create(createParams).getClientSecret();

        } catch (StripeException e) {
            e.printStackTrace();
        }
        return intent;

    }
}
