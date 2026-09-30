package com.devnet.chapaIntegration.service;

import com.devnet.chapaIntegration.dto.ChapaInitializeRequest;
import com.devnet.chapaIntegration.dto.ChapaInitializedResponse;
import com.devnet.chapaIntegration.dto.OrderRequest;
import com.devnet.chapaIntegration.model.Payment;
import com.devnet.chapaIntegration.model.Transaction;
import com.devnet.chapaIntegration.repository.PaymentRepository;
import com.devnet.chapaIntegration.repository.TransactionRepository;
import lombok.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChapaPaymentService implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final TransactionRepository transactionRepository;
    private final RestClient restClient;

    @Value("${chapa.secret-key}");
    private String chapaapikey;

    public ChapaPaymentService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public String createCheckoutSession(OrderRequest orderRequest, String idempotencyKey, String baseUrl) {

        String checkoutKey = (idempotencyKey != null && !idempotencyKey.isBlank()) ? idempotencyKey + "-checkout" : UUID.randomUUID().toString() + "-checkout";

        Optional<Payment> existingOpt = paymentRepository.findByUuid(checkoutKey);

        if(existingOpt.isPresent()){
            Payment existing = existingOpt.get();
            if(existing.getCheckoutUrl() !=null && !existing.getCheckoutUrl().isBlank()){
                return existing.getCheckoutUrl();
            }
        }
        // create paymetn recored if missid
        Payment payment = Payment.builder()
                .uuid(checkoutKey)
                .amount(orderRequest.getAmount())
                .currency(orderRequest.getCurrency())
                .description(orderRequest.getDescription())
                .status(
                        "CHECKOUT_CREATED"
                ).build();
        try{
            payment = paymentRepository.save(payment);
        } catch(DataIntegrityViolationException dive){
            payment = paymentRepository.findByUuid(checkoutKey).orElseThrow(()->new RuntimeException("Failed to create or load paymetn"));
        }






    }

    @Override
    public String initializePayment(Payment payment, String email, String fName, String lName, String phoneNum) {
        String tx_Ref = "pay-"+UUID.randomUUID();

        ChapaInitializeRequest request = ChapaInitializeRequest.builder()
                .amount(payment.getAmount().toString())
                .currency(payment.getCurrency())
                .email(email)
                .first_name(fName)
                .last_name(lName)
                .phone_number(phoneNum)
                .tx_ref(tx_Ref)
                .callback_url( "http://localhost:8080/api/payments/chapa/callback")
                .return_url("http://localhost:8080/success")
                .build();

        ChapaInitializedResponse response = restClient.post()
                .uri("https://api.chapa.co/v1/transaction/initialize")
                .header(
                        "Authorization",
                        "Bearer"+chapaapikey
                )
                .body(request)
                .retrieve()
                .body(ChapaInitializedResponse.class);

        return response.getData().getCheckout_url();


    }


}
