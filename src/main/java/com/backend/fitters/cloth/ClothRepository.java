package com.backend.fitters.cloth;

import java.util.List;

import com.backend.fitters.cloth.dto.ClothesDto;
import com.backend.fitters.cloth.dto.ClothDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ClothRepository extends JpaRepository<Cloth, Long> {

    @Query(value = """
            SELECT new com.backend.fitters.cloth.dto.ClothesDto(
            c.id, c.clothUrl, c.dueDate, c.size, c.description, c.createdAt,
            c.updatedAt, u.firstName, u.lastName, u.email, u.id AS userId
            ) FROM Cloth c LEFT JOIN c.user u WHERE u.id = :userId""")
    Page<ClothesDto> findAllByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query(value = """
            SELECT new com.backend.fitters.cloth.dto.ClothesDto(
            c.id, c.clothUrl, c.dueDate, c.size, c.description, c.createdAt,
            c.updatedAt, u.firstName, u.lastName, u.email, u.id AS userId
            ) FROM Cloth c LEFT JOIN c.user u""")
    List<ClothesDto> findAllClothes(@Param("userId") Long userId);

    @Query(value = """
            SELECT new com.backend.fitters.cloth.dto.ClothDto(
             c.clothUrl, c.dueDate, c.id, c.description, c.size,
             u.id AS userId)
             FROM Cloth c LEFT JOIN c.user u
             WHERE c.id = :clothId""")

    ClothDto findByClothId(@Param("clothId") Long clothId);

}