package com.example.marvelheroesapp.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvelheroesapp.models.HeroModel.Companion.mockHeroList
import com.example.marvelheroesapp.ui.theme.BackgroundFirstCardColor
import com.example.marvelheroesapp.ui.theme.BackgroundSecondCardColor
import com.example.marvelheroesapp.ui.theme.BackgroundThirdCardColor
import com.example.marvelheroesapp.ui.theme.large48
import com.example.marvelheroesapp.ui.theme.small16
import kotlin.math.abs

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselCardsComponent(navigateToSecondScreen: (Int) -> Unit) {

    val state = rememberLazyListState()
    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    val backgroundColors = remember {
        listOf(
            BackgroundFirstCardColor,
            BackgroundSecondCardColor,
            BackgroundThirdCardColor,
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                val path = Path().apply {
                    moveTo(0f, size.height)
                    lineTo(size.width, size.height)
                    lineTo(size.width, size.height / 3f)
                    close()
                }
                drawPath(path, backgroundColors[centerItemIndex])
            }
    ) {
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(horizontal = large48),
            horizontalArrangement = Arrangement.spacedBy(space = small16),
            state = state,
            flingBehavior = flingBehavior,
        ) {
            items(count = mockHeroList.size) { index ->
                HeroCardComponent(
                    onClick = { heroId -> navigateToSecondScreen(heroId) },
                    heroModel = mockHeroList[index],
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CarouselCardsComponent(navigateToSecondScreen = {})
}
