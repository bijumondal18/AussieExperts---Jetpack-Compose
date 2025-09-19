package com.aussie.aussieexperts.presentation.pages.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.aussie.aussieexperts.core.base.BaseScaffold
import com.aussie.aussieexperts.data.BottomNavItem
import com.aussie.aussieexperts.presentation.pages.auth.profile.ProfileScreen
import com.aussie.aussieexperts.presentation.pages.chat.ChatScreen
import com.aussie.aussieexperts.presentation.pages.home.HomeScreen


@Composable
fun MainBottomNav(modifier: Modifier = Modifier) {

    val navItems = listOf(
        BottomNavItem("home", "Home", Icons.Default.Home),
        BottomNavItem("chat", "Chat", Icons.Default.Message),
        BottomNavItem("profile", "Profile", Icons.Default.Person),
    )

    var selected by remember { mutableStateOf("home") }


    BaseScaffold(
        title = "",
        bottomNavItems = navItems,
        selectedRoute = selected,
        onBottomNavClick = { item -> selected = item.route },
        showBack = false,
        actions = {},
        floatingActionButton = {},
        content = {
            when (selected) {
                "home" -> HomeScreen()
                "chat" -> ChatScreen()
                "profile" -> ProfileScreen()
            }
        },
    )

}