package com.example.techstore.repository;

import com.example.techstore.domain.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    @Query(value = "SELECT me.* FROM messages me " +
            "WHERE " +
            "   (?1 IS NULL OR me.room_id = ?1) ", nativeQuery = true)
    Page<Message> getAll(String roomId, Pageable pageable);
}
