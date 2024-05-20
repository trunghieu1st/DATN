package com.example.techstore.repository;

import com.example.techstore.domain.entity.ReviewFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ReviewFileRepository extends JpaRepository<ReviewFile, String> {

    @Modifying
    @Query(value = "DELETE FROM review_files r " +
            "WHERE " +
            "   r.review_id = ?1 ", nativeQuery = true)
    void deleteAllByReviewId(String reviewId);

}
