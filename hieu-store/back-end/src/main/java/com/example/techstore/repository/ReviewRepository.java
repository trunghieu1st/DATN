package com.example.techstore.repository;

import com.example.techstore.domain.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    @Query(value = "SELECT * FROM reviews r " +
            "INNER JOIN order_details od ON r.order_detail_id = od.id " +
            "INNER JOIN product_options po ON od.product_option_id = po.id " +
            "WHERE " +
            "   (?1 IS NULL OR po.product_id = ?1 " +
            "   AND (?2 IS NULL OR r.star = ?2) ", nativeQuery = true)
    Page<Review> search(String productId, Integer star, Pageable pageable);

}
