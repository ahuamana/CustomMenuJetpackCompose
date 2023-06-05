package com.paparazziteam.lazyrow.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paparazziteam.lazyrow.domain.CategoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {

    private val listCategories = mutableListOf<CategoryItem>()

    private val _listCategories = MutableStateFlow<ListCategoriesState>(ListCategoriesState.HideLoading)
    val listCategoriesState = _listCategories.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ListCategoriesState.HideLoading
    )

    init {
        getListCategories()
    }

    fun getListCategories() = viewModelScope.launch {
        _listCategories.value = ListCategoriesState.ShowLoading
        delay(2000)
        _listCategories.value = ListCategoriesState.HideLoading
        delay(2000)
        _listCategories.value = ListCategoriesState.Success(
            createListCategories()
        )

        //save list
        listCategories.addAll(createListCategories())

    }

    //clickedCategory
    private fun createListCategories():List<CategoryItem>{
        return listOf(
            CategoryItem(
                id = 1,
                name = "Category 1",
                isSelected = false
            ),
            CategoryItem(
                id = 2,
                name = "Category 2",
                isSelected = false
            ),
            CategoryItem(
                id = 3,
                name = "Category 3",
                isSelected = false
            ),

            )
    }

    //submit categoryclicked
    fun submitCategoryClicked(category: CategoryItem) = viewModelScope.launch {
        //create map with category selected
        val newList = listCategories.map {
            if(it.id == category.id){
                it.copy(isSelected = true)
            }else{
                it
            }
        }

        //update state
        _listCategories.value = ListCategoriesState.Success(newList)
    }

    //

}



sealed class ListCategoriesState {
    object HideLoading : ListCategoriesState()
    object ShowLoading : ListCategoriesState()
    data class Success(val list: List<CategoryItem>) : ListCategoriesState()
    data class Error(val error: String) : ListCategoriesState()
}