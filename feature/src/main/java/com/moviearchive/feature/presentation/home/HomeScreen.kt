package com.moviearchive.feature.presentation.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import com.moviearchive.feature.util.BANNER_IMAGE_URL
import com.moviearchive.ui.theme.DetailIcon
import com.moviearchive.ui.theme.EmptyMovieSize
import com.moviearchive.ui.theme.EmptyTextStyle
import com.moviearchive.ui.theme.GradientBlack
import com.moviearchive.ui.theme.GradientDarkGray
import com.moviearchive.ui.theme.GradientWhite
import com.moviearchive.ui.theme.HighPadding
import com.moviearchive.ui.theme.HomeBannerHeight
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
            title = stringResource(R.string.screen_home),
            onFavoriteClicked = { isFavorite ->
                viewModel.getFavoriteMovies(isFavorite)
            }
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
    val uiState by viewModel.uiMovies.collectAsState()

    LaunchedEffect(
        key1 = true,
    ) {
        viewModel.getMovies()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TopBanner()

        Text(
            text = stringResource(R.string.list_of_movies),
            style = HomeTextTitleStyle,
            modifier = Modifier.padding(
                top = NormalPadding,
                start = HighPadding,
                bottom = NormalPadding
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
                val movies = (uiState as Success<PersistentList<MovieUiModel>>).value
                if (movies.isEmpty()) {
                    EmptyMovies()
                } else {
                    ShowMovies(
                        movies = movies,
                        onShowDetail = onShowDetail
                    )
                }
            }
        }
    }
}

@Composable
fun TopBanner() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(
                start = SmallPadding,
                end = SmallPadding,
                top = NormalPadding,
                bottom = SmallPadding,
            )
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(HomeBannerHeight)
                .clip(RoundedCornerShape(size = MovieItemRound)),
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context).data(BANNER_IMAGE_URL).build()
            ),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}

@Composable
fun EmptyMovies() {
    Column(
        modifier = Modifier
            .padding(NormalPadding)
            .fillMaxWidth()
            .height(MovieItemHeight),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(EmptyMovieSize),
            imageVector = Icons.Filled.Warning,
            tint = Color.DarkGray,
            contentDescription = null
        )
        Divider(
            Modifier
                .height(NormalPadding),
            color = Color.Transparent
        )
        Text(
            text = stringResource(id = R.string.movie_not_found),
            style = EmptyTextStyle
        )
    }
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
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
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
            Icon(
                if (movie.isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                tint = Color.Red,
                modifier = Modifier
                    .padding(NormalPadding)
                    .constrainAs(isLiked) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                contentDescription = null
            )

        }
    }
}

@Preview
@Composable
fun TopBannerPreview() {
    TopBanner()
}

@Preview
@Composable
fun EmptyMoviesPreview() {
    EmptyMovies()
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