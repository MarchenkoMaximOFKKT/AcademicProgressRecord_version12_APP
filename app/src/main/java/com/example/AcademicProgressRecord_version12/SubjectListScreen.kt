package com.example.AcademicProgressRecord_version12

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectListScreen() {
    val subjects = listOf(
        Subject(1, "Мобільна розробка", targetScore = 90, currentScore = 41.6, attendancePercent = 92, status = SubjectStatus.AT_RISK),
        Subject(2, "Бази даних", targetScore = 80, currentScore = 62.0, attendancePercent = 85, status = SubjectStatus.SAFE),
        Subject(3, "Алгоритми", targetScore = 75, currentScore = 35.0, attendancePercent = 60, status = SubjectStatus.CRITICAL),
        Subject(4, "Операційні системи", targetScore = 85, currentScore = 88.0, attendancePercent = 100, status = SubjectStatus.SAFE),
        Subject(5, "Мережі", targetScore = 80, currentScore = 64.0, attendancePercent = 78, status = SubjectStatus.AT_RISK),
        Subject(6, "Основи Програмної Інженерії", targetScore = 90, currentScore = 91.0, attendancePercent = 95, status = SubjectStatus.SAFE),
        Subject(7, "Python", targetScore = 70, currentScore = 55.0, attendancePercent = 88, status = SubjectStatus.SAFE),
        Subject(8, "ООП", targetScore = 75, currentScore = 30.0, attendancePercent = 70, status = SubjectStatus.CRITICAL)
    )

    var query by remember { mutableStateOf("") }
    val filtered = subjects.filter { it.name.contains(query, ignoreCase = true) }
    val context = LocalContext.current

    Scaffold(
        topBar = { TopAppBar(title = { Text("Журнал успішності") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(horizontal = 16.dp)) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Пошук предмета") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(filtered, key = { it.id }) { subject ->
                    SubjectCard(
                        subject = subject,
                        onClick = {
                            Toast.makeText(context, "Відкрити: ${subject.name}", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}
@Composable
fun SubjectCard(subject: Subject, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable { onClick() }) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(subject.name, style = MaterialTheme.typography.titleMedium)
                Text("Бал: ${"%.1f".format(subject.currentScore)} / ціль ${subject.targetScore}")
                Text("Відвідуваність: ${subject.attendancePercent}%")
            }
            val label = when (subject.status) {
                SubjectStatus.SAFE -> "Безпечно"
                SubjectStatus.AT_RISK -> "Під загрозою"
                SubjectStatus.CRITICAL -> "Критично"
            }
            Text(label, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubjectListScreenPreview() {
    SubjectListScreen()
}