package com.backend.fitters.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.fitters.bid.BidRepository;
import com.backend.fitters.bid.dto.RelatedEntityDto;

import java.math.BigDecimal;
import java.util.Map;

import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.advice.BadRequestException;
import com.backend.fitters.bid.request.CreateBidRequest;
import com.backend.fitters.cloth.Cloth;
import com.backend.fitters.cloth.ClothRepository;
import com.backend.fitters.user.User;
import com.backend.fitters.user.UserRepository;

@Service
public class BidService {

    private final BidRepository bidRepository;
    private final ClothRepository clothRepository;
    private final UserRepository userRepository;

    @Autowired
    public BidService(
            BidRepository bidRepository,
            ClothRepository clothRepository,
            UserRepository userRepository

    ) {
        this.bidRepository = bidRepository;
        this.clothRepository = clothRepository;
        this.userRepository = userRepository;
    }

    private RelatedEntityDto getRelatedEntities(Long userId, Long clothId) {
        User user = this.userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        Cloth cloth = this.clothRepository
                .findById(clothId)
                .orElseThrow(() -> new NotFoundException("Cloth not found"));
        return new RelatedEntityDto(user, cloth);
    }

    public void createBid(CreateBidRequest request) {
        RelatedEntityDto entities = getRelatedEntities(request.getUserId(), request.getClothId());
        BigDecimal bidField = BigDecimal.valueOf(Double.valueOf(request.getBid()));

        boolean existingBid = this.bidRepository.findByIdWithUserAndCloth(request.getUserId(), request.getClothId());
        if (existingBid) {
            throw new BadRequestException("You can only place one bid per piece of clothing");
        }

        this.bidRepository.save(new Bid(
                bidField,
                entities.getUser(),
                entities.getCloth()));
    }
}
