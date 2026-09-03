package IV_ONE_team.com.github.euroone_kotlin.ui.screens.educador

import IV_ONE_team.com.github.euroone_kotlin.model.EducatorClassInfo
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
 * Lista completa de turmas ministradas pelo educador. Toque em qualquer
 * turma para visualizar o detalhe.
 */
@Composable
fun EducadorTurmasScreen(
    educadorViewModel: EducadorViewModel,
    onBack: () -> Unit,
    onSelectTurma: (String) -> Unit
) {
    val state by educadorViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { EuroTopBar(title = "Minhas turmas", onBack = onBack) },
        containerColor = EuroPalette.Ink50
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.classes, key = { it.id }) { turma ->
                TurmaCard(turma, onClick = { onSelectTurma(turma.id) })
            }
        }
    }
}

@Composable
private fun TurmaCard(turma: EducatorClassInfo, onClick: () -> Unit) {
    EuroCard(modifier = Modifier.clickable { onClick() }) {
        Row(modifier = Modifier.padding(bottom = 4.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = turma.className,
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Ink900
                )
                Text(
                    text = turma.discipline,
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink500
                )
            }
            StatusChip(text = turma.status, accent = statusAccent(turma.status))
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = turma.currentModule,
            style = MaterialTheme.typography.bodyMedium,
            color = EuroPalette.Ink700
        )
        Text(
            text = "${turma.schedule} - ${turma.room}",
            style = MaterialTheme.typography.bodySmall,
            color = EuroPalette.Ink500
        )
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            InfoInline(label = "Alunos", value = "${turma.students}")
            InfoInline(label = "Presenca", value = "${turma.attendance}%")
            InfoInline(label = "Engajamento", value = "${turma.engagement}%")
        }
    }
}

@Composable
private fun InfoInline(label: String, value: String) {
    Column {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = EuroPalette.Brand800
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = EuroPalette.Ink500
        )
    }
}
