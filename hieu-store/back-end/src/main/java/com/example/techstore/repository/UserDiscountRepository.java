package com.example.techstore.repository;

import com.example.techstore.domain.entity.DiscountCode;
import com.example.techstore.domain.entity.User;
import com.example.techstore.domain.entity.UserDiscount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDiscountRepository extends JpaRepository<UserDiscount, String> {

    @Query(value = "SELECT * FROM user_discounts ud " +
            "WHERE " +
            "   ud.user_id = ?1 AND ud.discount_id = ?2 ", nativeQuery = true)
    UserDiscount getByUserIdAndDiscountCodeId(String userId, String discountCodeId);

    @Query(value = "SELECT ud.* FROM user_discounts ud " +
            "WHERE ?1 IS NULL OR ud.status = ?1 ", nativeQuery = true)
    Page<UserDiscount> getAll(Boolean status, Pageable pageable);

    @Query(value = "SELECT ud.* FROM user_discounts ud " +
            "INNER JOIN discount_codes dc ON ud.discount_id = dc.id " +
            "WHERE " +
            "   ud.user_id = ?1 " +
            "   AND (?2 IS NULL OR dc.type = ?2) " +
            "   AND (?3 IS NULL OR ud.status = ?3) ", nativeQuery = true)
    Page<UserDiscount> getAllByUserId(String userId, Boolean type, Boolean status, Pageable pageable);

    boolean existsByUserAndDiscountCode(User user, DiscountCode discountCode);
}
