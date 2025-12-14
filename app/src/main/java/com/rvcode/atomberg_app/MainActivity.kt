package com.rvcode.atomberg_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.rvcode.atomberg_app.networks.RetrofitClient
import com.rvcode.atomberg_app.ui.theme.AtombergappTheme
//import com.rvcode.atomberg_app.views.LoginScreen
//import dagger.hilt.android.AndroidEntryPoint

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        RetrofitClient.init(application = application)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AtombergappTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Atomberg Fan controller")
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                   NavigationGraph(modifier = Modifier.padding(innerPadding), navHostController = navController)
                }

            }
        }
    }
}



