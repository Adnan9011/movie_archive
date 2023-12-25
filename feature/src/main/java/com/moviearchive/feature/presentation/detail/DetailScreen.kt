package com.moviearchive.feature.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.moviearchive.feature.model.MovieUiModel
import com.moviearchive.ui.theme.DetailCardHeight
import com.moviearchive.ui.theme.DetailIcon
import com.moviearchive.ui.theme.DetailImageAspectRatio
import com.moviearchive.ui.theme.MovieDetailItemTextStyle
import com.moviearchive.ui.theme.MovieDetailTextStyle
import com.moviearchive.ui.theme.NormalPadding
import com.moviearchive.ui.theme.SmallPadding
import com.moviearchive.ui.widget.AppBarDetail

@Composable
fun DetailScreen(
    modifier: Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    movieId: Int,
    onBackClicked: () -> Unit
) {

    Scaffold(
        topBar = {
            AppBarDetail(
                title = "Detailed",
                onBackClicked = onBackClicked
            )
        }
    ) { paddingValues ->
        DetailContent(
            modifier = modifier.padding(paddingValues),
            movie = MovieUiModel(
                id = 0,
                imageUrl = "",
                title = "Title",
                numComments = 0,
                numLikes = 0,
                isLiked = false
            )
        )
    }
}

@Composable
fun DetailContent(
    modifier: Modifier,
    movie: MovieUiModel
) {
    val listState = rememberLazyListState()

    DetailScaffold(
    ) {
        LazyColumn(state = listState) {
            item {
                Header(movie = movie)
            }
            item {
                Spacer(modifier = Modifier.requiredHeight(SmallPadding))
            }
            item {
                Text(
                    text = movie.title,
                    style = MovieDetailTextStyle,
                    modifier = Modifier.padding(NormalPadding)
                )
            }
        }
    }
}

@Composable
fun DetailScaffold(
    content: @Composable () -> Unit,
) {
    val backgroundColor = Color.Transparent
    Box(modifier = Modifier.fillMaxSize()) {
        Surface(modifier = Modifier.fillMaxSize(), content = content)
    }
}

@Composable
fun Header(
    movie: MovieUiModel
) {

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (
            image,
            info,
            fab,
            iconComment,
            textComment,
            iconLike,
            textLike,
        )
            = createRefs()
        Image(
            painter = rememberAsyncImagePainter(
                model = movie.imageUrl
            ),
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(DetailImageAspectRatio)
                .constrainAs(image) {
                    width = Dimension.fillToConstraints
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        top = parent.top,
                        bottom = info.top
                    )
                },
            contentScale = ContentScale.Crop
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(DetailCardHeight)
                .constrainAs(info) {
                    width = Dimension.fillToConstraints
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        top = image.bottom,
                        bottom = parent.bottom
                    )
                },
            shape = RectangleShape
        ) {}

        Icon(
            modifier = Modifier
                .padding(start = NormalPadding, top = SmallPadding)
                .size(DetailIcon)
                .constrainAs(iconComment) {
                    start.linkTo(info.start)
                    top.linkTo(info.top)
                },
            imageVector = Icons.Filled.MailOutline,
            tint = Color.Blue,
            contentDescription = ""
        )
        Text(
            text = movie.numComments.toString(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MovieDetailItemTextStyle,
            color = Color.Blue,
            modifier = Modifier
                .padding(start = NormalPadding, top = SmallPadding)
                .constrainAs(textComment) {
                    start.linkTo(iconComment.end)
                    top.linkTo(iconComment.top)
                    bottom.linkTo(iconComment.bottom)
                }
        )
        Icon(
            imageVector = Icons.Outlined.Favorite,
            contentDescription = "",
            tint = Color.Red,
            modifier = Modifier
                .padding(start = NormalPadding, top = SmallPadding)
                .size(DetailIcon)
                .constrainAs(iconLike) {
                    start.linkTo(iconComment.start)
                    top.linkTo(iconComment.bottom)
                },
        )
        Text(
            text = movie.numLikes.toString(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MovieDetailItemTextStyle,
            color = Color.Red,
            modifier = Modifier
                .padding(start = NormalPadding, top = SmallPadding)
                .constrainAs(textLike) {
                    start.linkTo(iconLike.end)
                    top.linkTo(iconLike.top)
                    bottom.linkTo(iconLike.bottom)
                }
        )

        Box(
            modifier = Modifier
                .constrainAs(fab) {
                    end.linkTo(parent.end)
                    linkTo(
                        top = info.top,
                        bottom = info.top
                    )
                }
        ) {
            FloatingActionButton(
                onClick = {
                    //Todo: Set Like and undo Liked
                },
                modifier = Modifier.padding(NormalPadding),
                shape = CircleShape
            ) {
                Icon(
                    if (movie.isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    tint = Color.Red,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        modifier = Modifier,
        movieId = 0,
        onBackClicked = {}
    )
}

@Preview
@Composable
fun HeaderPreview() {
    Header(
        MovieUiModel(
            id = 0,
            imageUrl = "",
            title = "Title",
            numComments = 11,
            numLikes = 93,
            isLiked = false
        )
    )
}