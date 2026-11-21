package com.example.AcademicProgressRecord_version12

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
fun statusLabel(status: SubjectStatus): String = when (status) {
    SubjectStatus.SAFE -> "Безпечно"
    SubjectStatus.AT_RISK -> "Під загрозою"
    SubjectStatus.CRITICAL -> "Критично"
}
@Composable
fun SubjectListScreen(
    onSubjectClick: (Int) -> Unit,
    viewModel: ListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SubjectListContent(
        uiState = uiState,
        onQueryChange = viewModel::onQueryChange,
        onSubjectClick = onSubjectClick
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectListContent(
    uiState: ListUiState,
    onQueryChange: (String) -> Unit,
    onSubjectClick: (Int) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Журнал успішності") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(horizontal = 16.dp)) {
            OutlinedTextField(
                value = uiState.query,
                onValueChange = onQueryChange,
                label = { Text("Пошук предмета") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(uiState.visibleSubjects, key = { it.id }) { subject ->
                        SubjectCard(
                            subject = subject,
                            onClick = { onSubjectClick(subject.id) }
                        )
                    }
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
            Text(statusLabel(subject.status), style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubjectListContentPreview() {
    SubjectListContent(
        uiState = ListUiState(subjects = sampleSubjects),
        onQueryChange = {},
        onSubjectClick = {}
    )
}