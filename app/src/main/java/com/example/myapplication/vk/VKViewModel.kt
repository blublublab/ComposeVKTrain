package com.example.myapplication.vk

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.vk.entity.TopicPost
import com.example.myapplication.vk.ui.StatisticEntity

class VKViewModel : ViewModel() {

    private val _topicsPosts = MutableLiveData(listOf(TopicPost(), TopicPost(), TopicPost(), TopicPost(), TopicPost(), TopicPost(), TopicPost(), TopicPost(), TopicPost(), TopicPost(), TopicPost(), TopicPost(), TopicPost(), TopicPost(), TopicPost(), TopicPost(), TopicPost()))
    val topicPosts: LiveData<List<TopicPost>> = _topicsPosts

    fun updateStatisticCount(currentPost: TopicPost, statisticEntity: StatisticEntity) {
        //Ищем нужный элемент в постах
        Log.d("VKPOST_DEBUG", "Post id : ${currentPost.postId}\nClicked Item: ${statisticEntity.type}\nItem Count :${statisticEntity.count}")
        //Заменяем в листе все значения на новые

        val updatedStatistic = currentPost.statistics.toMutableList().map { oldStatistic ->
            if (oldStatistic.type == statisticEntity.type) {
                oldStatistic.copy(count = oldStatistic.count + 1)
            } else {
                oldStatistic
            }
        }

        //Находим элемент и  Заменяем значение в в новом листе
        val newPostList = _topicsPosts.value?.toMutableList()
        newPostList?.replaceAll { oldPost ->
            if (oldPost.postId == currentPost.postId) {
                currentPost.copy(
                    statistics = updatedStatistic,
                )
            } else {
                oldPost
            }
        }

        _topicsPosts.value = newPostList
    }

    fun deleteTopic(topicPost: TopicPost) {
        _topicsPosts.value = _topicsPosts.value?.toMutableList()?.apply { remove(topicPost) }
    }
}