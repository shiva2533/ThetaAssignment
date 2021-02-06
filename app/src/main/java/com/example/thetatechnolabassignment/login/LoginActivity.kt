package com.example.thetatechnolabassignment.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.thetatechnolabassignment.R
import com.example.thetatechnolabassignment.home.model.HomeActivity
import com.example.thetatechnolabassignment.util.TextUtil
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
    lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        if (sharedPreferences != null) {
            val email = sharedPreferences.getString("EMAIL", "")

            val password = sharedPreferences.getString("PASSWORD", "")
            if (email!!.isNotEmpty() && password!!.isNotEmpty()) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
        val repository = LoginRepository(this)
        val viewModelProviderFactory = LoginViewModelProviderFactory(repository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(LoginViewModel::class.java)

        btnLogin.setOnClickListener {
            if (validateEmailPassword(
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString()
                )
            ) {

                viewModel.saveCredentials(
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString()
                )

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }


        }
    }

    private fun validateEmailPassword(emai: String, password: String): Boolean {
        if (!TextUtil.isValidEmail(emai)) {
            editTextEmail.setError("Wrong email")
            return false
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Enter password")
            return false
        }
        return true
    }
}