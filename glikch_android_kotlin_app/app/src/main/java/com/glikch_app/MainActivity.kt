// Glikch Innovations eCommerce, Technical Services, and Course Modules Application
// Joel De Alba - B.S.C.S Full Stack Software Engineer
// Date: 03 / 28 / 2025

// Classify Main App Package
package com.glikch_app

// Android Component Imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

// Foundation, Material, Runtime Imports
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

// Compose UI Imports
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

// Scroll Animation - Info Board
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.*
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.graphics.graphicsLayer

// UI Theme Implementation
import com.glikch_app.ui.theme.GLIKCH_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GLIKCH_AppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf("Products") }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                selected = selectedTab,
                onSelectedChange = { selectedTab = it }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Header()
            MessageCard()
            when (selectedTab) {
                "Products" -> ProductGrid()
                "Services" -> ServicesScreen()
                "Courses" -> CoursesScreen()
                "About" -> AboutScreen()
            }
        }
    }
}

@Composable
fun Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("GLIKCH Innovations LLC.", style = MaterialTheme.typography.headlineSmall)
        Text("gktech.help@outlook.com • (828) 208-5406", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun ScrollingTextLine(
    text: String,
    delayMillis: Int = 0
) {
    val scrollWidth = 2000f
    val infiniteTransition = rememberInfiniteTransition()
    val offsetX by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = -1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 10000,
                delayMillis = delayMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(28.dp)
            .graphicsLayer { clip = true }
    ) {
        Text(
            text = "   |   $text   ", // ← trailing whitespace for spacing
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .offset { IntOffset((offsetX * scrollWidth).toInt(), 0) }
        )
    }
}

@Composable
fun MessageCard() {
    val messages = listOf(
        "This is a Test Marquee - Testing Text Length  |",
        "New Flippers in Stock - Learn More 🔧  |",
        "GLIKCH Innovations is now hiring remote interns 💼  |"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp) // Taller to fit multiple lines
            .padding(horizontal = 12.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            messages.forEachIndexed { index, message ->
                ScrollingTextLine(
                    text = message,
                    delayMillis = index * 2500 // Delay each line by 3 seconds more than the last
                )
            }
        }
    }
}

@Composable
fun ProductGrid() {
    val products = List(12) { "Product ${it + 1}" }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        items(products.size) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp), // More height for stacked content
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    // 🔷 Image Placeholder Box (color-coded)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .background(Color(
                                android.graphics.Color.HSVToColor(
                                    floatArrayOf((index * 30f) % 360, 0.7f, 0.9f)
                                )
                            ))
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // 🧾 Product Name
                    Text(
                        text = "Product Name",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    // 📄 Description
                    Text(
                        text = "Short Item Description",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // 💰 Price Tag (bottom-aligned)
                    Text(
                        text = "Product Price",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(selected: String, onSelectedChange: (String) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = selected == "Products",
            onClick = { onSelectedChange("Products") },
            icon = { Icon(Icons.Default.Store, contentDescription = "Products") },
            label = { Text("Products") }
        )
        NavigationBarItem(
            selected = selected == "Services",
            onClick = { onSelectedChange("Services") },
            icon = { Icon(Icons.Default.Build, contentDescription = "Services") },
            label = { Text("Services") }
        )
        NavigationBarItem(
            selected = selected == "Courses",
            onClick = { onSelectedChange("Courses") },
            icon = { Icon(Icons.Default.School, contentDescription = "Courses") },
            label = { Text("Courses") }
        )
        NavigationBarItem(
            selected = selected == "About",
            onClick = { onSelectedChange("About") },
            icon = { Icon(Icons.Default.Info, contentDescription = "About") },
            label = { Text("About") }
        )
    }
}

@Composable
fun ServicesScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Services Coming Soon")
    }
}

@Composable
fun CoursesScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Courses Coming Soon")
    }
}

@Composable
fun AboutScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("About GLIKCH Coming Soon")
    }
}
