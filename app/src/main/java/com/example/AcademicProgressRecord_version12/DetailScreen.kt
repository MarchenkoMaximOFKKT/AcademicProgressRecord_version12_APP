package com.example.AcademicProgressRecord_version12

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(subject: Subject, onBack: () -> Unit) {
    val subjectComponents = sampleComponents.filter { it.subjectId == subject.id }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(subject.name) },
                navigationIcon = { TextButton(onClick = onBack) { Text("Назад") } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { SummaryCard(subject, subjectComponents) }
            item { Text("Компоненти оцінювання", style = MaterialTheme.typography.titleMedium) }
            if (subjectComponents.isEmpty()) {
                item { Text("Компоненти ще не додані") }
            } else {
                items(subjectComponents, key = { it.id }) { component ->
                    ComponentCard(component)
                }
            }
        }
    }
}
@Composable
fun SummaryCard(subject: Subject, components: List<GradeComponent>) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text("Статус: ${statusLabel(subject.status)}", style = MaterialTheme.typography.titleMedium)
            Text("Бал: ${"%.1f".format(subject.currentScore)} / ціль ${subject.targetScore}")
            if (components.isNotEmpty()) {
                val maxPossible = subject.currentScore + components.filter { it.score == null }.sumOf { it.weight }
                Text("Максимум можливий: ${"%.1f".format(maxPossible)}")
            }
            Text("Відвідуваність: ${subject.attendancePercent}% (мінімум ${subject.minAttendancePercent}%)")
        }
    }
}

@Composable
fun ComponentCard(component: GradeComponent) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(component.name, style = MaterialTheme.typography.titleSmall)
                Text("Вага: ${"%.0f".format(component.weight)}%")
            }
            Text(component.score?.let { "%.0f / %.0f".format(it, component.maxScore) } ?: "ще не здано")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(subject = sampleSubjects[0], onBack = {})
}