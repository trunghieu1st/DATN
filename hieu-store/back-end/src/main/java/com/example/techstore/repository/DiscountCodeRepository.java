package com.example.techstore.repository;

import com.example.techstore.domain.entity.DiscountCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, String> {
    @Query(value = "SELECT d.* FROM discount_codes d " +
            "WHERE " +
            "(?1 = '' OR d.code LIKE CONCAT('%', ?1, '%')) " +
            "AND (?2 IS NULL OR d.type = ?2) ", nativeQuery = true)
    Page<DiscountCode> getAll(String keyword, Boolean type, Pageable pageable);

    Optional<DiscountCode> findByCode(String code);

}
