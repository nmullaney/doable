package com.nmullaney.doable.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.nmullaney.doable.R
import com.nmullaney.doable.ui.dashboard.DashboardScreen
import com.nmullaney.doable.ui.home.HomeScreen
import com.nmullaney.doable.ui.notifications.NotificationScreen

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector, val content: @Composable () -> Unit) {
    data object Home : Screen("home", R.string.title_home, Icons.Filled.Home, { HomeScreen() })
    data object Dashboard : Screen("dashboard", R.string.title_dashboard, Icons.Filled.Build, { DashboardScreen() })
    data object Notifications : Screen("notifications", R.string.title_notifications, Icons.Filled.Notifications,
        { NotificationScreen() })
}

val AllScreens = listOf(Screen.Home, Screen.Dashboard, Screen.Notifications)