package IV_ONE_team.com.github.euroone_kotlin.ui.components

import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Cabecalho de secao com titulo e legenda opcional. Usado para dividir
 * secoes verticais nas telas do EuroOne.
 */
@Composable
fun SectionHeader(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
) {
    Column(modifier = modifier.padding(padding)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = EuroPalette.Brand800
        )
        if (subtitle != null) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = EuroPalette.Ink500
            )
        }
    }
}
