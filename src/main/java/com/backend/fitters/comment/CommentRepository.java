package com.backend.fitters.comment;

import com.backend.fitters.comment.dto.CommentDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = """
            SELECT new com.backend.fitters.comment.dto.CommentDto(c.id AS commentId,
             c.text AS text, u.firstName as firstName, u.lastName AS lastName,
             p.avatarUrl AS avatarUrl, c.createdAt, u.id AS userId)
            FROM Comment c
            INNER JOIN c.user u
            INNER JOIN c.user.profile p
            INNER JOIN c.cloth cl
            WHERE cl.id = :clothId
                            """)
    Page<CommentDto> getComments(@Param("clothId") Long clothId, Pageable pageable);

}
