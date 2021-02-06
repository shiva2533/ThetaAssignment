package com.example.thetatechnolabassignment.util

import android.util.Patterns
import android.view.View
import com.google.android.material.snackbar.Snackbar

class TextUtil {
    companion object {

        fun isValidEmail(email: String?): Boolean {
            if (email!!.isEmpty())
                return false
            else return Patterns.EMAIL_ADDRESS.matcher(email).matches();


        }

        fun View.showSnackbar(message: String) {
            Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()

        }



    }
}