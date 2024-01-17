package com.example.myapplication.instagram

data class InstagramEntity(
    var isFollowed : Boolean = false,
    var posts : Int,
    var followers : Int,
    var following : Int,
    val id : Int,
)