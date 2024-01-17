package com.example.myapplication.vk.entity

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.R
import com.example.myapplication.navigation.Screen

sealed class BottomNavigationBar(
    val screen : Screen,
    val icon : ImageVector,
    val titleId : Int,
    val contentDescription : String? = null,
) {
    data object Home : BottomNavigationBar(
        screen = Screen.NewsFeed,
        icon = Icons.Default.Home,
        titleId = R.string.home,
        contentDescription = "Home"
    )
    data object Favourite : BottomNavigationBar(
        screen = Screen.Favourite,
        icon = Icons.Default.Favorite,
        titleId = R.string.favourite,
        contentDescription = "Favourite"
    )
    data object Profile : BottomNavigationBar(
        screen = Screen.Profile,
        icon = Icons.Default.AccountCircle,
        titleId = R.string.profile,
        contentDescription = "Profile"
    )
}