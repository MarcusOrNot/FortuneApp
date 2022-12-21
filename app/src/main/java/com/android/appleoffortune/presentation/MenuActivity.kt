package com.android.appleoffortune.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.view.View
import android.widget.Button
import com.android.appleoffortune.R
import com.android.appleoffortune.data.repository.WebDataImpl
import com.android.appleoffortune.domain.usecases.WebDataUseCase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.FirestoreClient
import com.google.firebase.firestore.remote.FirestoreChannel
import com.google.firebase.ktx.Firebase

class MenuActivity : AppCompatActivity() {
    private lateinit var _webData:WebDataUseCase;
    private lateinit var _buttonPlay:Button
    private lateinit var _buttonChangeLink:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        _buttonPlay = findViewById(R.id.buttonPlay)
        _buttonChangeLink = findViewById(R.id.buttonChangeLink)
        _webData = WebDataUseCase(WebDataImpl(this))

        _buttonChangeLink.setOnClickListener(View.OnClickListener {
            //_webData.setLink("")
            val linkIntent:Intent = Intent(this, LinkAuth::class.java)
            startActivity(linkIntent)
        })

        _buttonPlay.setOnClickListener(View.OnClickListener {
            val mainIntent:Intent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        })

    }
}