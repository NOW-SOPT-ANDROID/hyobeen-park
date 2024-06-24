package com.sopt.now.compose.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.now.compose.presentation.ui.main.home.HomeScreen
import com.sopt.now.compose.presentation.ui.main.mypage.MypageScreen
import com.sopt.now.compose.R
import com.sopt.now.compose.presentation.ui.main.search.SearchScreen
import com.sopt.now.compose.theme.NOWSOPTAndroidTheme

class MainActivity : ComponentActivity() {
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var nickname: String
    private lateinit var mbti: String
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    initUserData()
                    MainScreen()
                }
            }
        }
    }

    data class BottomNavigationItem(
        val Icon: ImageVector,
        val label: String
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(showBackground = true)
    @Composable
    private fun MainScreen() {
        var selectedItem by remember { mutableIntStateOf(0) }
        val items = listOf(
            BottomNavigationItem(
                Icon = Icons.Filled.Home,
                label = "Home"
            ),
            BottomNavigationItem(
                Icon = Icons.Filled.Search,
                label = "Search"
            ),
            BottomNavigationItem(
                Icon = Icons.Filled.Person,
                label = "Mypage"
            )
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        Text(stringResource(id = R.string.app_name))
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(item.Icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 30.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                when (selectedItem) {
                    0 -> HomeScreen(userId)
                    1 -> SearchScreen()
                    2 -> MypageScreen(userId)
                }
            }
        }
    }


    @Composable
    fun initUserData() {
        intent.apply {
            userId = getStringExtra("userId") ?: "0"
        }
    }


}