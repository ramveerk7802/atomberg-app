package com.rvcode.atomberg_app.views

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rvcode.atomberg_app.composeUtil.InputField
import com.rvcode.atomberg_app.viewModelFactoroies.AuthViewModelFactory
import com.rvcode.atomberg_app.viewmodels.AuthViewModel

@Composable
fun LoginScreen(navHostController: NavHostController){
    val application = LocalContext.current.applicationContext as Application
    val authViewModel: AuthViewModel= viewModel( factory = AuthViewModelFactory(application = application))
    val api_key = remember { mutableStateOf("") }
    val refresh_token = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp).imePadding()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter the Credential",
            style = MaterialTheme.typography.titleLarge,
        )

        InputField(
            label = "Enter the api key",
            placeHolder = "Api key here",
            text = api_key.value,
            onValueChange = {api_key.value=it}
        )
        InputField(
            label = "Enter the Refresh Token",
            placeHolder = "Refresh token here",
            text = refresh_token.value,
            onValueChange = {refresh_token.value=it}
        )

        OutlinedButton(
            onClick = {
                authViewModel.fetchAccessToken(apikey = api_key.value, refreshToken = refresh_token.value){
                    navHostController.navigate(route = "product"){
                        popUpTo("login"){
                            inclusive = true
                        }
                    }
                }
            },
        ){
            Text(
                text = "Get Access token"
            )

        }



    }
}