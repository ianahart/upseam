package com.backend.fitters.chat;

import java.util.List;

import com.backend.fitters.chat.dto.ChatMessageDto;
import com.backend.fitters.chat.dto.DeleteMessageDto;
import com.backend.fitters.chat.dto.GetUsersWithMessagesDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    @Modifying
    @Query("delete from ChatMessage cm where cm.id in ?1")
    void deleteChatMessagesWithIds(List<Long> ids);

    @Query(value = """
                    SELECT r.id AS receiverUserId,
                    s.id AS senderUserId, cm.id AS chatId
                    FROM chat_message cm
                    INNER JOIN _user r ON cm.receiver_id = r.id
                    INNER JOIN _user s ON cm.sender_id = s.id
                    WHERE r.id = :userOne AND s.id = :userTwo
                    OR r.id = :userTwo AND s.id = :userOne
            """, nativeQuery = true)
    List<DeleteMessageDto> getMessagesToDelete(@Param("userOne") Long userOne, @Param("userTwo") Long userTwo);

    @Query(value = """
            SELECT DISTINCT ON (r.id, s.id)
            s.id AS receiverUserId,
            cm.id AS id,
            cm.content AS content,
            s.first_name AS firstName,
            s.last_name AS lastName
            FROM chat_message cm
            INNER JOIN _user r ON cm.receiver_id = r.id
            INNER JOIN _user s ON cm.sender_id = s.id
            WHERE r.id = :currentUserId
            ORDER BY r.id, s.id
                 """, nativeQuery = true)

    Page<GetUsersWithMessagesDto> getUsersWithMessages(@Param("currentUserId") Long currentUserId, Pageable pageable);

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
