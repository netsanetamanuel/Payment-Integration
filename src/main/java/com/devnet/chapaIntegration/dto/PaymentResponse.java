package com.devnet.chapaIntegration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
          private boolean success;
            private String paymentUuid;
            private int amount;
           private String currency;
           private String  status;
            private String checkoutUrl;
            private String message;

    private String lastTransactionUuid;
    private String lastTransactionStatus;
    //private String lastGateway;
    //private String lastGatewayTransactionId;
    private Instant lastTransactionCreatedAt;

}
