package com.devnet.chapaIntegration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    // payment models


        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String uuid;
        private Long amount;
        private String currency;
        private String description;
        private String status;
        @Column(length = 2000)
        private String clientSecret;
        @Column(length = 2000)
        private String checkoutUrl;

}
