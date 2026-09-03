package IV_ONE_team.com.github.euroone_kotlin.ui.screens.educando

import IV_ONE_team.com.github.euroone_kotlin.R
import IV_ONE_team.com.github.euroone_kotlin.model.BadgeInfo
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroTopBar
import IV_ONE_team.com.github.euroone_kotlin.ui.components.SectionHeader
import IV_ONE_team.com.github.euroone_kotlin.ui.components.StatusChip
import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.AuthViewModel
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.EducandoViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Tela de perfil do educando: dados institucionais + badges + botao de sair.
 */
@Composable
fun EducandoPerfilScreen(
    authViewModel: AuthViewModel,
    educandoViewModel: EducandoViewModel,
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    val user by authViewModel.currentUser.collectAsStateWithLifecycle()
    val state by educandoViewModel.state.collectAsStateWithLifecycle()
    val snapshot = state.snapshot

    Scaffold(
        topBar = { EuroTopBar(title = "Meu perfil", onBack = onBack) },
        containerColor = EuroPalette.Ink50
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(EuroPalette.Brand800, EuroPalette.Brand500)
                        )
                    )
                    .padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.euri_mascot),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(72.dp)
                            .background(
                                EuroPalette.Ink0.copy(alpha = 0.15f),
                                RoundedCornerShape(20.dp)
                            )
                    )
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text(
                            text = user?.name.orEmpty(),
                            style = MaterialTheme.typography.headlineMedium,
                            color = EuroPalette.Ink0
                        )
                        Text(
                            text = "Matricula: ${user?.badgeCode.orEmpty()}",
                            style = MaterialTheme.typography.bodySmall,
                            color = EuroPalette.Brand100
                        )
                        Text(
                            text = user?.campus.orEmpty(),
                            style = MaterialTheme.typography.bodySmall,
                            color = EuroPalette.Brand100
                        )
                        Spacer(Modifier.height(6.dp))
                        StatusChip(
                            text = user?.role?.label.orEmpty(),
                            accent = EuroPalette.Yellow
                        )
                    }
                }
            }

            if (snapshot != null) {
                SectionHeader(title = "Progressao")
                EuroCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        InfoBlock(
                            label = "Nivel",
                            value = snapshot.level.toString(),
                            modifier = Modifier.weight(1f)
                        )
                        InfoBlock(
                            label = "Pontos",
                            value = snapshot.points.toString(),
                            modifier = Modifier.weight(1f)
                        )
                        InfoBlock(
                            label = "Ranking",
                            value = "${snapshot.ranking}/${snapshot.rankingTotal}",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            SectionHeader(
                title = "Emblemas conquistados",
                subtitle = "Continue evoluindo para desbloquear todos."
            )
            state.badges.forEach { badge ->
                BadgeCard(badge)
                Spacer(Modifier.height(8.dp))
            }

            Spacer(Modifier.height(20.dp))
            Button(
                onClick = onLogout,
                colors = ButtonDefaults.buttonColors(
                    containerColor = EuroPalette.Critical,
                    contentColor = EuroPalette.Ink0
                ),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) { Text("Sair da conta") }
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
private fun InfoBlock(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

@Composable
private fun BadgeCard(badge: BadgeInfo) {
    val accent = if (badge.earned) EuroPalette.Gold else EuroPalette.Ink400
    EuroCard(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = badge.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Ink900
                )
                Text(
                    text = badge.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink500
                )
                Spacer(Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { badge.progress / badge.total.toFloat() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
                    color = accent,
                    trackColor = EuroPalette.Ink100
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "${badge.progress}/${badge.total}",
                    style = MaterialTheme.typography.labelSmall,
                    color = EuroPalette.Ink500
                )
            }
            Spacer(Modifier.width(12.dp))
            StatusChip(
                text = if (badge.earned) "Conquistado" else "Em progresso",
                accent = accent
            )
        }
    }
}
