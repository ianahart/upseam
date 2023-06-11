package com.backend.fitters.subscriber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.backend.fitters.subscriber.request.CreateSubscriberRequest;
import com.backend.fitters.advice.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {

    private final SubscriberRepository subscriberRepository;

    @Autowired
    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    private boolean validateEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public void createSubscriber(CreateSubscriberRequest request) {
        if (!validateEmail(request.getEmail())) {
            throw new BadRequestException("Please provide a valid email address");
        }

        if (this.subscriberRepository.checkEmailExists(request.getEmail())) {
            throw new BadRequestException("This email address is already subscribed");
        }

        this.subscriberRepository.save(new Subscriber(request.getEmail()));

    }
}
