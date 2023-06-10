package com.backend.fitters.chat;

import com.backend.fitters.chat.request.SendChatMessageRequest;
import com.backend.fitters.chat.response.GetChatMessagesResponse;
import com.backend.fitters.chat.response.GetUsersWithMessagesResponse;
import com.backend.fitters.chat.response.SendChatMessageResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/users")
    public ResponseEntity<GetUsersWithMessagesResponse> getUsersWithMessages(
            @RequestParam("currentUserId") Long currentUserId,
            @RequestParam("page") int page) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new GetUsersWithMessagesResponse("success",
                        this.chatService.getUsersWithMessages(currentUserId, page)));
    }

    @GetMapping
    public ResponseEntity<GetChatMessagesResponse> getChatMessages(
            @RequestParam("receiverUserId") Long receiverUserId,
            @RequestParam("senderUserId") Long senderUserId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GetChatMessagesResponse("success",
                        this.chatService.getChatMessages(receiverUserId, senderUserId)));

    }

    @PostMapping
    public ResponseEntity<SendChatMessageResponse> sendChatMessage(@RequestBody SendChatMessageRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new SendChatMessageResponse("success", this.chatService.sendChatMessage(request)));
    }
}
