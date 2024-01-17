package com.example.myapplication.vk.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.navigation.AppNavGraph
import com.example.myapplication.navigation.rememberNavigationState
import com.example.myapplication.vk.VKViewModel
import com.example.myapplication.vk.entity.BottomNavigationBar

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun NewsMainScreen(viewModel: VKViewModel) {
    val navigationState = rememberNavigationState()
    Scaffold(bottomBar = {
        NavigationBar {

            val navStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
            val currentRoute = navStackEntry?.destination?.route

            val elementsList = listOf(
                BottomNavigationBar.Home, BottomNavigationBar.Favourite, BottomNavigationBar.Profile
            )
            elementsList.forEach { item ->
                NavigationBarItem(selected = currentRoute == item.screen.route, onClick = {
                    navigationState.navigateTo(item.screen.route)
                }, icon = {
                    Icon(
                        imageVector = item.icon, contentDescription = item.contentDescription
                    )
                }, label = { Text(text = stringResource(id = item.titleId)) })
            }
        }
    }) { paddingValues ->
        AppNavGraph(navHostController = navigationState.navHostController, homeScreenContent = {
            HomeScreen(
                modifier = Modifier.padding(paddingValues), viewModel = viewModel
            )
        }, favouriteContent = { Text("Test") }, profileContent = { Text(text = "Test2") })
    }
}


@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
@Preview
fun VKPreview() {
    NewsMainScreen(viewModel = VKViewModel())
}