package com.example.shopping.Request.repo;

import com.example.shopping.Request.entity.CloseRequest;
import com.example.shopping.Request.entity.OpenRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CloseRequestRepository extends JpaRepository<CloseRequest, Long> {

    @Query("SELECT r FROM CloseRequest r ORDER BY CASE r.status " +
            "WHEN 'PENDING' THEN 1 " +
            "WHEN 'ACCEPTED' THEN 2 " +
            "ELSE 4 END, r.createdAt DESC")
    List<CloseRequest> findAllOrStatusAndCreatedAt();
}
