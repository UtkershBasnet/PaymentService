package com.naresh.paymentservice.PaymentGateway;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface PaymentGateWayService {

    String generatePaymentLink(long orderId , String email) throws StripeException, RazorpayException, JSONException;
}
