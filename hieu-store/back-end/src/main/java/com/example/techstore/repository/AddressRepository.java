package com.example.techstore.repository;

import com.example.techstore.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, String> {
    @Query(value = "SELECT * FROM addresses " +
            "WHERE user_id = ?1 " +
            "ORDER BY created_date ASC", nativeQuery = true)
    List<Address> getAllByUserId(String userId);

    @Modifying
    @Query(value = "UPDATE addresses " +
            "SET address_default = 00 " +
            "WHERE id <> ?1 AND created_by = ?2", nativeQuery = true)
    void changeDefault(String id, String userId);

    @Query(value = "SELECT * FROM addresses " +
            "WHERE user_id = ?1 AND address_default = 1", nativeQuery = true)
    Address getDefaultByUserId(String userId);
}
