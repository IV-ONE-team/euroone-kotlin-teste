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
 * Detalhe de uma turma sob responsabilidade do educador. Mostra
 * informacoes gerais, cronograma, presenca e engajamento agregados.
 */
@Composable
fun EducadorTurmaDetalheScreen(
    classId: String,
    educadorViewModel: EducadorViewModel,
    onBack: () -> Unit
) {
    val turma = remember(classId) { educadorViewModel.classById(classId) }

    Scaffold(
        topBar = { EuroTopBar(title = "Detalhes da turma", onBack = onBack) },
        containerColor = EuroPalette.Ink50
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            if (turma == null) {
                Text(
                    text = "Turma nao encontrada.",
                    modifier = Modifier.padding(20.dp),
                    color = EuroPalette.Ink500
                )
                return@Column
            }

            EuroCard(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = turma.className,
                            style = MaterialTheme.typography.headlineMedium,
                            color = EuroPalette.Brand800
                        )
                        Text(
                            text = turma.discipline,
                            style = MaterialTheme.typography.bodyMedium,
                            color = EuroPalette.Ink600
                        )
                    }
                    StatusChip(text = turma.status, accent = statusAccent(turma.status))
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Modulo em curso: ${turma.currentModule}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = EuroPalette.Ink700
                )
                Text(
                    text = "Cronograma: ${turma.schedule}",
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink500
                )
                Text(
                    text = "Local: ${turma.room}",
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink500
                )
                Text(
                    text = "Periodo: ${turma.startDate} a ${turma.endDate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink500
                )
            }

            SectionHeader(title = "Indicadores da turma")
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Stat(label = "Alunos", value = "${turma.students}", modifier = Modifier.weight(1f))
                Stat(label = "Presenca", value = "${turma.attendance}%", modifier = Modifier.weight(1f))
                Stat(label = "Engajamento", value = "${turma.engagement}%", modifier = Modifier.weight(1f))
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
