package com.backend.fitters.comment;

import com.backend.fitters.comment.request.CreateCommentRequest;
import com.backend.fitters.comment.response.CreateCommentResponse;
import com.backend.fitters.comment.response.GetCommentsResponse;
import com.backend.fitters.comment.response.RemoveCommentResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<RemoveCommentResponse> removeComment(@PathVariable("commentId") Long commentId) {
        this.commentService.removeComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(new RemoveCommentResponse("success"));
    }

    @GetMapping
    public ResponseEntity<GetCommentsResponse> getComments(
            @RequestParam("clothId") Long clothId,
            @RequestParam("page") int page,
            @RequestParam("totalPages") int totalPages,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("direction") String direction) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GetCommentsResponse("success",
                        this.commentService.getComments(clothId, page, totalPages, pageSize, direction)));
    }

    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(@RequestBody CreateCommentRequest request) {
        this.commentService.createComment(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateCommentResponse("success"));
    }
}
