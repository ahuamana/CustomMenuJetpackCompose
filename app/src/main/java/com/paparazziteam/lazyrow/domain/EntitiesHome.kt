package com.paparazziteam.lazyrow.domain

data class CategoryItem(
    val id: Int,
    val name: String,
    val isSelected: Boolean = false
)