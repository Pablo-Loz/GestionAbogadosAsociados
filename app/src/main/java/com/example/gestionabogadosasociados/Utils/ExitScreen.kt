package com.example.gestionabogadosasociados.Utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ExitAppDialog(showDialog: Boolean, onExitConfirm: () -> Unit, onDismissRequest: () -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text("Salir de la aplicación") },
            text = { Text("¿Estás seguro de que quieres salir?") },
            confirmButton = {
                TextButton(onClick = onExitConfirm) {
                    Text("Sí")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text("No")
                }
            }
        )
    }
}