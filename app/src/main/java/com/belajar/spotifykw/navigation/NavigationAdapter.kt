package com.belajar.spotifykw.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import com.belajar.spotifykw.home.HomeLayout
import com.belajar.spotifykw.routes.Routes
import com.belajar.spotifykw.startedscreen.ChoseModeLayout
import com.belajar.spotifykw.startedscreen.GetStartedLayout
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationAdapter() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(navController = navController, startDestination = Routes.GetStarted.routes) {
        composable(Routes.GetStarted.routes,
            enterTransition = {
                fadeIn(tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, tween(700))
            }) {
            GetStartedLayout(navController)
        }

        composable(Routes.ChooseMode.routes,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Right, tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, tween(700))
            }) {
            ChoseModeLayout(navController)
        }

        composable(Routes.Home.routes,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Up, tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, tween(700))
            }) {
            HomeLayout()
        }
    }
}