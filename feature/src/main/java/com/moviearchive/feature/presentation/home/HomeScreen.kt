package com.moviearchive.feature.presentation.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.moviearchive.core.Result.Failure
import com.moviearchive.core.Result.Loading
import com.moviearchive.core.Result.Success
import com.moviearchive.feature.R
import com.moviearchive.feature.model.MovieUiModel
import com.moviearchive.ui.theme.DetailIcon
import com.moviearchive.ui.theme.GradientBlack
import com.moviearchive.ui.theme.GradientDarkGray
import com.moviearchive.ui.theme.GradientWhite
import com.moviearchive.ui.theme.HighPadding
import com.moviearchive.ui.theme.HomeTextTitleStyle
import com.moviearchive.ui.theme.MovieDetailItemTextStyle
import com.moviearchive.ui.theme.MovieItemHeight
import com.moviearchive.ui.theme.MovieItemRound
import com.moviearchive.ui.theme.MovieItemTitleStyle
import com.moviearchive.ui.theme.MovieItemWidth
import com.moviearchive.ui.theme.NormalPadding
import com.moviearchive.ui.theme.SmallEvelation
import com.moviearchive.ui.theme.SmallPadding
import com.moviearchive.ui.widget.AppBarHome
import com.moviearchive.ui.widget.VerticalGradiant
import kotlinx.collections.immutable.PersistentList

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onShowDetail: (movieId: Int) -> Unit
) {
    Scaffold(topBar = {
        AppBarHome(
            stringResource(R.string.screen_home)
        )
    }) { paddingValues ->
        HomeContent(
            viewModel = viewModel,
            modifier = modifier.padding(paddingValues),
            onShowDetail = onShowDetail
        )
    }
}

@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    modifier: Modifier,
    onShowDetail: (movieId: Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(
        key1 = true,
    ) {
        viewModel.getMovies()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.list_of_movies),
            style = HomeTextTitleStyle,
            modifier = Modifier.padding(
                top = NormalPadding,
                start = HighPadding,
                bottom = HighPadding
            )
        )

        when (uiState) {
            Loading -> {
                //Todo: Implement Shimmer
            }

            is Failure -> {
                //Todo: Implement Error Handling
            }

            is Success -> {
                ShowMovies(
                    (uiState as Success<PersistentList<MovieUiModel>>).value,
                    onShowDetail = onShowDetail
                )
            }
        }
    }
//    }
}

@Composable
fun ShowMovies(
    movies: PersistentList<MovieUiModel>,
    onShowDetail: (movieId: Int) -> Unit
) {
    val scrollState = rememberScrollState()

    LazyRow(
        Modifier
            .verticalScroll(scrollState)
    ) {
        items(
            items = movies,
            key = { movie -> movie.id }
        ) { movie ->
            MovieItems(
                movie = movie,
                onShowDetail = onShowDetail
            )
        }
    }
}

@Composable
fun MovieItems(
    movie: MovieUiModel,
    onShowDetail: (movieId: Int) -> Unit
) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .background(Color.White)
            .padding(all = SmallPadding)
            .wrapContentSize()
            .clip(RoundedCornerShape(size = MovieItemRound)),
        shadowElevation = SmallEvelation,
        tonalElevation = SmallEvelation
    ) {
        ConstraintLayout(
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    onShowDetail(movie.id)
                }
        ) {
            val (
                image,
                gradiant,
                textComment,
                iconComment,
                textLike,
                iconLike,
                isLiked)
                = createRefs()

            Image(
                modifier = Modifier
                    .size(width = MovieItemWidth, height = MovieItemHeight)
                    .constrainAs(image) {
                        linkTo(
                            start = parent.start,
                            end = parent.end,
                            top = parent.top,
                            bottom = gradiant.bottom
                        )
                    },
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context).data(movie.imageUrl).build()
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            VerticalGradiant(
                modifier = Modifier
                    .width(MovieItemWidth)
                    .constrainAs(gradiant) {
                        linkTo(
                            start = parent.start,
                            end = parent.end,
                            top = gradiant.top,
                            bottom = image.bottom
                        )
                    },
                listColors = listOf(
                    GradientWhite,
                    GradientDarkGray,
                    GradientBlack
                )
            ) {
                Text(
                    text = movie.title,
                    style = MovieItemTitleStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = SmallPadding,
                            bottom = SmallPadding,
                            start = NormalPadding,
                            end = NormalPadding
                        ),
                    overflow = TextOverflow.Ellipsis
                )
            }

            Icon(
                modifier = Modifier
                    .padding(start = NormalPadding, top = SmallPadding)
                    .size(DetailIcon)
                    .constrainAs(iconComment) {
                        start.linkTo(image.start)
                        top.linkTo(image.bottom)
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
                    .padding(start = HighPadding, top = SmallPadding, bottom = SmallPadding)
                    .size(DetailIcon)
                    .constrainAs(iconLike) {
                        start.linkTo(textComment.end)
                        top.linkTo(iconComment.top)
                    },
            )
            Text(
                text = movie.numLikes.toString(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MovieDetailItemTextStyle,
                color = Color.Red,
                modifier = Modifier
                    .padding(start = NormalPadding, top = SmallPadding, bottom = SmallPadding)
                    .constrainAs(textLike) {
                        start.linkTo(iconLike.end)
                        top.linkTo(iconLike.top)
                        bottom.linkTo(iconLike.bottom)
                    }
            )

        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MovieItems(
        movie = MovieUiModel(
            id = 0,
            imageUrl = "",
            title = "Title",
            numComments = 0,
            numLikes = 0,
            isLiked = false
        ),
        onShowDetail = {}
    )
}