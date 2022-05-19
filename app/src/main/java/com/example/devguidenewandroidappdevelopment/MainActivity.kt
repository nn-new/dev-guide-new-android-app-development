package com.example.devguidenewandroidappdevelopment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun PreviewExampleOrderModifier() {
    ExampleOrderModifier()
}

@Composable
fun ExampleOrderModifier() {
    val shape = CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
    Surface {
        Text(
            text = "Hello world!",
            style = TextStyle(
                color = Color.Green,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* Click Event */ }
                .padding(16.dp)
                .border(2.dp, MaterialTheme.colors.secondary, shape)
                .padding(8.dp)
                .background(MaterialTheme.colors.primary, shape)
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExampleBasicLayout() {
    ExampleBasicLayout()
}

@Composable
fun ExampleBasicLayout() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Column Text 1")
            Text("Column Text 2")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Row Text 1")
                Text(text = "Row Text 2")
            }
        }
        Text(
            "Stack Text",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp, top = 16.dp)
        )
        Text(
            "Stack Text",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExampleCOnstaintLayout() {
    ExampleConstaintLayout()
}

@Composable
fun ExampleConstaintLayout() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (text1Ref, edit1Ref, btn1Ref, btn2Ref) = createRefs()
        Text("Name", modifier = Modifier.constrainAs(text1Ref) {
            top.linkTo(parent.top)
            centerHorizontallyTo(parent)
        })
        TextField(
            value = "Name",
            onValueChange = {},
            modifier = Modifier
                .padding(top = 8.dp)
                .background(color = Color.LightGray)
                .constrainAs(edit1Ref) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(text1Ref.bottom)
                })
        Button(onClick = {}, modifier = Modifier
            .padding(top = 8.dp)
            .constrainAs(btn1Ref) {
                end.linkTo(edit1Ref.end)
                top.linkTo(edit1Ref.bottom)
            },
            content = { Text(text = "OK") }
        )
        TextButton(
            onClick = {}, modifier = Modifier
                .padding(end = 8.dp)
                .constrainAs(btn2Ref) {
                    end.linkTo(btn1Ref.start)
                    baseline.linkTo(btn1Ref.baseline)
                },
            content = { Text(text = "Cancel") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DevGuideNewAndroidAppDevelopmentTheme {
        Greeting("Android")
    }
}