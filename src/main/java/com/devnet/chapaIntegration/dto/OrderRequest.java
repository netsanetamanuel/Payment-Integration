package com.devnet.chapaIntegration.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {


    @NotNull
    private Long amount;

    @NotBlank
    private String currency;

    private String description;
}
