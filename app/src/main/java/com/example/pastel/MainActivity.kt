package com.example.pastel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
//import com.example.pastel.ui.theme.NavGraph
//import com.example.pastel.ui.theme.PastelTheme
import com.example.pastel.ui.theme.navigation.NavGraph
import com.example.pastel.ui.theme.theme.PastelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PastelTheme {
                val navController = rememberNavController()
                Scaffold { innerPadding ->
                    NavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}