package IV_ONE_team.com.github.euroone_kotlin.ui.screens.educador

import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroTopBar
import IV_ONE_team.com.github.euroone_kotlin.ui.components.SectionHeader
import IV_ONE_team.com.github.euroone_kotlin.ui.components.StatusChip
import IV_ONE_team.com.github.euroone_kotlin.ui.components.statusAccent
import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.EducadorViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Detalhe do aluno visto pelo educador. Exibe presenca, engajamento,
 * pontuacao e a acao pedagogica sugerida pelo sistema.
 */
@Composable
fun EducadorAlunoDetalheScreen(
    studentId: String,
    educadorViewModel: EducadorViewModel,
    onBack: () -> Unit
) {
    val student = remember(studentId) { educadorViewModel.studentById(studentId) }

    Scaffold(
        topBar = { EuroTopBar(title = "Detalhes do aluno", onBack = onBack) },
        containerColor = EuroPalette.Ink50
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            if (student == null) {
                Text(
                    text = "Aluno nao encontrado.",
                    modifier = Modifier.padding(20.dp),
                    color = EuroPalette.Ink500
                )
                return@Column
            }

            EuroCard(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = student.name,
                            style = MaterialTheme.typography.headlineMedium,
                            color = EuroPalette.Brand800
                        )
                        Text(
                            text = "Matricula: ${student.registration}",
                            style = MaterialTheme.typography.bodySmall,
                            color = EuroPalette.Ink500
                        )
                        Text(
                            text = "${student.discipline} - ${student.className}",
                            style = MaterialTheme.typography.bodySmall,
                            color = EuroPalette.Ink600
                        )
                    }
                    StatusChip(text = student.status, accent = statusAccent(student.status))
                }
            }

            SectionHeader(title = "Indicadores")
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Stat(label = "Presenca", value = "${student.attendance}%", modifier = Modifier.weight(1f))
                Stat(label = "Engajamento", value = "${student.engagement}%", modifier = Modifier.weight(1f))
                Stat(label = "Pontos", value = "${student.points}", modifier = Modifier.weight(1f))
            }

            SectionHeader(title = "Acao pedagogica sugerida")
            EuroCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = student.recommendedAction,
                    style = MaterialTheme.typography.bodyMedium,
                    color = EuroPalette.Ink700
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "${student.pendingAssignments} atividade(s) pendente(s).",
                    style = MaterialTheme.typography.labelSmall,
                    color = EuroPalette.Ink500
                )
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun Stat(label: String, value: String, modifier: Modifier = Modifier) {
    EuroCard(modifier = modifier) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium,
            color = EuroPalette.Brand800
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = EuroPalette.Ink500
        )
    }
}
