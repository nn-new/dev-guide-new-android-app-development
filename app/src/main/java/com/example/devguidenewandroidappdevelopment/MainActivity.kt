package com.example.devguidenewandroidappdevelopment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    GreetingScreen()
}

@Composable
fun GreetingScreen(greetingViewModel: GreetingViewModel = viewModel()) {
    Greetings(
        items = greetingViewModel.greetingDetail,
        onCloseGreeting = { g -> greetingViewModel.remove(g) }
    )
}

@Composable
fun Greetings(
    items: List<GreetingDetail>,
    onCloseGreeting: (GreetingDetail) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = items, key = { item -> item.id }) { item ->
            Greeting(item = item, { onCloseGreeting(item) })
        }
    }
}

@Composable
private fun Greeting(item: GreetingDetail, onClose: () -> Unit) {

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