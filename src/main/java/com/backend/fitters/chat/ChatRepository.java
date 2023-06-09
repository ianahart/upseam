package com.backend.fitters.chat;

import java.util.List;

import com.backend.fitters.chat.dto.ChatMessageDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    @Query("""
            SELECT new com.backend.fitters.chat.dto.ChatMessageDto(
                         cm.id, cm.content, sp.avatarUrl AS senderAvatarUrl,
                     rp.avatarUrl AS receiverAvatarUrl, r.firstName AS receiverFirstName,
                     r.lastName AS receiverLastName, s.id AS senderUserId, r.id AS receiverUserId,
                     cm.createdAt
            )
                      FROM ChatMessage cm
                      INNER JOIN cm.sender s
                      INNER JOIN cm.receiver r
                      INNER JOIN cm.sender.profile sp
                      INNER JOIN cm.receiver.profile rp
                      WHERE s.id = :senderUserId AND r.id = :receiverUserId
                      AND cm.id = :chatMessageId
                    """)
    ChatMessageDto getChatMessage(
            @Param("chatMessageId") Long chatMessageId,
            @Param("senderUserId") Long senderUserId,
            @Param("receiverUserId") Long receiverUserId);

    @Query("""
            SELECT new com.backend.fitters.chat.dto.ChatMessageDto(
             cm.id, cm.content, sp.avatarUrl AS senderAvatarUrl,
             rp.avatarUrl AS receiverAvatarUrl, r.firstName AS receiverFirstName,
             r.lastName AS receiverLastName, s.id AS senderUserId, r.id AS receiverUserId,
             cm.createdAt

            ) FROM ChatMessage cm
              INNER JOIN cm.sender s
              INNER JOIN cm.receiver r
              INNER JOIN cm.sender.profile sp
              INNER JOIN cm.receiver.profile rp
              WHERE (r.id = :receiverUserId AND s.id = :senderUserId)
              OR (r.id = :senderUserId AND s.id = :receiverUserId)
              ORDER BY cm.id DESC LIMIT 50
                    """)

    List<ChatMessageDto> getChatMessages(@Param("receiverUserId") Long receiverUserId,
            @Param("senderUserId") Long senderUserId);
}
