package com.naresh.paymentservice.Controllers;

import com.naresh.paymentservice.Dtos.InitiatePaymentRequestDto;
import com.naresh.paymentservice.PaymentGateway.PaymentGateWayService;
import com.naresh.paymentservice.Services.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payments")
public class PaymentGatewayController {

    private PaymentService paymentService;

    public PaymentGatewayController(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    @PostMapping("/")
    public String initiatePayment(@RequestBody  InitiatePaymentRequestDto initiatePaymentRequestDto) throws StripeException, JSONException, RazorpayException {
        return paymentService.initiatePayment(
                initiatePaymentRequestDto.getOrderId(),
                initiatePaymentRequestDto.getEmail()
        );
    }
}
