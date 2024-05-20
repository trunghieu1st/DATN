package com.example.techstore.repository;

import com.example.techstore.domain.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, String> {
    @Query(value = "SELECT news.* FROM news", nativeQuery = true)
    Page<News> getAll(Pageable pageable);

    @Query(value = "SELECT * FROM news WHERE status = ?1", nativeQuery = true)
    Page<News> getByStatus(Boolean status, Pageable pageable);

}
