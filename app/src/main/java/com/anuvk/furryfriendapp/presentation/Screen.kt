package com.anuvk.furryfriendapp.presentation

sealed class Screen(val route: String) {
    data object Main : Screen("breedList")
    data object Details : Screen("breedDetail/{breedName}")

}