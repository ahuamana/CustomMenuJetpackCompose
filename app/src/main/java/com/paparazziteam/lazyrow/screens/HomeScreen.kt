package com.paparazziteam.lazyrow.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.paparazziteam.lazyrow.ItemMenu
import com.paparazziteam.lazyrow.R
import com.paparazziteam.lazyrow.domain.CategoryItem

@Composable
fun HomeScreen(
    list: List<CategoryItem>,
    onClick: (CategoryItem) -> Unit
) {

    LazyRow(
        contentPadding = PaddingValues(10.dp),
    ) {
        itemsIndexed(list) { index, item ->
            ItemMenu(item, onClick)
        }
    }
}


@Composable
fun Home() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val state = viewModel.listCategoriesState.collectAsStateWithLifecycle()

    when(val currentState = state.value) {
        is ListCategoriesState.ShowLoading -> {
            Log.e("ShowLoading", "ShowLoading")
            //image loading
            Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Loading")
        }

        is ListCategoriesState.Error -> {

        }
        ListCategoriesState.HideLoading -> {

        }
        is ListCategoriesState.Success -> {
            HomeScreen(
                currentState.list,
                onClick = {
                    viewModel.submitCategoryClicked(it)
                }
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPrev() {
    Home()
}