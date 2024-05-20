package com.example.techstore.repository;

import com.example.techstore.domain.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
    @Query(value = "SELECT fl.* FROM files fl ", nativeQuery = true)
    Page<File> getAll(Pageable pageable);
}
