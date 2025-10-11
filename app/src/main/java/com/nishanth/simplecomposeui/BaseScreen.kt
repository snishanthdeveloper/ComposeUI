package com.nishanth.simplecomposeui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BaseScreen() {
    val navController = rememberAnimatedNavController()
    val currentBackStack = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack.value?.destination?.route

    // Bottom bar visible only on main screens
    val showBottomBar = currentRoute in listOf(Screen.Home.route, Screen.Search.route, Screen.Profile.route)

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = fadeIn(tween(400)),
                exit = fadeOut(tween(400))
            ) {
                AnimatedBottomBar(navController)
            }
        }
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(tween(400)) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(tween(300)) }
            ) { HomeScreen(navController) }

            composable(Screen.Search.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(tween(400)) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(tween(300)) }
            ) { SearchScreen() }

            composable(Screen.Profile.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(tween(400)) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(tween(300)) }
            ) { ProfileScreen() }

            // Detail screen without bottom bar
            composable(Screen.Detail.route,
                enterTransition = { fadeIn(tween(400)) + scaleIn(initialScale = 0.8f) },
                exitTransition = { fadeOut(tween(400)) + scaleOut(targetScale = 1.2f) }
            ) {
                DetailScreen(navController)
            }
        }
    }
}
