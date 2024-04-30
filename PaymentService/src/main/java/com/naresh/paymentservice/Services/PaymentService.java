package com.naresh.paymentservice.Services;

import com.naresh.paymentservice.PaymentGateway.PaymentGateWayService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGateWayService paymentGateWayService;

    public PaymentService(PaymentGateWayService paymentGateWayService){
        this.paymentGateWayService=paymentGateWayService;
    }

    public String initiatePayment(Long orderId , String email) throws StripeException, JSONException, RazorpayException {
        return paymentGateWayService.generatePaymentLink(orderId,email);
    }

}
