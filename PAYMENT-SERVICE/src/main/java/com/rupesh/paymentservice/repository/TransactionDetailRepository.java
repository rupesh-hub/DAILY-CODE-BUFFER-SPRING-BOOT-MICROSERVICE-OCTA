package com.rupesh.paymentservice.repository;

import com.rupesh.paymentservice.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {
    Optional<TransactionDetail> findByOrderId(String orderId);
}
