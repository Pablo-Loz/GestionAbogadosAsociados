package com.example.gestionabogadosasociados


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource


/**
 * Composable que representa la pantalla de inicio de sesión.
 *
 * @param title Título de la pantalla.
 * @param onServerAccessClick Función que se ejecuta cuando se pulsa el botón de acceso al servidor.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(title: String, onServerAccessClick: (String) -> Unit) {
    var url by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { Text(
                    text = title,
                    color = Color.White,
                    fontSize = 20.sp, // Aumenta el tamaño de fuente
                    fontWeight = FontWeight.Bold // Cambia el peso de fuente a Bold
                ) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.blue_100),
                )
            )
        },
        modifier = Modifier.fillMaxWidth(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.odoo_logo_rgb_svg_ccby4_0_removebg_preview),
                contentDescription = "Icono App",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(150.dp)

            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = url,
                onValueChange = { url = it },
                label = {
                    Text(
                        text = "Direccion del Servidor",
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (url.isNotEmpty()) {
                        onServerAccessClick(url)
                    }
                }, colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.blue_100)
                )
            ) {
                Text("Acceder al Servidor")
            }
        }
    }
}