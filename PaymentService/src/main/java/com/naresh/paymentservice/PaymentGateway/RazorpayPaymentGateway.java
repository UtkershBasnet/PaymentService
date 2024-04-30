package com.naresh.paymentservice.PaymentGateway;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;

import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;



@Service

public class RazorpayPaymentGateway implements PaymentGateWayService {

    @Value("${Razorpay_id}")
    String razorpayKeyId;

    @Value("${Razorpay_secret_key}")
    String razorpayKeySecret;

    @Override
    public String generatePaymentLink(long orderId , String email) throws RazorpayException, JSONException {
        RazorpayClient razorpay = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
        JSONObject paymentLinkRequest = new JSONObject();

        //43.56 -> 43.56 * 100 => 4356
        paymentLinkRequest.put("amount",100000);
        paymentLinkRequest.put("currency","INR");
        //paymentLinkRequest.put("accept_partial",true);
        //paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",1714170362);
        paymentLinkRequest.put("reference_id", "15423456");
        paymentLinkRequest.put("description","Sample payment link for SST");
        JSONObject customer = new JSONObject();
        customer.put("name","+919999999999");
        customer.put("contact","Naresh Biradar");
        customer.put("email", email);
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","SST demo paymentSErvice");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://scaler.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
        return payment.toString();

    }
}
