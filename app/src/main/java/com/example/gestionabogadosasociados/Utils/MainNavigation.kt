package com.example.gestionabogadosasociados

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestionabogadosasociados.Utils.ExitAppDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Composable que representa la navegación principal de la aplicación.
 *
 * @param navController Controlador de navegación que gestiona la navegación entre las distintas pantallas.
 * @param drawerState Estado del drawer.
 * @param coroutineScope Ámbito de la coroutine utilizado para controlar la apertura y cierre del drawer.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    coroutineScope: CoroutineScope = rememberCoroutineScope()

) {
    var showExitDialog by remember { mutableStateOf(false) }

    var currentRoute by remember { mutableStateOf(Routes.Login.name) }
    var serverUrl by remember { mutableStateOf("") }
    val color = colorResource(id = R.color.blue_1000)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    menus,
                    modifier = Modifier.background(color = color),

                ) { route ->
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    if (route != currentRoute)
                        navController.navigate(route)
                    currentRoute = route
                }
            }
        }
    ) {
        fun onNavigationClick(goBack: Boolean = false) {
            if (goBack) navController.navigateUp()
            else coroutineScope.launch {
                drawerState.open()
            }
        }
        NavHost(
            navController = navController, startDestination = Routes.Login.name,
            modifier = Modifier
               // .padding(top = 16.dp, start = 16.dp, end = 16.dp),

        ) {

            composable(Routes.Login.name) {
                LoginScreen(
                    title = Routes.Login.value,
                    onServerAccessClick = { url ->
                        serverUrl = url
                        navController.navigate(Routes.Webview.name)
                    }
                )
            }
            composable(Routes.Webview.name) {
                WebviewScreen(
                    title = Routes.Webview.value,
                    url = serverUrl,
                    onNavigationClick = ::onNavigationClick
                )
            }
            composable(Routes.Exit.name) {
                if (showExitDialog) {
                    ExitAppDialog(
                        showDialog = showExitDialog,
                        onExitConfirm = {
                            navController.navigate(Routes.Login.name)
                            (LocalContext as ComponentActivity).finish()
                        },
                        onDismissRequest = {
                            showExitDialog = false
                            navController.navigate(Routes.Login.name)
                        }
                    )
                } else {
                    showExitDialog = true
                }
            }
        }
    }

}

/**
 * Composable que representa el contenido del cajón lateral.
 *
 * @param menus Lista de elementos del menú.
 * @param modifier Modificador para el contenido del drawer.
 * @param onMenuClick Función lambda para manejar el clic en un elemento del menú.
 */
@Composable
private fun DrawerContent(
    menus: Array<DrawerMenu>,
    modifier: Modifier = Modifier,
    onMenuClick: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxHeight(),
                painter = painterResource(id = R.drawable.logoabo),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
        }
        var selectedMenuIndex by remember { mutableIntStateOf(0) }
        menus.forEachIndexed { index, menuItem ->
            NavigationDrawerItem(
                label = { Text(text = menuItem.title) },
                icon = { Icon(imageVector = menuItem.icon, contentDescription = null) },
                // si el índice seleccionado es el de este item, selected será true
                selected = (index == selectedMenuIndex),
                onClick = {
                    // guardamos el índice del item pulsado
                    selectedMenuIndex = index
                    onMenuClick(menuItem.route)
                },
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    selectedContainerColor = colorResource(id = R.color.blue_100),
                    selectedTextColor = MaterialTheme.colorScheme.onSecondary,
                ),
            )
        }
    }
}