package com.example.chalo.presentation.cart

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.chalo.BuildConfig
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class PaymentActivity : ComponentActivity(), PaymentResultListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startPayment()
    }

    private fun startPayment() {
        val checkout = Checkout()
        checkout.setKeyID(BuildConfig.RAZORPAY_KEY)

        val amountPaise = intent.getIntExtra("amount", 0)

        try {
            val options = JSONObject()
            options.put("name", "Chalo")
            options.put("description", "Service Booking Payment")
            options.put("currency", "INR")
            options.put("amount", amountPaise)

            checkout.open(this, options)
        } catch (e: Exception) {
            finish()
        }
    }

    override fun onPaymentSuccess(razorpayPaymentId: String?) {
        setResult(RESULT_OK)
        finish()
    }

    override fun onPaymentError(code: Int, response: String?) {
        setResult(RESULT_CANCELED)
        finish()
    }
}