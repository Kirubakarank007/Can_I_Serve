package com.example.can_i_serve.Activity

import RegisterDetails
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.can_i_serve.R
import com.example.can_i_serve.databinding.AlertDialogeBinding
import com.example.can_i_serve.databinding.RegisterActivityBinding
import com.example.can_i_serve.utils
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.hbb20.CountryCodePicker

class RegisterActivity:AppCompatActivity() {
    private lateinit var binding: RegisterActivityBinding
    private lateinit var countryCode:CountryCodePicker
    private var phoneNumberLength:Int = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=RegisterActivityBinding.inflate(layoutInflater)
        binding.root.setOnClickListener {
            utils.hideKeyboard(it,this)
        }
        setContentView(binding.root)
        countryCode=binding.countryCodePicker
        countryCode.setOnCountryChangeListener {
            val code=countryCode.selectedCountryNameCode
            binding.phone.text.clear()
            phoneNumberLength=getNumberLength(code)
            binding.phone.filters= arrayOf(InputFilter.LengthFilter(phoneNumberLength))

        }
        binding.role.inputType=InputType.TYPE_NULL
        val roles = listOf("Seeker", "Provider")
        val roleAdapter = ArrayAdapter(this, R.layout.drop_down , roles)
        binding.role.setAdapter(roleAdapter)

        binding.role.setOnClickListener {
            binding.role.showDropDown()
        }
        binding.sendotpBtn.setOnClickListener {
            validationInputField()
        }
    }

    /**
     * it will get the phone number length based on the country code
     */
    private fun getNumberLength(code: String?): Int {
        val phoneUtil = PhoneNumberUtil.getInstance()
        // Get the example number for the given country
        val exampleNumber = phoneUtil.getExampleNumberForType(code, PhoneNumberUtil.PhoneNumberType.MOBILE)
        // Return the length of the example number or 0 if not available
        return exampleNumber?.nationalNumber?.toString()?.length ?: 0
    }


    private fun validationInputField() {
        val name = binding.name.text.toString()
        val age = binding.age.text
        val location = binding.location.text.toString()
        val email = binding.email.text.toString()
        val phone = binding.phone.text.toString()
        val role = binding.role.text.toString()


        if (name.isEmpty()) {
            alert("Enter name")
        } else if (age.isEmpty()) {
            alert("Enter age")
        }else if(location.isEmpty()){
            alert("Enter Location")
        }else if(email.isEmpty() && phone.isEmpty()){
            alert("Enter valid email or phone number")
        }
//        else if(email.isNotEmpty() && phone.isEmpty() ){
//          val isValid=  android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
//            if(!isValid){
//                alert("Enter vali email address")
//            }
//        }
        else if(phone.isEmpty()){
            alert("Enter Phone number")
        }else  if(phone.isNotEmpty() && phone.length!=phoneNumberLength){
            alert("Enter valid Phone Number")
        }else if(role.isEmpty()){
            alert("Select rolw")
        }else{
            var roles=0
            if(role=="Provider"){
                roles=1
            }
            val data=RegisterDetails(
                name=name,
                age=age.toString().toInt(),
                location = location,
                email=email,
                phone = phone,
                role = roles,
                countryCode = binding.countryCodePicker.selectedCountryCode
            )
            val intent= Intent(this,OTPVerificationActivity::class.java)
            intent.putExtra("register_details",data)
            startActivity(intent)
            finish()
        }
    }

     fun alert(message: String) {
         val bind=AlertDialogeBinding.inflate(layoutInflater)
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


}