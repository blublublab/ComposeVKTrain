package com.example.myapplication.vk.ui

import com.example.myapplication.R


enum class StatisticType(val iconId: Int) {
    LIKES(R.drawable.ic_like), VIEWS(R.drawable.ic_views_count), REPOSTS(R.drawable.ic_share), COMMENTS(
        R.drawable.ic_comment
    )
}

data class StatisticEntity(
    val type: StatisticType, var count: Int = 0, var contentDescription: String = type.name
) {


    companion object {
        fun List<StatisticEntity>.get(statisticType: StatisticType): StatisticEntity = find {
            it.type == statisticType
        } ?: throw IllegalStateException()
    }
}


