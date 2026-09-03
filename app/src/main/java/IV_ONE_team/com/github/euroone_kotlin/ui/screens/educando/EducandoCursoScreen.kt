package IV_ONE_team.com.github.euroone_kotlin.ui.screens.educando

import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroTopBar
import IV_ONE_team.com.github.euroone_kotlin.ui.components.SectionHeader
import IV_ONE_team.com.github.euroone_kotlin.ui.components.StatusChip
import IV_ONE_team.com.github.euroone_kotlin.ui.components.statusAccent
import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.EducandoViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Tela de detalhe de um curso do educando. Recebe o `courseId` via
 * argumento de navegacao e busca o curso correspondente no ViewModel.
 */
@Composable
fun EducandoCursoScreen(
    courseId: String,
    educandoViewModel: EducandoViewModel,
    onBack: () -> Unit
) {
    val course = remember(courseId) { educandoViewModel.courseById(courseId) }

    Scaffold(
        topBar = { EuroTopBar(title = "Curso", onBack = onBack) },
        containerColor = EuroPalette.Ink50
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            if (course == null) {
                SectionHeader(title = "Curso nao encontrado")
                Text(
                    text = "Nao encontramos os dados deste curso nos mocks.",
                    modifier = Modifier.padding(20.dp),
                    color = EuroPalette.Ink500
                )
                return@Column
            }

            EuroCard(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.padding(end = 12.dp)) {
                        Text(
                            text = course.name,
                            style = MaterialTheme.typography.headlineMedium,
                            color = EuroPalette.Brand800
                        )
                        Text(
                            text = "Prof. ${course.teacher}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = EuroPalette.Ink600
                        )
                        Text(
                            text = course.className,
                            style = MaterialTheme.typography.bodySmall,
                            color = EuroPalette.Ink500
                        )
                    }
                    StatusChip(course.status, statusAccent(course.status))
                }
                Spacer(Modifier.height(16.dp))
                LinearProgressIndicator(
                    progress = { course.progress / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .background(EuroPalette.Ink100, RoundedCornerShape(6.dp)),
                    color = EuroPalette.Brand500,
                    trackColor = EuroPalette.Ink100
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "${course.progress}% concluido",
                    style = MaterialTheme.typography.labelLarge,
                    color = EuroPalette.Brand700
                )
            }

            SectionHeader(title = "Modulo atual")
            EuroCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = course.currentModule,
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Ink900
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Proxima aula: ${course.nextLesson}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = EuroPalette.Ink600
                )
            }

            SectionHeader(title = "Numeros do curso")
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatBox(
                    label = "Aulas assistidas",
                    value = "${course.watchedLessons}/${course.totalLessons}",
                    modifier = Modifier.weight(1f)
                )
                StatBox(
                    label = "Atividades concluidas",
                    value = "${course.completedAssignments}",
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatBox(
                    label = "Pendentes",
                    value = "${course.pendingAssignments}",
                    modifier = Modifier.weight(1f)
                )
                StatBox(
                    label = "Total de aulas",
                    value = "${course.totalLessons}",
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun StatBox(label: String, value: String, modifier: Modifier = Modifier) {
    EuroCard(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = EuroPalette.Ink500
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium,
            color = EuroPalette.Brand800
        )
    }
}
