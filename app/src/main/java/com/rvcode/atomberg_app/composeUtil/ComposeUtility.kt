package com.rvcode.atomberg_app.composeUtil

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InputField(label: String, placeHolder: String, text:String,onValueChange:(String)-> Unit){
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeHolder)
        }
    )
}


@Composable
fun ProductItemView(){
    ElevatedCard(
        onClick = {},
        modifier = Modifier.fillMaxWidth()
    ){

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyPreview(){

}