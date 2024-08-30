package com.example.can_i_serve.Activity

import RegisterDetails
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.can_i_serve.Database.AppDatabase
import com.example.can_i_serve.R
import com.example.can_i_serve.databinding.RegisterActivityBinding
import com.example.can_i_serve.utils
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.hbb20.CountryCodePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity:AppCompatActivity() {
    private lateinit var binding: RegisterActivityBinding
    private lateinit var countryCode:CountryCodePicker
    private var phoneNumberLength:Int = 10
    private lateinit var phoneNumberList:MutableList<String>
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
       CoroutineScope(Dispatchers.IO).launch {
           val db=AppDatabase.getDatabase(this@RegisterActivity).canIServeDao().getAllUsers()
          if(db.isNotEmpty()){
              val list=utils.getPhoneNumberList(this@RegisterActivity,db)
              phoneNumberList=list
          }else{
              phoneNumberList= mutableListOf()
          }
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
        val name = binding.name.text.toString().trim()
        val age = binding.age.text.trim()
        val location = binding.location.text.toString().trim()
        val email = binding.email.text.toString().trim()
        val phone = binding.phone.text.toString().trim()
        val role = binding.role.text.toString()


        if (name.isEmpty()) {
           utils.showAlert(this,"Enter name")
        } else if (age.isEmpty()) {
            utils.showAlert(this,"Enter age")
        }else if (age.isNotEmpty() &&  age.toString().toInt()<=18){
            utils.showAlert(this,"Age is must be greater than 18")
        }
        else if(location.isEmpty()){
            utils.showAlert(this,"Enter Location")
        }else if(email.isEmpty() && phone.isEmpty()){
            utils.showAlert(this,"Enter valid email or phone number")
        }
//        else if(email.isNotEmpty() && phone.isEmpty() ){
//          val isValid=  android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
//            if(!isValid){
//                alert("Enter vali email address")
//            }
//        }
        else if(phone.isEmpty()){
            utils.showAlert(this,"Enter Phone number")
        }else if(phone.isNotEmpty() && phoneNumberList.contains(phone)){
            utils.showAlert(this,"This Phone number already registered")
        }

        else  if(phone.isNotEmpty() && phone.length!=phoneNumberLength){
            utils.showAlert(this,"Enter valid Phone Number")
        }else if(role.isEmpty()){
            utils.showAlert(this,"Select rolw")
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
                countryCode = binding.countryCodePicker.selectedCountryCode,
                password = null
            )
            val intent= Intent(this,OTPVerificationActivity::class.java)
            intent.putExtra("register_details",data)
            startActivity(intent)
            finish()
        }
    }
}