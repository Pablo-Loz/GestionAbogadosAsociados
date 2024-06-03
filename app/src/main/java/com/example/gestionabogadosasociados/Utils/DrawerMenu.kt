package com.example.gestionabogadosasociados

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class que representa un elemento individual en el menú.
 *
 * @property icon El icono asociado con el elemento.
 * @property route La ruta a la que navegar cuando se selecciona el elemento del menú.
 * @property title El título del elemento del menú.
 */
data class DrawerMenu(val icon: ImageVector, val route: String, val title: String)

/**
 * Array que contiene los elementos del menú.
 */
val menus = arrayOf(
    DrawerMenu(Icons.Filled.Home, Routes.Login.name, Routes.Login.value),
    DrawerMenu(Icons.Filled.Search, Routes.Webview.name, Routes.Webview.value),
    DrawerMenu(Icons.Filled.ExitToApp, Routes.Exit.name, Routes.Exit.value),
)