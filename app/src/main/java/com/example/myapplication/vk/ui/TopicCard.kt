package com.example.myapplication.vk.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.vk.entity.TopicPost
import com.example.myapplication.vk.ui.StatisticEntity.Companion.get
import java.time.format.DateTimeFormatter


@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun TopicCard(
    modifier: Modifier = Modifier,
    topicPost: TopicPost,
    swipeModifier : Modifier = Modifier,
    onClickStatisticLike: (StatisticEntity) -> Unit = {},
    onClickStatisticRepost: (StatisticEntity) -> Unit = {},
    onClickStatisticComment: (StatisticEntity) -> Unit = {},
    onClickStatisticView: (StatisticEntity) -> Unit = {},
    onDismissed: (DismissValue) -> Boolean

) {
    val dismissState = rememberDismissState()
    if(dismissState.isDismissed(DismissDirection.EndToStart)){
        onDismissed(DismissValue.DismissedToStart)
    }
    SwipeToDismiss(
        modifier = swipeModifier,
        state = dismissState,
        background = {},
        directions = setOf(DismissDirection.EndToStart),
        dismissContent = {
            Card(
                modifier = modifier.fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(0.3.dp, MaterialTheme.colorScheme.secondary),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Column {
                    TopicTop(topicPost)
                    TopicContent(topicPost)
                    TopicBottom(
                        statistic = topicPost.statistics,
                        onClickStatisticLike = onClickStatisticLike,
                        onClickStatisticComment = onClickStatisticComment,
                        onClickStatisticRepost = onClickStatisticRepost,
                        onClickStatisticView = onClickStatisticView
                    )

                }
            }
        })
}


@Composable
private fun TopicTop(topicPost: TopicPost) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .padding(4.dp)
                    .clip(CircleShape),
                painter = painterResource(id = topicPost.profileImageId),
                contentDescription = stringResource(R.string.image_profile),
            )
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = topicPost.profileName, style = MaterialTheme.typography.bodySmall
                )
                Text(
                    style = MaterialTheme.typography.bodySmall,
                    text = topicPost.time.format(DateTimeFormatter.ofPattern("HH:mm")),
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = stringResource(R.string.more_options),
            tint = MaterialTheme.colorScheme.onBackground
        )
    }

}

@Composable
private fun TopicContent(
    topicPost: TopicPost, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .height(250.dp)
            .fillMaxWidth()
    ) {
        if (topicPost.topicText != null) {
            Text(
                text = topicPost.topicText,
                style = MaterialTheme.typography.bodySmall,
            )
        }

        if (topicPost.topicImageId != null) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface),
                painter = painterResource(id = topicPost.topicImageId),
                contentDescription = "Image",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillWidth
            )
        }

    }
}

@Composable
private fun TopicBottom(
    modifier: Modifier = Modifier,
    statistic: List<StatisticEntity>,
    onClickStatisticLike: (StatisticEntity) -> Unit,
    onClickStatisticRepost: (StatisticEntity) -> Unit,
    onClickStatisticComment: (StatisticEntity) -> Unit,
    onClickStatisticView: (StatisticEntity) -> Unit,
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .padding(bottom = 8.dp)
    ) {
        StatisticItem(
            modifier = Modifier.weight(0.75f), statistic = statistic.get(StatisticType.LIKES)
        ) {
            onClickStatisticLike(statistic.get(StatisticType.LIKES))
        }
        Row {
            StatisticItem(statistic = statistic.get(StatisticType.REPOSTS)) {
                onClickStatisticRepost(statistic.get(StatisticType.REPOSTS))
            }
            StatisticItem(statistic = statistic.get(StatisticType.COMMENTS)) {
                onClickStatisticComment(statistic.get(StatisticType.COMMENTS))
            }
            StatisticItem(statistic = statistic.get(StatisticType.VIEWS)) {
                onClickStatisticView(statistic.get(StatisticType.VIEWS))
            }
        }
    }
}


@Composable
fun StatisticItem(
    modifier: Modifier = Modifier, statistic: StatisticEntity, onClickItem: () -> Unit
) {
    Row(
        modifier = modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(end = 4.dp, start = 2.dp),
            text = statistic.count.toString(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary
        )
        IconButton(
            modifier = Modifier.bounceClick(),
            onClick = { onClickItem() }) {
            Icon(
                painter = painterResource(id = statistic.type.iconId),
                contentDescription = statistic.contentDescription,
                tint = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}


/*
@Composable
@Preview
fun TopicCardPreviewLight() {
    MyApplicationTheme(darkTheme = false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            TopicCard(topicPost = TopicPost())
        }

    }
}

@Composable
@Preview
fun TopicCardPreviewDark() {
    MyApplicationTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            TopicCard(topicPost = TopicPost())
        }
    }
}
*/
