package com.android.appleoffortune.presentation

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.appleoffortune.R
import com.android.appleoffortune.data.repository.StopRateImpl
import com.android.appleoffortune.data.repository.TacticsDataImpl
import com.android.appleoffortune.data.repository.WebDataImpl
import com.android.appleoffortune.domain.usecases.StopRateUseCase
import com.android.appleoffortune.domain.usecases.TacticsUseCase
import com.android.appleoffortune.domain.usecases.WebDataUseCase
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var _mainView:WebView;
    private lateinit var _webData:WebDataUseCase
    private lateinit var _tacticsUseCase:TacticsUseCase
    private lateinit var _stopRateUseCase:StopRateUseCase
    private lateinit var _winButton:Button
    private lateinit var _loseButton:Button
    private lateinit var _infoText:TextView

    private var _currentRound:Int = 0
    private val PAUSE_TIME_MiLLISEC:Long = 3500;
    private val MAX_ROUNDS:Int = 6;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        _winButton = findViewById(R.id.winBTN)
        _loseButton = findViewById(R.id.loseBTN)
        _infoText = findViewById(R.id.infoText)

        _webData = WebDataUseCase(WebDataImpl(this))
        _tacticsUseCase = TacticsUseCase(TacticsDataImpl())
        _stopRateUseCase = StopRateUseCase(StopRateImpl())

        if (_webData.getLink()==null) {
            startActivity(Intent(this, LinkAuth::class.java).apply {
                // you can add values(if any) to pass to the next class or avoid using `.apply`
                //putExtra("keyIdentifier", value)
            })
            return
        }

        _mainView = findViewById(R.id.main_view)
        var settings:WebSettings = _mainView.settings
        settings.javaScriptEnabled = true
        settings.setLoadWithOverviewMode(true)
        settings.setUseWideViewPort(true)
        //_mainView.l
        _mainView.webViewClient = object : WebViewClient() {
            /*override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url.toString()
                view?.loadUrl(url)
                return super.shouldOverrideUrlLoading(view, request)
            } */


            override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
                webView.loadUrl(url)
                return true
            }
        }
        _mainView.loadUrl(_webData.getLink()!!)

        _winButton.setOnClickListener(View.OnClickListener {
            _currentRound++
            _loseButton.text = "LOSE"
            /*_winButton.visibility = View.INVISIBLE
            println("stop rate round 3"+ _stopRateUseCase.GetStopRate(3).toString())
            println("random tactic is "+ _tacticsUseCase.GetRandomTactic(3))*/
            val stopRate:Int = _stopRateUseCase.GetStopRate(_currentRound)
            if ((_currentRound>MAX_ROUNDS) || (stopRate>0 && Random.nextInt(1,100)<stopRate)) {
                setStopState()
            }
            else {
                _infoText.setText(_currentRound.toString()+" round: "+_tacticsUseCase.GetRandomTactic(_currentRound))
                startPause()
            }
        })

        _loseButton.setOnClickListener(View.OnClickListener {
            setLoseState()
        })

        setLoseState()

        /*GlobalScope.launch {
            val resTactic:String = _tacticsUseCase.GetRandomTactic(1)
            println("res Tactics is "+resTactic)
        }*/

        //System.out.println("++++++++++++++++++++count of links"+ link2.)
    }

    private fun startPause() {
        _winButton.visibility = View.INVISIBLE
        _loseButton.visibility = View.INVISIBLE

        object : CountDownTimer(PAUSE_TIME_MiLLISEC, PAUSE_TIME_MiLLISEC) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                _winButton.visibility = View.VISIBLE
                _loseButton.visibility = View.VISIBLE
            }

        }.start()
    }

    private fun setLoseState() {
        _currentRound = 0
        _infoText.setText("Press WIN to start")
        _loseButton.visibility = View.INVISIBLE
        _winButton.visibility = View.VISIBLE
    }

    private fun setStopState() {
        _infoText.setText("Заберите выигрыш!")
        _winButton.visibility = View.INVISIBLE
        _loseButton.visibility = View.VISIBLE
        _loseButton.text = "Take WIN"
    }
}