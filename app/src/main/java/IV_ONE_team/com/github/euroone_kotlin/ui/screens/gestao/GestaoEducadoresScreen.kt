package IV_ONE_team.com.github.euroone_kotlin.ui.screens.gestao

import IV_ONE_team.com.github.euroone_kotlin.model.EducatorSummary
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroTopBar
import IV_ONE_team.com.github.euroone_kotlin.ui.components.StatusChip
import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.GestaoViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Listagem executiva de educadores. Toque para abrir o detalhe.
 */
@Composable
fun GestaoEducadoresScreen(
    gestaoViewModel: GestaoViewModel,
    onBack: () -> Unit,
    onSelectEducador: (String) -> Unit
) {
    val state by gestaoViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { EuroTopBar(title = "Educadores", onBack = onBack) },
        containerColor = EuroPalette.Ink50
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.educators, key = { it.id }) { educator ->
                EducatorCard(educator, onClick = { onSelectEducador(educator.id) })
            }
        }
    }
}

@Composable
private fun EducatorCard(educator: EducatorSummary, onClick: () -> Unit) {
    EuroCard(modifier = Modifier.clickable { onClick() }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(EuroPalette.Brand500.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = educator.initials,
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Brand800
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = educator.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Ink900
                )
                Text(
                    text = educator.mainCourse,
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink500
                )
                Text(
                    text = "${educator.classes} turmas - ${educator.students} alunos",
                    style = MaterialTheme.typography.labelSmall,
                    color = EuroPalette.Ink500
                )
            }
            StatusChip(
                text = if (educator.alert) "Alerta" else "Ok",
                accent = if (educator.alert) EuroPalette.Attention else EuroPalette.Engaged
            )
        }
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InfoInline(label = "Engajamento", value = "${educator.engagement}%")
            InfoInline(label = "Variacao", value = educator.delta)
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
