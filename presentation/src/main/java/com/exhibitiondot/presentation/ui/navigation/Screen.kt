package com.exhibitiondot.presentation.ui.navigation

sealed class Screen(val route: String) {
    data object SignIn : Screen("sign-in")
    data object SignUp : Screen("sign-up")
    data object Home : Screen("home")
    data object EventDetail : Screen("event-detail")
    data object MyPage : Screen("my-page")
}

const val KEY_SIGN_UP_EMAIL = "key-email"
const val KEY_EVENT_ID = "key-event-id"