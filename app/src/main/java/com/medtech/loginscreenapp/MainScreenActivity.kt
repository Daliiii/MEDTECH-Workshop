package com.medtech.loginscreenapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main_screen.*

class MainScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        val email = intent.getStringExtra(EMAIL_KEY)
        val name = intent.getStringExtra(NAME_KEY)
        val lastName = intent.getStringExtra(LAST_NAME_KEY)
        greetingText.text = getString(R.string.greetings_message, name, lastName)
        emailTextView.text = getString(R.string.email_message, email)
        setUpRecyclerView()
        logoutButton.setOnClickListener {
            val sharedPreferences = applicationContext.getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpRecyclerView() {
        val provider = ItemListProvider()
        val items = provider.provideList()
        val adapter = ItemsAdapter(items){
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}