package com.devnet.chapaIntegration.service;

import com.devnet.chapaIntegration.dto.OrderRequest;
import com.devnet.chapaIntegration.model.Payment;

public interface PaymentService {
    public String createCheckoutSession(OrderRequest orderRequest,String idempotencyKey,String baseUrl);
    public String initializePayment(Payment payment , String email, String fName, String lName);

    String initializePayment(Payment payment, String email, String fName, String lName, String phoneNum);
}
