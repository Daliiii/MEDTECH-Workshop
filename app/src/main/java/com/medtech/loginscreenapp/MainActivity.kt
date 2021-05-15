package com.medtech.loginscreenapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.emailEditText
import kotlinx.android.synthetic.main.activity_sign_up.*

const val SP_EMAIL_KEY = "shared_pref_email"
const val SHARED_PREFERENCE = "SharedPref"

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = applicationContext.getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE)
        if (sessionIsValid()){
            openMainScreen()
        }
        setUpViews()
    }

    private fun sessionIsValid(): Boolean {
        return sharedPreferences.getString(SP_EMAIL_KEY, null) != null
    }

    private fun setUpViews() {
        loginButton.setOnClickListener {
            if (validateAccount(emailEditText.text.toString(), passwordEditText.text.toString())) {
                saveSession(emailEditText.text.toString())
                openMainScreen()
            }
        }
        signUpText.setOnClickListener {
            val intent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openMainScreen() {
        val intent = Intent(this@MainActivity, MainScreenActivity::class.java)
        intent.putExtra(EMAIL_KEY, emailEditText.text.toString())
        intent.putExtra(NAME_KEY, "MSB")
        intent.putExtra(LAST_NAME_KEY, "MSB")
        startActivity(intent)
    }

    private fun saveSession(email: String) {
        val editor = sharedPreferences.edit()
        editor.putString(SP_EMAIL_KEY, email)
        editor.apply()
    }

    private fun validateAccount(email: String, password: String): Boolean {
        return email == "myemail@smu.tn" && password == "mypassword"
    }
}