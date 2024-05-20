package com.example.techstore.repository;

import com.example.techstore.domain.entity.User;
import com.example.techstore.domain.entity.UserRoom;
import com.example.techstore.domain.entity.UserRoomId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoomRepository extends JpaRepository<UserRoom, UserRoomId> {
    boolean existsUserRoomByUser(User user);

    boolean existsUserRoomById(UserRoomId userRoomId);

    boolean existsById(UserRoomId userRoomId);

    @Query(value = "SELECT ur.* FROM user_rooms ur " +
            "WHERE " +
            "   (?1 IS NULL OR ur.user_id= ?1) ", nativeQuery = true)
    Page<UserRoom> getAll(String userId, Pageable pageable);

    Optional<UserRoom> findUserRoomByUser(User user);

    @Query(value = "SELECT ur.* " +
            "FROM user_rooms ur " +
            "WHERE " +
            "   ur.room_id = ?1", nativeQuery = true)
    List<UserRoom> getAllUserByRoomId(String roomId);
}