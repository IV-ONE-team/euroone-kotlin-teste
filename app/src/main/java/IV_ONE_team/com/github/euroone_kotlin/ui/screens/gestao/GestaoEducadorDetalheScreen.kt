package IV_ONE_team.com.github.euroone_kotlin.ui.screens.gestao

import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroTopBar
import IV_ONE_team.com.github.euroone_kotlin.ui.components.SectionHeader
import IV_ONE_team.com.github.euroone_kotlin.ui.components.StatusChip
import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.GestaoViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Detalhe de um educador (visao gestao).
 */
@Composable
fun GestaoEducadorDetalheScreen(
    educatorId: String,
    gestaoViewModel: GestaoViewModel,
    onBack: () -> Unit
) {
    val educator = remember(educatorId) { gestaoViewModel.educatorById(educatorId) }

    Scaffold(
        topBar = { EuroTopBar(title = "Detalhes do educador", onBack = onBack) },
        containerColor = EuroPalette.Ink50
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            if (educator == null) {
                Text(
                    text = "Educador nao encontrado.",
                    modifier = Modifier.padding(20.dp),
                    color = EuroPalette.Ink500
                )
                return@Column
            }

            EuroCard(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(EuroPalette.Brand500.copy(alpha = 0.15f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = educator.initials,
                            style = MaterialTheme.typography.headlineMedium,
                            color = EuroPalette.Brand800
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = educator.name,
                            style = MaterialTheme.typography.headlineMedium,
                            color = EuroPalette.Brand800
                        )
                        Text(
                            text = educator.mainCourse,
                            style = MaterialTheme.typography.bodyMedium,
                            color = EuroPalette.Ink600
                        )
                    }
                    StatusChip(
                        text = if (educator.alert) "Alerta" else "Ok",
                        accent = if (educator.alert) EuroPalette.Attention else EuroPalette.Engaged
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
                Stat(label = "Turmas", value = educator.classes.toString(), modifier = Modifier.weight(1f))
                Stat(label = "Alunos", value = educator.students.toString(), modifier = Modifier.weight(1f))
                Stat(label = "Engajamento", value = "${educator.engagement}%", modifier = Modifier.weight(1f))
            }
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Stat(label = "Delta", value = educator.delta, modifier = Modifier.weight(1f))
                Stat(
                    label = "Status",
                    value = if (educator.alert) "Atencao" else "Saudavel",
                    modifier = Modifier.weight(1f)
                )
            }

            SectionHeader(title = "Observacao pedagogica")
            EuroCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = educator.highlight,
                    style = MaterialTheme.typography.bodyMedium,
                    color = EuroPalette.Ink700
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
