package com.example.assignment3q1.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment3q1.ui.theme.Assignment3Q1Theme
import kotlinx.coroutines.launch

/**
 * Q1: Row/Column Mastery - Settings Screen
 *
 * Layout: Column as main container; each row = Row with left Column (label + supporting text)
 * and right control. Modifier.weight(1f) on left prevents truncation.
 * M3: TopAppBar, Card, Switch, Checkbox, Slider, HorizontalDivider, AssistChip, IconButton, Snackbar.
 * Modifiers: padding, fillMaxWidth, weight, heightIn, align, background, clickable.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }
    var brightness by remember { mutableFloatStateOf(0.7f) }
    var analyticsChecked by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                actions = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Theme options")
                            }
                        }
                    ) {
                        Icon(Icons.Default.Settings, contentDescription = "Theme")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            // Card section: Notifications
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Notifications",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.heightIn(min = 8.dp))
                    SettingRow(
                        label = "Push notifications",
                        supportingText = "Receive alerts and updates",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Switch(
                            checked = notificationsEnabled,
                            onCheckedChange = { notificationsEnabled = it }
                        )
                    }
                }
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )

            // Row: Dark mode (with weight + align)
            SettingRow(
                label = "Dark mode",
                supportingText = "Use dark theme",
                modifier = Modifier.fillMaxWidth()
            ) {
                Switch(
                    checked = darkModeEnabled,
                    onCheckedChange = { darkModeEnabled = it }
                )
            }

            Spacer(modifier = Modifier.heightIn(min = 8.dp))

            // Row: Brightness with Slider (heightIn on label column)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    .clickable { }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 48.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Brightness",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        "Screen brightness level",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Slider(
                    value = brightness,
                    onValueChange = { brightness = it },
                    modifier = Modifier
                        .weight(1f)
                        .sizeIn(minWidth = 120.dp)
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )

            // Row: Analytics checkbox
            SettingRow(
                label = "Share analytics",
                supportingText = "Help improve the app",
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = analyticsChecked,
                    onCheckedChange = { analyticsChecked = it }
                )
            }

            Spacer(modifier = Modifier.heightIn(min = 16.dp))

            // AssistChips row
            Text(
                "Quick actions",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AssistChip(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Cleared cache")
                        }
                    },
                    label = { Text("Clear cache") }
                )
                AssistChip(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Support opened")
                        }
                    },
                    label = { Text("Support") }
                )
            }
        }
    }
}

@Composable
private fun SettingRow(
    label: String,
    supportingText: String,
    modifier: Modifier = Modifier,
    control: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .padding(vertical = 8.dp)
            .heightIn(min = 56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = supportingText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Box(
            modifier = Modifier.align(Alignment.CenterVertically),
            contentAlignment = Alignment.CenterEnd
        ) {
            control()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    Assignment3Q1Theme {
        SettingsScreen()
    }
}
