package com.moviearchive.feature.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.moviearchive.feature.util.ShimmerRectagleHeight
import com.moviearchive.ui.theme.MovieItemHeight
import com.moviearchive.ui.theme.MovieItemRound
import com.moviearchive.ui.theme.MovieItemWidth
import com.moviearchive.ui.theme.SmallPadding
import com.moviearchive.ui.widget.shimmer.ShimmerRectangle

@Composable
fun ShimmerMovieItem(
    isEnableShimmer: Boolean
) {
    Column(
        modifier = Modifier
            .width(MovieItemWidth)
            .clip(RoundedCornerShape(size = MovieItemRound))
    ) {
        ShimmerRectangle(
            isEnableShimmer = isEnableShimmer,
            width = MovieItemWidth,
            height = MovieItemHeight,
            round = MovieItemRound,
            padding = SmallPadding
        )

        ShimmerRectangle(
            isEnableShimmer = isEnableShimmer,
            width = MovieItemWidth,
            height = ShimmerRectagleHeight,
            round = MovieItemRound,
            padding = SmallPadding
        )
    }
}

@Preview
@Composable
private fun ShimmerMovieItemPreview() = ShimmerMovieItem(true)