package com.example.pastel.ui.theme.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pastel.ui.theme.screens.Pastel.PastelGastosScreen
import com.example.pastel.ui.theme.screens.Barras.BarrasGastosScreen

object RutasWalletView {
    const val PASTEL = "pastel_gastos"
    const val BARRAS = "barras_gastos"
}

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val rutaActual = navController.currentBackStackEntry?.destination?.route

            if (rutaActual == RutasWalletView.PASTEL || rutaActual == null) {
                Button(
                    onClick = { navController.navigate(RutasWalletView.PASTEL) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Pastel")
                }
            } else {
                OutlinedButton(
                    onClick = { navController.navigate(RutasWalletView.PASTEL) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Pastel")
                }
            }

            if (rutaActual == RutasWalletView.BARRAS) {
                Button(
                    onClick = { navController.navigate(RutasWalletView.BARRAS) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Barras")
                }
            } else {
                OutlinedButton(
                    onClick = { navController.navigate(RutasWalletView.BARRAS) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Barras")
                }
            }
        }

        NavHost(
            navController = navController,
            startDestination = RutasWalletView.PASTEL,
            modifier = Modifier.weight(1f)
        ) {
            composable(route = RutasWalletView.PASTEL) {
                PastelGastosScreen()
            }
            composable(route = RutasWalletView.BARRAS) {
                BarrasGastosScreen()
            }
        }
    }
}