package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.compose.MyApplicationTheme
import com.example.myapplication.instagram.InstagramViewModel
import com.example.myapplication.vk.VKViewModel
import com.example.myapplication.vk.ui.NewsMainScreen

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    private val instagramViewModel by viewModels<InstagramViewModel>()
    private val vkViewModel by viewModels<VKViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Test(viewModel = vkViewModel)
            }
        }
    }

    @Composable
    fun Test(viewModel: VKViewModel){
        NewsMainScreen(viewModel = viewModel)
    }




}


