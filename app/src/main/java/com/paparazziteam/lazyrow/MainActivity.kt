package com.paparazziteam.lazyrow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paparazziteam.lazyrow.domain.CategoryItem
import com.paparazziteam.lazyrow.screens.Home
import com.paparazziteam.lazyrow.ui.theme.LazyRowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemMenu(item: CategoryItem, onClick: (CategoryItem) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    println("ItemMenu: $item")
    Card(
        border = BorderStroke(1.dp, Color(0xFF3F51B5)),
        modifier = Modifier
            .widthIn(90.dp)
            .padding(end = 10.dp)
            .clickable(
                onClick = {
                    onClick(item)
                          //Log.d("TAG", "ItemMenu: $item")
                },
                interactionSource = interactionSource,
                indication = rememberRipple( bounded = true, radius = 24.dp, color = Color.Gray)
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (item.isSelected) Color(0xFF3F51B5) else Color.Transparent,
        ),
    ) {
        Column(
            modifier = Modifier.widthIn(90.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                color = if(item.isSelected) Color.White else Color.Black,
                text = item.name,
                modifier = Modifier.padding(5.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LazyRowTheme {
        //ItemMenu("Categorias Ensaladas  hellos")
        //test 2 lines // 1 + salto de linea
        ItemMenu(
            CategoryItem(
                id = 1,
                name = "Categorias Ensaladas  hellos",
                isSelected = false,
            ),
            onClick = {}
        )
    }
}
