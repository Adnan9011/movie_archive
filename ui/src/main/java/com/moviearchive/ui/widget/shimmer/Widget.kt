package com.moviearchive.ui.widget.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerCircle(
    isEnableShimmer: Boolean,
    isLightModeActive: Boolean = false,
    size: Dp,
    padding: Dp
) {
    Box(
        modifier = Modifier
            .padding(all = padding)
            .background(
                color = Color.LightGray,
                shape = CircleShape
            )
            .size(size)
            .shimmerLoadingAnimation(
                isEnableShimmer = isEnableShimmer,
                isLightModeActive = isLightModeActive,
            )
    )
}

@Composable
fun ShimmerSquare(
    isEnableShimmer: Boolean,
    isLightModeActive: Boolean = false,
    size: Dp,
    round: Dp,
    padding: Dp
) {
    Box(
        modifier = Modifier
            .padding(all = padding)
            .clip(shape = RoundedCornerShape(round))
            .background(color = Color.LightGray)
            .size(size)
            .shimmerLoadingAnimation(
                isEnableShimmer = isEnableShimmer,
                isLightModeActive = isLightModeActive,
            )
    )
}

@Composable
fun ShimmerRectangle(
    isEnableShimmer: Boolean,
    isLightModeActive: Boolean = false,
    width: Dp,
    height: Dp,
    round: Dp,
    padding: Dp
) {
    Box(
        modifier = Modifier
            .padding(all = padding)
            .clip(shape = RoundedCornerShape(round))
            .background(color = Color.LightGray)
            .width(width)
            .height(height)
            .shimmerLoadingAnimation(
                isEnableShimmer = isEnableShimmer,
                isLightModeActive = isLightModeActive,
            )
    )
}

@Preview
@Composable
private fun ShimmerCirclePreview() = ShimmerCircle(
    isEnableShimmer = true,
    size = 100.dp,
    padding = 10.dp
)

@Preview
@Composable
private fun ShimmerSquarePreview() = ShimmerSquare(
    isEnableShimmer = true,
    size = 100.dp,
    round = 25.dp,
    padding = 10.dp
)

@Preview
@Composable
private fun ShimmerRectanglePreview() = ShimmerRectangle(
    isEnableShimmer = true,
    width = 200.dp,
    height = 100.dp,
    round = 25.dp,
    padding = 10.dp
)
