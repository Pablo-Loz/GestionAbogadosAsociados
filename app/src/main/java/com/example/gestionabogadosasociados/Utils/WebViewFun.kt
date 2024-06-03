package com.example.gestionabogadosasociados


import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import kotlin.reflect.KFunction1


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebviewScreen(title: String, url: String, onNavigationClick: KFunction1<Boolean, Unit>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 20.sp, // Aumenta el tamaño de fuente
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.blue_100),
                ),
                navigationIcon = {
                    IconButton(onClick = { onNavigationClick(true) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            LoadWebView(url = url)
        }
    }
}


@Composable
fun LoadWebView(url: String) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.allowContentAccess = true
                settings.allowFileAccess = true
                settings.databaseEnabled = true
                settings.loadsImagesAutomatically = true
                settings.javaScriptCanOpenWindowsAutomatically = true
                settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
                settings.offscreenPreRaster = true
                settings.cacheMode = WebSettings.LOAD_DEFAULT
                isScrollbarFadingEnabled = true
            }
        },
        update = {
            val htmlData = """
                <!DOCTYPE html>
                <html>
                <head>
                    <style>
                        html, body { margin: 0; padding: 0; height: 100%; }
                    </style>
                </head>
                <body>
                    <div style='height: 100%; background-color: white;'></div>
                </body>
                </html>
            """
            it.loadData(htmlData, "text/html", "UTF-8")
            it.loadUrl(url)
        }
    )
}
