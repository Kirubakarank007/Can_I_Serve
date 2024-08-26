package com.example.can_i_serve.Activity

import RegisterDetails
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.can_i_serve.databinding.AlertDialogeBinding
import com.example.can_i_serve.databinding.OtpScreenActivityBinding
import com.example.can_i_serve.utils
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import java.io.Serializable
import java.util.concurrent.TimeUnit

class OTPVerificationActivity: AppCompatActivity() {
    private lateinit var binding:OtpScreenActivityBinding
    private lateinit var mAuth:FirebaseAuth
    lateinit var verificationCode:String
    lateinit var resendingToken:PhoneAuthProvider.ForceResendingToken
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=OtpScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth=FirebaseAuth.getInstance()
        val data:RegisterDetails = intent.getSerializableExtra("register_details") as RegisterDetails
        Log.d("##OTP",data.toString())

        sendOtp("+"+data.countryCode+data.phone,false)
//       testing firebase is connected or not
//        val value: MutableMap<String, String> = HashMap()
//        FirebaseFirestore.getInstance().collection("test").add(value)
        binding.verifyBtn.setOnClickListener {
            verifyOtp()
        }

    }

    private fun verifyOtp() {
        val realOTP=verificationCode.substring(0,4)
        val otp1=binding.otp1.text.toString()
        val otp2 =binding.otp2.text
        val otp3= binding.otp3.text
        val otp4=binding.otp4.text
        val otp=otp1+otp2+otp3+otp4
        Log.d("##OTP","$otp ${verificationCode}")

        if(otp==realOTP){
            val intent= Intent(this,PasswordActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Log.d("##OTP","$otp ${verificationCode.substring(0,4)}")
            alert("Enter valid OTP")
        }
    }

    private fun alert(message: String) {
        val bind= AlertDialogeBinding.inflate(layoutInflater)
        val dialogView = bind.root
        val alertMessage = bind.alertMessage
        val alertOkButton = bind.alertOkButton
        alertMessage.text = message

        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        alertOkButton.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    private fun sendOtp(phone: String?, isResend: Boolean) {
        val builder = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(phone!!)   // Set the phone number for verification
            .setTimeout(60L, TimeUnit.SECONDS)  // Set timeout duration
            .setActivity(this)         // Make sure to set the current activity
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    utils.toastMessage(this@OTPVerificationActivity, "Verification Completed")
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Log.e("##OTP", "Verification failed: ${e.message}")
                    utils.toastMessage(this@OTPVerificationActivity, "OTP verification failed: ${e.localizedMessage}")
                }

                override fun onCodeSent(OTP: String, token: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(OTP, token)
                    verificationCode = OTP
                    resendingToken = token
                    utils.toastMessage(this@OTPVerificationActivity, "OTP sent successfully")
                }
            })

        // Handle resending of the OTP
        if (isResend) {
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build())
        } else {
            PhoneAuthProvider.verifyPhoneNumber(builder.build())
        }
    }


}