package com.example.myapplication.vk.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.vk.VKViewModel
import com.example.myapplication.vk.entity.TopicPost


@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel : VKViewModel
){
    val topicPosts = viewModel.topicPosts.observeAsState(listOf(TopicPost()))
    LazyColumn(

        modifier = modifier,
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(topicPosts.value, key = TopicPost::postId) { topicPost ->
            TopicCard(
                swipeModifier = Modifier.animateItemPlacement(),
                topicPost = topicPost,
                onClickStatisticView = { statisticEntity ->
                    viewModel.updateStatisticCount(
                        topicPost,
                        statisticEntity
                    )
                },
                onClickStatisticLike = { statisticEntity ->
                    viewModel.updateStatisticCount(
                        topicPost,
                        statisticEntity
                    )
                },
                onClickStatisticComment = { statisticEntity ->
                    viewModel.updateStatisticCount(
                        topicPost,
                        statisticEntity
                    )
                },
                onClickStatisticRepost = { statisticEntity ->
                    viewModel.updateStatisticCount(
                        topicPost,
                        statisticEntity
                    )
                },
                onDismissed = { dismissValue ->
                    if (dismissValue == DismissValue.DismissedToStart) {
                        viewModel.deleteTopic(topicPost)
                        true
                    } else {
                        false
                    }
                })
        }
    }
}