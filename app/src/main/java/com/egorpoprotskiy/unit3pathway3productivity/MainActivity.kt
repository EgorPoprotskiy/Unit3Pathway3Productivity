package com.egorpoprotskiy.unit3pathway3productivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.egorpoprotskiy.unit3pathway3productivity.data.Datasource
import com.egorpoprotskiy.unit3pathway3productivity.model.Productivity
import com.egorpoprotskiy.unit3pathway3productivity.ui.theme.Unit3Pathway3ProductivityTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit3Pathway3ProductivityTheme {
                Surface {
                    ProductivityApp(
                    )
                }
            }
        }
    }
}

@Composable
fun ProductivityApp() {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection)
            )
    ) {
//    ProductivityCard(productivity = Datasource().loadProductivity())
        ProductivityList(productivityList = Datasource().loadProductivity())
    }
}

//Создание макета одной карточки
@Composable
fun ProductivityCard(productivity: Productivity, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(productivity.iconId), null,
                modifier = Modifier
//                    .size(50.dp)
                    .width(50.dp)
                    .height(80.dp)
                    //выравнивает по центру
                    .wrapContentSize(Alignment.Center)
                    //Делает круглыми иконки
                    .clip(MaterialTheme.shapes.small),
                //Растягивет иконку под всю область(под весь круг)
                contentScale = ContentScale.Crop,
            )
            Column {
                Text(
                    text = LocalContext.current.getString(productivity.dayHead),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp),
                    fontSize = 20.sp
                )
                Text(
                    text = LocalContext.current.getString(productivity.day),
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .padding(10.dp),
                    fontSize = 15.sp
                )
            }
        }
    }
}

//Создается список из нескольких карточек(LazyColumn - это аналог recyclerView)
@Composable
fun ProductivityList(productivityList: List<Productivity>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = Modifier) {
        items(productivityList) {productivity ->
            ProductivityCard(
                productivity = productivity,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    Unit3Pathway3ProductivityTheme(darkTheme = false) {
        ProductivityCard(Productivity(R.drawable.baseline_sports_esports_24, R.string.day1_head, R.string.day1))
    }
}

@Preview
@Composable
fun GreetingPreviewDarkTheme() {
    Unit3Pathway3ProductivityTheme(darkTheme = true) {
        ProductivityCard(Productivity(R.drawable.baseline_sports_esports_24, R.string.day1_head, R.string.day1))
    }
}