package com.nmullaney.doable.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import com.nmullaney.doable.R
import com.nmullaney.doable.ui.dashboard.DashboardScreen
import com.nmullaney.doable.ui.home.TodayScreen
import com.nmullaney.doable.ui.notifications.NotificationScreen
import com.nmullaney.doable.ui.util.IconResource

sealed class Screen(val route: String, @StringRes val resourceId: Int, val iconResource: IconResource, val content: @Composable () -> Unit) {
    data object Today : Screen("today", R.string.title_today, IconResource(resourceId = R.drawable.baseline_checklist_24), { TodayScreen() })
    data object Dashboard : Screen("dashboard", R.string.title_dashboard, IconResource(Icons.Filled.Build), { DashboardScreen() })
    data object Notifications : Screen("notifications", R.string.title_notifications, IconResource(Icons.Filled.Notifications),
        { NotificationScreen() })
}

val AllScreens = listOf(Screen.Today, Screen.Dashboard, Screen.Notifications)