package com.example.tz_game

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

      val webView :WebView = wV
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.youtube.com/")
        @SuppressWarnings("deprecation")
        val webViewClient : WebViewClient = WebViewClient()
        fun UrlLoading(view:WebView,url:String):Boolean{
            view.loadUrl(url)
            return true
        }
        @TargetApi(Build.VERSION_CODES.N)
        fun ShouldUrlLoading(view:WebView, request:WebResourceRequest):Boolean{
            view.loadUrl(request.url.toString())
            return true
        }
        webView.webViewClient = webViewClient
    }
}