package IV_ONE_team.com.github.euroone_kotlin.ui.components

import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Cartao compacto para exibir uma metrica (valor + rotulo + auxiliar +
 * cor de status). Usado nos dashboards do educador e da gestao.
 */
@Composable
fun MetricCard(
    label: String,
    value: String,
    helper: String,
    accent: Color = EuroPalette.Brand500,
    trend: String? = null,
    modifier: Modifier = Modifier
) {
    EuroCard(
        modifier = modifier,
        contentPadding = PaddingValues(14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(
                        color = accent.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = accent
                )
            }
            if (trend != null) {
                Text(
                    text = trend,
                    style = MaterialTheme.typography.labelSmall,
                    color = EuroPalette.Ink500,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Column(
            modifier = Modifier.padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                color = EuroPalette.Ink900
            )
            Text(
                text = helper,
                style = MaterialTheme.typography.bodySmall,
                color = EuroPalette.Ink500
            )
        }
    }
}
