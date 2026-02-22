package com.example.assignment3q1.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment3q1.ui.theme.Assignment3Q1Theme

/**
 * Q1: Row/Column Mastery - Settings Screen
 *
 * Requirements:
 * - Column as main layout; each setting row = Row with (Column label + control).
 * - Use Modifier.weight() to prevent truncation.
 * - Material 3: at least 6 of TopAppBar, Card, ListItem, Switch, Checkbox, Slider, Divider,
 *   AssistChip, IconButton, Snackbar.
 * - Modifiers: padding, fillMaxWidth, weight, heightIn/sizeIn, align, and one of border/clip/background/clickable.
 */
@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                "Build your settings rows here (Row + Column + weight + M3 controls).",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    Assignment3Q1Theme { SettingsScreen() }
}
