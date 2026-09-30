package com.devnet.chapaIntegration.repository;

import com.devnet.chapaIntegration.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByUuid(String uuid);
    Optional<Payment> findById(Long id);

}
