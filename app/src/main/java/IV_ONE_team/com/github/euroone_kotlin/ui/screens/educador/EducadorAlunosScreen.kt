package IV_ONE_team.com.github.euroone_kotlin.ui.screens.educador

import IV_ONE_team.com.github.euroone_kotlin.model.StudentListItem
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroTopBar
import IV_ONE_team.com.github.euroone_kotlin.ui.components.StatusChip
import IV_ONE_team.com.github.euroone_kotlin.ui.components.statusAccent
import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.EducadorViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Lista de alunos sob tutoria do educador, ordenada pela criticidade do
 * status. Toque para abrir o detalhe individual.
 */
@Composable
fun EducadorAlunosScreen(
    educadorViewModel: EducadorViewModel,
    onBack: () -> Unit,
    onSelectAluno: (String) -> Unit
) {
    val state by educadorViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { EuroTopBar(title = "Meus alunos", onBack = onBack) },
        containerColor = EuroPalette.Ink50
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.students, key = { it.id }) { student ->
                StudentCard(student, onClick = { onSelectAluno(student.id) })
            }
        }
    }
}

@Composable
private fun StudentCard(student: StudentListItem, onClick: () -> Unit) {
    EuroCard(modifier = Modifier.clickable { onClick() }) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = student.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Ink900
                )
                Text(
                    text = "${student.registration} - ${student.discipline}",
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink500
                )
            }
            StatusChip(text = student.status, accent = statusAccent(student.status))
        }
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column {
                Text(
                    text = "${student.attendance}%",
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Brand800
                )
                Text(
                    text = "Presenca",
                    style = MaterialTheme.typography.labelSmall,
                    color = EuroPalette.Ink500
                )
            }
            Column {
                Text(
                    text = "${student.engagement}%",
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Brand800
                )
                Text(
                    text = "Engajamento",
                    style = MaterialTheme.typography.labelSmall,
                    color = EuroPalette.Ink500
                )
            }
            Column {
                Text(
                    text = student.pendingAssignments.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Attention
                )
                Text(
                    text = "Pendentes",
                    style = MaterialTheme.typography.labelSmall,
                    color = EuroPalette.Ink500
                )
            }
        }
    }
}
