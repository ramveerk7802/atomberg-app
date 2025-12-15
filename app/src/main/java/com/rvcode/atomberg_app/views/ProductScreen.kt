package com.rvcode.atomberg_app.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rvcode.atomberg_app.composeUtil.ProductItemView
import com.rvcode.atomberg_app.viewModelFactoroies.ProductViewModelFactory
import com.rvcode.atomberg_app.viewmodels.ProductViewModel


@Composable
fun ProductScreen(navHostController: NavHostController) {
    val productViewModel: ProductViewModel = viewModel( factory = ProductViewModelFactory())

    val devices = productViewModel.devices.observeAsState().value
    devices?.let {list->
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = list){p->
                ProductItemView(name =p.name, room = p.room ){
                    navHostController.navigate(route = "controller/${p.deviceId}")
                }
            }

        }
    }

}