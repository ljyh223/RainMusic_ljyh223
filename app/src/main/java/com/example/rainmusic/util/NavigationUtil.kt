package com.example.rainmusic.util

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

@ExperimentalAnimationApi
val defaultEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
    slideInHorizontally(
        initialOffsetX = {
            it
        },
        animationSpec = tween()
    )
}

@ExperimentalAnimationApi
val defaultExitTransition : (AnimatedContentScope.() -> ExitTransition) = {
    slideOutHorizontally(
        targetOffsetX = {
            -it
        },
        animationSpec = tween()
    ) + fadeOut(
        animationSpec = tween()
    )
}

@ExperimentalAnimationApi
val defaultPopEnterTransition : (AnimatedContentScope.() -> EnterTransition) = {
    slideInHorizontally(
        initialOffsetX = {
            -it
        },
        animationSpec = tween()
    )
}

@ExperimentalAnimationApi
val defaultPopExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
    slideOutHorizontally(
        targetOffsetX = {
            it
        },
        animationSpec = tween()
    )
}