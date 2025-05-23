package com.example.dsaappy

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.dsaappy.ui.theme.DSAAppyTheme

class MainActivity : ComponentActivity() {

    private lateinit var webView: WebView  // WebView als Klassenvariable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DSAAppyTheme {
                AndroidView(
                    factory = { context ->
                        webView = WebView(context).apply {
                            settings.javaScriptEnabled = true
                            settings.domStorageEnabled = true
                            webViewClient = WebViewClient()
                            webChromeClient = WebChromeClient()
                            loadUrl("file:///android_asset/app.html".replace(" ", "%20"))
                        }
                        webView
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    override fun onBackPressed() {
        // Wenn WebView zurück gehen kann, mache das, sonst beende die Activity
        if (::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}

