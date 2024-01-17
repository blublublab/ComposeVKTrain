package com.example.myapplication.vk.entity

import com.example.myapplication.R
import com.example.myapplication.vk.ui.StatisticEntity
import com.example.myapplication.vk.ui.StatisticType
import java.time.LocalTime
import java.util.UUID

data class TopicPost(
    val profileName: String= "IT Tech/",
    val time: LocalTime = LocalTime.MIDNIGHT,
    val profileImageId: Int = R.drawable.post_comunity_thumbnail,
    val topicText: String? = "Lorem ipsum dolor sit amet, consectetur adi ean euismod elementum nisi. Nunc non blandit massa enim nec. Est placerat in egestas erat imperdiet. Mattis nunc sed blandit libero. Faucibus ornare suspendisse sed nisi lacus sed viverra tellus. Sociis natoque penatibus et magnis dis. Tincidunt tortor aliquam nulla facilisi cras fermentum odio eu. Luctus accumsan tortor posuere ac ut consequat. Malesuada fames ac turpis egestas sed tempus urna et. Sagittis id consectetur purus ut faucibus pulvinar elementum. Nisl purus in mollis nunc sed id semper risus in. Nisl vel pretium lectus queo.",
    val topicImageId: Int? = R.drawable.post_content_image,
    val statistics: List<StatisticEntity> = StatisticType.entries.map { StatisticEntity(it) },
    val postId : String = UUID.randomUUID().toString()
)
