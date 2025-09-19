package com.aussie.aussieexperts.core.base

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.aussie.aussieexperts.data.BottomNavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScaffold(
    title: String = "",
    showBack: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    floatingActionButton: @Composable (() -> Unit) = {},
    content: @Composable (PaddingValues) -> Unit,
    bottomNavItems: List<BottomNavItem> = emptyList(),
    selectedRoute: String? = null,
    onBottomNavClick: (BottomNavItem) -> Unit = {},
) {
    Scaffold(

        topBar = {
            if (title.isNotEmpty()) {
                TopAppBar(
                    scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
                    title = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black)
                        )
                    },
                    navigationIcon = if (showBack && onBackClick != null) {
                        {
                            IconButton(onClick = onBackClick) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        }
                    } else {
                        {}
                    },
                    actions = actions
                )
            }
        },

        floatingActionButton = floatingActionButton,
        bottomBar = {
            if (bottomNavItems.isNotEmpty()) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            selected = selectedRoute == item.route,
                            onClick = { onBottomNavClick(item) },
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) }
                        )
                    }
                }
            }
        },
        content = content
    )
}