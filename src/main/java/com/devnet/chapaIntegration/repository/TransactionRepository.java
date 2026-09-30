package com.devnet.chapaIntegration.repository;

import com.devnet.chapaIntegration.model.Payment;
import com.devnet.chapaIntegration.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Optional<Transaction> findByUuid(String uuid);
    Optional<Transaction> findByGatewayTransactionId(String gatewayTransactionId);
    List<Transaction> findByPaymentOrderByCreatedAtDesc(Payment payment);
}
