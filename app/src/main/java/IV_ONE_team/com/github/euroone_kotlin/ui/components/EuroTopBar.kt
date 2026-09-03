package IV_ONE_team.com.github.euroone_kotlin.ui.components

import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

/**
 * TopAppBar padronizada do EuroOne: cor institucional azul + botao de voltar
 * opcional + acao de logout opcional. Usada em todas as telas internas.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EuroTopBar(
    title: String,
    onBack: (() -> Unit)? = null,
    onLogout: (() -> Unit)? = null
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar"
                    )
                }
            }
        },
        actions = {
            if (onLogout != null) {
                IconButton(onClick = onLogout) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "Sair"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = EuroPalette.Brand800,
            titleContentColor = EuroPalette.Ink0,
            navigationIconContentColor = EuroPalette.Ink0,
            actionIconContentColor = EuroPalette.Ink0
        )
    )
}
