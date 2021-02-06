package com.example.thetatechnolabassignment.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.thetatechnolabassignment.R
import com.example.thetatechnolabassignment.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences =
            activity?.getSharedPreferences("sharedPrefs", AppCompatActivity.MODE_PRIVATE)
        if (sharedPreferences != null) {
            val email = sharedPreferences.getString("EMAIL", "")

            if (email!!.isNotEmpty()) {
                textViewEmailId.text = email

            }

        }
        btnLogout.setOnClickListener {
            val sharedPreferencesEditor = sharedPreferences?.edit()
            sharedPreferencesEditor?.remove("sharedPrefs")
            sharedPreferencesEditor?.clear()
            sharedPreferencesEditor?.apply()
            activity?.startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()

        }

    }


}