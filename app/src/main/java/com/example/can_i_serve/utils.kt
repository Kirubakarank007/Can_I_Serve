package com.example.can_i_serve

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

class utils {

    companion object{
         fun hideKeyboard(view: View, context: Context) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        fun toastMessage(context: Context,message:String){
          return  Toast.makeText(context,message,Toast.LENGTH_LONG).show()
        }

        fun print(msg:String){
            Log.d("##data",msg)
        }

    }
}