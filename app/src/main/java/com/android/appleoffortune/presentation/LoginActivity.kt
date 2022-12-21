package com.android.appleoffortune.presentation

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.VideoView
import com.android.appleoffortune.R
import com.android.appleoffortune.data.repository.LoginServerImp
import com.android.appleoffortune.domain.repository.LoginResult
import com.android.appleoffortune.domain.usecases.LoginUseCase
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
    private lateinit var _backVideo:VideoView
    private lateinit var _passText:EditText
    private lateinit var _loginButton:Button
    private lateinit var _resultText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        _passText = findViewById(R.id.passText)
        _loginButton = findViewById(R.id.buttonLogin)
        _resultText = findViewById(R.id.resultText)

        _backVideo = findViewById(R.id.back_video)
        val videoUri:Uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.stars_wars)
        _backVideo.setVideoURI(videoUri)
        _backVideo.start()

        val login: LoginUseCase = LoginUseCase(LoginServerImp(), object : LoginResult {
            override fun onSuccess() {
                _resultText.visibility = View.INVISIBLE
                val menuIntent:Intent = Intent(this@LoginActivity, MenuActivity::class.java)
                menuIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(menuIntent)
            }

            override fun onFailed() {
                _resultText.visibility = View.VISIBLE
            }
        })

        _loginButton.setOnClickListener(View.OnClickListener {

            login.Login(_passText.text.toString())
        })
    }
}