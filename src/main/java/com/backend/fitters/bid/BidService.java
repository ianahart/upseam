package com.backend.fitters.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.fitters.bid.BidRepository;
import com.backend.fitters.bid.dto.BidDto;
import com.backend.fitters.bid.dto.BidsWithPaginationDto;
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
import com.backend.fitters.util.MyUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    public BidsWithPaginationDto getBids(Long clothId, int page, String direction, int pageSize) {
        int currentPage = MyUtils.paginate(page, direction);
        Pageable paging = PageRequest.of(currentPage, pageSize, Sort.by("id"));

        return new BidsWithPaginationDto(
                this.bidRepository.findAllBidsByClothId(clothId, paging),
                currentPage,
                direction);
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
