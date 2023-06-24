package com.backend.fitters.comment;

import com.backend.fitters.cloth.Cloth;
import com.backend.fitters.cloth.ClothRepository;
import com.backend.fitters.comment.dto.CommentDto;
import com.backend.fitters.comment.dto.PaginationCommentDto;
import com.backend.fitters.comment.request.CreateCommentRequest;
import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.user.UserService;
import com.backend.fitters.util.MyUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ClothRepository clothRepository;
    private final UserService userService;

    @Autowired
    public CommentService(CommentRepository commentRepository,
            UserService userService,
            ClothRepository clothRepository) {
        this.commentRepository = commentRepository;
        this.clothRepository = clothRepository;
        this.userService = userService;
    }

    public void removeComment(Long commentId) {
        boolean exists = this.commentRepository.existsById(commentId);

        if (exists) {
            this.commentRepository.deleteById(commentId);
        }
    }

    public PaginationCommentDto getComments(Long clothId, int page, int totalPages, int pageSize, String direction) {
        int currentPage = MyUtils.paginate(page, direction);

        Pageable paging = PageRequest.of(currentPage, pageSize, Sort.by("id").descending());

        Page<CommentDto> results = this.commentRepository.getComments(clothId, paging);

        return new PaginationCommentDto(
                results.getContent(),
                currentPage,
                pageSize,
                results.getTotalPages(),
                direction);

    }

    public void createComment(CreateCommentRequest request) {
        Cloth cloth = this.clothRepository.findById(request.getClothId())
                .orElseThrow(() -> new NotFoundException("Cloth not found."));
        this.commentRepository.save(
                new Comment(request.getText(),
                        this.userService.getUserById(request.getUserId()),
                        cloth));

    }
}
