package com.devnet.chapaIntegration.dto;

import lombok.Data;

@Data
public class ChapaInitializedResponse {

    private String status;
    private String message;
    private ChapaData data;

    @Data
    public static class ChapaData {
        private String checkout_url;
        private String tx_ref;
    }


}
