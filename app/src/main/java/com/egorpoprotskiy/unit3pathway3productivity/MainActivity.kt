package com.egorpoprotskiy.unit3pathway3productivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
//    ProductivityCard(productivity = Datasource().loadProductivity())
    ProductivityList(Datasource().loadProductivity())
}

//Создание макета одной карточки
@Composable
fun ProductivityCard(productivity: Productivity, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(productivity.iconId), null,
            )
            Column {
                Text(
                    text = LocalContext.current.getString(productivity.dayHead),
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = LocalContext.current.getString(productivity.day),
                    style = MaterialTheme.typography.labelSmall
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Unit3Pathway3ProductivityTheme {
        ProductivityCard(Productivity(R.drawable.baseline_sports_esports_24, R.string.day1_head, R.string.day1))
    }
}