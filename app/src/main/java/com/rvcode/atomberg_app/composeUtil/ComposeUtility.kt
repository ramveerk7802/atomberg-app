package com.rvcode.atomberg_app.composeUtil

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rvcode.atomberg_app.R

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
fun ProductItemView(name: String,room:String, onClick:(String)->Unit){
    ElevatedCard(
        onClick = {
            onClick
        },
        modifier = Modifier.fillMaxWidth().padding(5.dp),
    ){

        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

            AsyncImage(
                model = R.drawable.fan_image,
                contentScale = ContentScale.Crop,
                contentDescription = "fan image",
                modifier = Modifier.size(100.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "Aris fan",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Room",
                    style = MaterialTheme.typography.labelMedium
                )
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyPreview(){

}