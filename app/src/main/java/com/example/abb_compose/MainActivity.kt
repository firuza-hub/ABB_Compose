package com.example.abb_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.abb_compose.ui.theme.ABB_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ABB_ComposeTheme {
                MyList()

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ABB_ComposeTheme {
        MyList()
    }
}


@Composable
fun MyList() {
    SimpleColumn() {
        Text(text = "MY Custom Row", Modifier.padding(10.dp))
        Spacer(modifier = Modifier.height(20.dp))
        val list = listOf<String>("1", "2", "3", "4", "5", "6", "7", "8")
        SimpleRow() {
            list.forEach { i -> MyListItem(content = i) }

        }
    }

}


@Composable
fun MyListItem(content: String) {
    Card(
        modifier = Modifier
            .height(50.dp)
            .padding(5.dp),
        elevation = 6.dp
    ) {
        Text(
            modifier = Modifier.padding(6.dp),
            text = content, fontSize = 18.sp
        )
    }
}

@Composable
fun SimpleColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { m -> m.measure(constraints) }

        val height = placeables.sumOf { it.height }
        val width = constraints.maxWidth

        layout(width, height) {
            var y = 0
            for (placeable in placeables) {
                placeable.placeRelative(x = 0, y = y)
                y += placeable.height
            }
        }
    }
}


@Composable
fun SimpleRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { m -> m.measure(constraints) }

        val height = placeables.sumOf { it.height }
        val width = placeables.sumOf { it.width }

        layout(width, height) {
            var x = 0
            for (placeable in placeables) {
                placeable.placeRelative(x = x, y = 0)
                x += placeable.width
            }
        }
    }
}

