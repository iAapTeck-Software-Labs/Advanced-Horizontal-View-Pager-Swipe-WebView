package com.iaapteck.AdvancedHorizontalViewPagerWebView

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.iaapteck.lib.AdvancedHorizontalViewPagerWebView

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WebView.setWebContentsDebuggingEnabled(true)
        val wv = findViewById<AdvancedHorizontalViewPagerWebView>(R.id.web_view)
        wv.settings.javaScriptEnabled = true
        wv.loadUrl("https://www.youtube.com/")// now it will not fail here
    }
}
