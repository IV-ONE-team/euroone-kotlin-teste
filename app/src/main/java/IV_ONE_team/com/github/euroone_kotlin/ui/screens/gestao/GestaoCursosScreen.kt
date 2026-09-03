package IV_ONE_team.com.github.euroone_kotlin.ui.screens.gestao

import IV_ONE_team.com.github.euroone_kotlin.model.ManagedCourse
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroTopBar
import IV_ONE_team.com.github.euroone_kotlin.ui.components.StatusChip
import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.GestaoViewModel
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
 * Listagem executiva de cursos sob gestao com destaque para cursos que
 * requerem atencao (badge de alerta).
 */
@Composable
fun GestaoCursosScreen(
    gestaoViewModel: GestaoViewModel,
    onBack: () -> Unit,
    onSelectCurso: (String) -> Unit
) {
    val state by gestaoViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { EuroTopBar(title = "Cursos", onBack = onBack) },
        containerColor = EuroPalette.Ink50
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.courses, key = { it.id }) { curso ->
                CourseCard(curso, onClick = { onSelectCurso(curso.id) })
            }
        }
    }
}

@Composable
private fun CourseCard(curso: ManagedCourse, onClick: () -> Unit) {
    EuroCard(modifier = Modifier.clickable { onClick() }) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = curso.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Ink900
                )
                Text(
                    text = "${curso.classes} - ${curso.educators}",
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink500
                )
                Text(
                    text = "Periodo: ${curso.startDate} a ${curso.endDate}",
                    style = MaterialTheme.typography.labelSmall,
                    color = EuroPalette.Ink500
                )
            }
            StatusChip(
                text = if (curso.alert) "Alerta" else "Ok",
                accent = if (curso.alert) EuroPalette.Attention else EuroPalette.Engaged
            )
        }
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Stat(label = "Alunos", value = curso.students.toString())
            Stat(label = "Engajamento", value = "${curso.engagement}%")
            Stat(label = "Conclusao", value = "${curso.completion}%")
            Stat(label = "Delta", value = curso.delta)
        }
    }
}

@Composable
private fun Stat(label: String, value: String) {
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
