package com.example.marvelheroesapp.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.marvelheroesapp.R
import com.example.marvelheroesapp.models.HeroModel.Companion.mockHeroList
import com.example.marvelheroesapp.ui.theme.MarvelHeroesAppTheme
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselCardsComponent(navController: NavHostController) {

    val state = rememberLazyListState()
    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    val backgroundColors = remember {
        listOf(
            R.drawable.first_state,
            R.drawable.second_state,
            R.drawable.third_state,
        )
    }

    val centerItemIndex by remember {
        derivedStateOf {
            val layoutInfo = state.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2

            visibleItems.minByOrNull { abs((it.offset + it.size / 2) - viewportCenter) }?.index ?: 0
        }
    }

    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = backgroundColors[centerItemIndex]),
            contentDescription = "Background color",
            contentScale = ContentScale.Crop,
        )
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(horizontal = 48.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
            state = state,
            flingBehavior = flingBehavior,
        ) {
            items(count = mockHeroList.size) { index ->
                HeroCardComponent(
                    navController = navController,
                    heroModel = mockHeroList[index],
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MarvelHeroesAppTheme {
        CarouselCardsComponent(rememberNavController())
    }
}