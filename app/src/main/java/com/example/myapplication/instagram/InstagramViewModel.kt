package com.example.myapplication.instagram

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class InstagramViewModel : ViewModel() {

    private val _instagramAccounts = MutableLiveData<List<InstagramEntity>>().apply {
        this.value = buildList {
            repeat(30) { index ->
                add(
                    InstagramEntity(
                        id = index,
                        posts = Random.nextInt(0, 150),
                        followers = Random.nextInt(0, 3000),
                        following = Random.nextInt(0, 3000)
                    )
                )
            }
        }
    }
    val instagramAccounts: LiveData<List<InstagramEntity>> = _instagramAccounts

    fun changeFollowingStatus(newEntity: InstagramEntity) {

        val newList = instagramAccounts.value?.toMutableList()?.map { oldEntity ->
            if (oldEntity.id == newEntity.id) {
                val followersIncrement = if (newEntity.isFollowed) -1 else +1
                newEntity.copy(
                    isFollowed = !newEntity.isFollowed,
                    followers = newEntity.followers.plus(followersIncrement)
                )
            } else {
                oldEntity
            }
        }
        _instagramAccounts.value = newList
    }

    fun delete(instagramEntity: InstagramEntity) {
        val newList = _instagramAccounts.value?.toMutableList() ?: mutableListOf()
        newList.remove(instagramEntity)
        _instagramAccounts.value = newList
    }
}