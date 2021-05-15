package com.medtech.loginscreenapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.emailEditText

const val EMAIL_KEY = "email_key"
const val NAME_KEY = "name_key"
const val LAST_NAME_KEY = "last_name_key"

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signUpButton.setOnClickListener {
            when {
                passwordInputEditText.text.toString().length < 5 -> passwordInput.error =
                    getString(R.string.not_valid_password)
                !isValidEmail(emailEditText.text.toString()) -> emailInput.error =
                    getString(R.string.not_valid_email)
                !isValidName(nameInputEditText.text.toString()) -> nameInput.error =
                    getString(R.string.not_valid_name)
                !isValidName(lastNameInputEditText.text.toString()) -> lastNameInput.error =
                    getString(R.string.not_valid_name)
                else -> {
                    Toast.makeText(
                        this,
                        getString(R.string.sign_up_successful),
                        Toast.LENGTH_LONG
                    )
                        .show()
                    val intent = Intent(this, MainScreenActivity::class.java)
                    intent.putExtra(EMAIL_KEY, emailEditText.text.toString())
                    intent.putExtra(NAME_KEY, nameInputEditText.text.toString())
                    intent.putExtra(LAST_NAME_KEY, lastNameInputEditText.text.toString())
                    startActivity(intent)
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean =
        !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()

    private fun isValidName(name: String): Boolean =
        !TextUtils.isEmpty(name) && !name.trim().contains("[0-9]".toRegex())
}