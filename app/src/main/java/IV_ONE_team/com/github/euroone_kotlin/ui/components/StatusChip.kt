package IV_ONE_team.com.github.euroone_kotlin.ui.components

import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Chip pequeno para exibir status categorizados (engajado, atencao, risco...).
 */
@Composable
fun StatusChip(
    text: String,
    accent: Color = EuroPalette.Brand500,
    modifier: Modifier = Modifier
) {
    Text(
        text = text.uppercase(),
        style = MaterialTheme.typography.labelSmall,
        color = accent,
        modifier = modifier
            .background(
                color = accent.copy(alpha = 0.15f),
                shape = RoundedCornerShape(999.dp)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
    )
}

/**
 * Mapa auxiliar para converter status textual em cor semantica.
 */
fun statusAccent(status: String): Color = when (status.lowercase()) {
    "engajado", "engaged", "success", "ativo", "em_andamento" -> EuroPalette.Engaged
    "atencao", "attention" -> EuroPalette.Attention
    "risco", "critico", "critical" -> EuroPalette.Critical
    "autodidata", "autodidact", "info" -> EuroPalette.Autodidact
    "concluida", "concluido" -> EuroPalette.Brand500
    else -> EuroPalette.Ink500
}
