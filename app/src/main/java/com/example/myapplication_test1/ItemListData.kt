package com.example.myapplication_test1

data class ItemData(
    val title: String,
    val subtitle: String,
    val iconResId: Int = 0 // Ресурс значка (drawable) или 0, если значка нет
) {
    fun hasIcon(): Boolean = iconResId != 0
}

