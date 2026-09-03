package IV_ONE_team.com.github.euroone_kotlin.ui.screens.educando

import IV_ONE_team.com.github.euroone_kotlin.model.RewardItem
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroTopBar
import IV_ONE_team.com.github.euroone_kotlin.ui.components.SectionHeader
import IV_ONE_team.com.github.euroone_kotlin.ui.components.StatusChip
import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.EducandoViewModel
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Catalogo de recompensas do educando. Exibe itens que podem ser trocados
 * pelos pontos acumulados. Recompensas bloqueadas ficam com destaque
 * visual distinto para deixar clara a mecanica de progressao.
 */
@Composable
fun EducandoRecompensasScreen(
    educandoViewModel: EducandoViewModel,
    onBack: () -> Unit
) {
    val state by educandoViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { EuroTopBar(title = "Recompensas", onBack = onBack) },
        containerColor = EuroPalette.Ink50
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            RewardsHeader(points = state.snapshot?.points ?: 0)
            SectionHeader(
                title = "Catalogo",
                subtitle = "Troque seus pontos por itens do Instituto Eurofarma."
            )
            LazyColumn(
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.rewards, key = { it.id }) { reward ->
                    RewardCard(reward)
                }
            }
        }
    }
}

@Composable
private fun RewardsHeader(points: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(EuroPalette.Coral, EuroPalette.Amber)
                )
            )
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(
                        color = EuroPalette.Ink0.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CardGiftcard,
                    contentDescription = null,
                    tint = EuroPalette.Ink0
                )
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text(
                    text = "Voce tem",
                    color = EuroPalette.Ink0,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "$points pontos",
                    color = EuroPalette.Ink0,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Reservados para novas conquistas.",
                    color = EuroPalette.Ink0,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
private fun RewardCard(reward: RewardItem) {
    val accent = if (reward.unlocked) EuroPalette.Engaged else EuroPalette.Ink500
    EuroCard {
        Row {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = reward.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Ink900
                )
                Text(
                    text = reward.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink500
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = reward.stock,
                    style = MaterialTheme.typography.labelSmall,
                    color = EuroPalette.Ink400
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${reward.costPoints} pts",
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Gold
                )
                Spacer(Modifier.height(6.dp))
                StatusChip(
                    text = if (reward.unlocked) "Disponivel" else "Bloqueada",
                    accent = accent
                )
            }
        }
    }
}
