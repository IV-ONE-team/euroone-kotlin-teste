package IV_ONE_team.com.github.euroone_kotlin.ui.screens.gestao

import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroTopBar
import IV_ONE_team.com.github.euroone_kotlin.ui.components.SectionHeader
import IV_ONE_team.com.github.euroone_kotlin.ui.components.StatusChip
import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.GestaoViewModel
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
 * Detalhamento executivo de um curso especifico.
 */
@Composable
fun GestaoCursoDetalheScreen(
    courseId: String,
    gestaoViewModel: GestaoViewModel,
    onBack: () -> Unit
) {
    val curso = remember(courseId) { gestaoViewModel.courseById(courseId) }

    Scaffold(
        topBar = { EuroTopBar(title = "Detalhes do curso", onBack = onBack) },
        containerColor = EuroPalette.Ink50
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            if (curso == null) {
                Text(
                    text = "Curso nao encontrado.",
                    modifier = Modifier.padding(20.dp),
                    color = EuroPalette.Ink500
                )
                return@Column
            }

            EuroCard(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = curso.name,
                            style = MaterialTheme.typography.headlineMedium,
                            color = EuroPalette.Brand800
                        )
                        Text(
                            text = "${curso.classes} - ${curso.educators}",
                            style = MaterialTheme.typography.bodySmall,
                            color = EuroPalette.Ink600
                        )
                        Text(
                            text = "Periodo: ${curso.startDate} a ${curso.endDate}",
                            style = MaterialTheme.typography.bodySmall,
                            color = EuroPalette.Ink500
                        )
                    }
                    StatusChip(
                        text = if (curso.alert) "Alerta" else "Ok",
                        accent = if (curso.alert) EuroPalette.Attention else EuroPalette.Engaged
                    )
                }
            }

            SectionHeader(title = "Indicadores")
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Metric(label = "Alunos", value = "${curso.students}", modifier = Modifier.weight(1f))
                Metric(label = "Engajamento", value = "${curso.engagement}%", modifier = Modifier.weight(1f))
                Metric(label = "Conclusao", value = "${curso.completion}%", modifier = Modifier.weight(1f))
            }
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Metric(label = "Delta trimestre", value = curso.delta, modifier = Modifier.weight(1f))
                Metric(
                    label = "Status",
                    value = if (curso.alert) "Atencao" else "Saudavel",
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun Metric(label: String, value: String, modifier: Modifier = Modifier) {
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
