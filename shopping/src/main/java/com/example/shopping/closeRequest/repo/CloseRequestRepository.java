package com.example.shopping.closeRequest.repo;

import com.example.shopping.closeRequest.entity.CloseRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloseRequestRepository extends JpaRepository<CloseRequest, Long> {
}
