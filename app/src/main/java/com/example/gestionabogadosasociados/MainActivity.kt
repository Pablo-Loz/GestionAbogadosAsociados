package com.example.gestionabogadosasociados

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.example.gestionabogadosasociados.ui.theme.GestionAbogadosAsociadosTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("SetJavaScriptEnabled", "UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestionAbogadosAsociadosTheme {
                MainNavigation()
            }
        }
    }


}