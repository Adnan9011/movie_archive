package com.moviearchive.ui.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarHome(
    title: String
) {
    TopAppBar(
        title = { Text(text = title) },
        colors = topAppBarColors(
            containerColor = colorScheme.primary,
            titleContentColor = colorScheme.onPrimary,
            navigationIconContentColor = colorScheme.onPrimary,
            actionIconContentColor = colorScheme.onSecondary
        ),
        actions = {
            LikeAction(
            ) { isLiked ->

            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarDetail(
    title: String,
    onBackClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        colors = topAppBarColors(
            containerColor = colorScheme.primary,
            titleContentColor = colorScheme.onPrimary,
            navigationIconContentColor = colorScheme.onPrimary,
            actionIconContentColor = colorScheme.onSecondary
        ),
        navigationIcon = {
            IconButton(onClick = {
                onBackClicked.invoke()
            }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarHomePreview() = AppBarHome(title = "Home Title")

@Preview(showBackground = true)
@Composable
fun AppBaDetailPreview() = AppBarDetail(title = "Detailed Title", onBackClicked = {})