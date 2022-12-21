package com.android.appleoffortune.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.android.appleoffortune.R
import com.android.appleoffortune.data.repository.GameLinkFirebase
import com.android.appleoffortune.data.repository.WebDataImpl
import com.android.appleoffortune.domain.usecases.GameLinkUseCase
import com.android.appleoffortune.domain.usecases.WebDataUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LinkAuth : AppCompatActivity() {
    private lateinit var _webData: WebDataUseCase
    private lateinit var _gameLinkData:GameLinkUseCase
    private lateinit var _urlTextField: EditText
    private lateinit var _buttonContinue: Button
    private lateinit var _buttonGetFromServer:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link_auth)
        _urlTextField = findViewById(R.id.urlText)
        _buttonContinue = findViewById(R.id.buttonContinue)
        _buttonGetFromServer = findViewById(R.id.buttonGetFromServer)

        _webData = WebDataUseCase(WebDataImpl(this))
        _gameLinkData = GameLinkUseCase(GameLinkFirebase())

        _buttonContinue.setOnClickListener(View.OnClickListener {
            _webData.setLink(_urlTextField.text.toString())
            val mainIntent:Intent = Intent(this, MenuActivity::class.java)
            startActivity(mainIntent)
        })

        _buttonGetFromServer.setOnClickListener(View.OnClickListener {
            _buttonGetFromServer.visibility = View.INVISIBLE
            GlobalScope.launch { _urlTextField.setText(_gameLinkData.GetGameLink()) }
        })

        _urlTextField.setText(_webData.getLink()?:"")

    }
}