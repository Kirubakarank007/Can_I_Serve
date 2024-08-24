package com.example.can_i_serve

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class utils {

    companion object{
         fun hideKeyboard(view: View, context: Context) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}