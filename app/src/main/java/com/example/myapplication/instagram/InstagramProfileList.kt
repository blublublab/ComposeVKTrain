package com.example.myapplication.instagram

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R


@ExperimentalMaterial3Api
@Composable
fun InstagramProfileList(
    viewModel: InstagramViewModel
) {
    val instagramAccountsState = viewModel.instagramAccounts.observeAsState(listOf())
    LazyColumn {
        items(instagramAccountsState.value, key = { it.id }) {
            val dismissState = rememberDismissState()

            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.delete(it)
            }

            SwipeToDismiss(state = dismissState, background = {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "<- Delete",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                }
            }, dismissContent = {
                InstagramProfileCard(
                    instagramEntity = it, viewModel = viewModel
                )
            }, directions = setOf(DismissDirection.EndToStart))

        }
    }
}

@Composable
fun InstagramProfileCard(
    instagramEntity: InstagramEntity, viewModel: InstagramViewModel
) {

    Card(
        modifier = Modifier.padding(8.dp), shape = RoundedCornerShape(
            topStart = 4.dp, topEnd = 4.dp
        ), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ), border = BorderStroke(
            width = 1.dp, color = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(
                        start = 26.dp, top = 8.dp, bottom = 0.dp, end = 24.dp
                    )
                    .fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier,
                        text = "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 0.5.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    FollowButton(instagramEntity.isFollowed) {
                        viewModel.changeFollowingStatus(instagramEntity)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 4.dp, end = 4.dp
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                InstagramImage()
                UserStatistics("Posts", instagramEntity.posts.toString())
                UserStatistics("Followers", instagramEntity.followers.toString())
                UserStatistics("Following", instagramEntity.following.toString())
            }
        }

    }

}


@Composable
private fun FollowButton(
    isFollowed: Boolean, isFollowedClickListener: () -> Unit
) {
    Button(
        onClick = {
            isFollowedClickListener()
        }, colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(
                alpha = if (isFollowed) 0.5f else 1f
            ),
        )
    ) {
        val text = if (isFollowed) "Unfollow" else "Follow"
        Text(text = text)
    }
}


@Composable
private fun UserStatistics(title: String, value: String) {
    Column(
        modifier = Modifier.height(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(

            text = value,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
        Text(
            text = title,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            letterSpacing = 1.5.sp,
        )
    }
}

@Composable
private fun InstagramImage() {
    Image(
        modifier = Modifier
            .clip(CircleShape)
            .size(50.dp),
        painter = painterResource(id = R.drawable.instagram_profile_picture),
        contentDescription = "Instagram",
        contentScale = ContentScale.Crop
    )
}

/*
@Preview
@Composable
fun PreviewCardLight() {
    MyApplicationTheme(
        darkTheme = false
    ) {
        InstagramProfileCard(MainViewModel())
    }
}

@Preview
@Composable
fun PreviewCardDark() {
    MyApplicationTheme(
        darkTheme = true
    ) {
        InstagramProfileCard(MainViewModel())
    }
}
*/
