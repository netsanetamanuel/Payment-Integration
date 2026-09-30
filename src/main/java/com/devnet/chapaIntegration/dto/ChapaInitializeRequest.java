package com.devnet.chapaIntegration.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChapaInitializeRequest {

    private String amount;
    private String currency;
    private String email;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String tx_ref;

    private String callback_url;
    private String return_url;

    private String customization_title;
    private String customization_description;
}
