package com.example.devguidenewandroidappdevelopment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.devguidenewandroidappdevelopment.ui.theme.DevGuideNewAndroidAppDevelopmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevGuideNewAndroidAppDevelopmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "greeting_screen") {
        composable("greeting_screen") {
            GreetingScreen(navController = navController)
        }
        composable(
            "greeting_detail_screen/{userName}",
            arguments = listOf(navArgument("userName") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            GreetingDetailScreen(
                userName = navBackStackEntry.arguments?.getString("userName") ?: "",
                navController = navController
            )
        }
    }
}

@Composable
fun GreetingScreen(
    greetingViewModel: GreetingViewModel = viewModel(),
    navController: NavController?
) {
    Greetings(
        items = greetingViewModel.greetingDetail,
        onSeeMore = { g -> navController?.navigate("greeting_detail_screen/${g.name}") },
        onCloseGreeting = { g -> greetingViewModel.remove(g) }
    )
}

@Composable
fun GreetingDetailScreen(userName: String, navController: NavController?) {
    Scaffold(topBar = {
        GreetingDetailTopBar(navigationAction = {navController?.navigateUp()})
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_picture),
                    contentDescription = "Profile Image"
                )
                Text(
                    text = userName,
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }
}

@Composable
fun GreetingDetailTopBar(navigationAction: () -> Unit) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "content description",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = navigationAction)
            )
        },
        title = {
            Text(
                text = "Dev Guide Jetpack Compose",
                style = MaterialTheme.typography.h6,
                maxLines = 1,
            )
        },
    )
}

@Composable
fun Greetings(
    items: List<GreetingDetail>,
    onSeeMore: (GreetingDetail) -> Unit,
    onCloseGreeting: (GreetingDetail) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = items, key = { item -> item.id }) { item ->
            Greeting(item = item, { onSeeMore(item) }, { onCloseGreeting(item) })
        }
    }
}

@Composable
private fun Greeting(item: GreetingDetail, onSeeMore: () -> Unit, onClose: () -> Unit) {

    val expanded = rememberSaveable { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = "Hello, ")
                Text(text = item.name)
                if (expanded.value) {
                    Text(
                        "Composem ipsum color sit lazy, padding theme elit, sed do bouncy.".repeat(
                            4
                        )
                    )
                    OutlinedButton(
                        onClick = onSeeMore,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text("See more detail")
                    }
                }
            }
            OutlinedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
            IconButton(onClick = onClose) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")

            }
        }
    }
}

class GreetingDetail(val id: Int, val name: String, var expanded: Boolean = false)

private fun getGreetingDetail() = List(1000) { GreetingDetail(it, "#$it") }

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DevGuideNewAndroidAppDevelopmentTheme {
        MyApp()
    }
}