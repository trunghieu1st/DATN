package com.example.techstore.repository;

import com.example.techstore.domain.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    @Query(value = "SELECT * FROM carts WHERE user_id = ?1", nativeQuery = true)
    Page<Cart> getAllByUser(String userId, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM carts WHERE user_id = ?1", nativeQuery = true)
    int getNumberItem(String userId);

    @Query(value = "SELECT * FROM carts WHERE user_id = ?1 AND product_option_id = ?2", nativeQuery = true)
    Cart findByUserIdAndProductOptionId(String userId, String productOptionId);
}
