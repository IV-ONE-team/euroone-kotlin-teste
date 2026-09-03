package IV_ONE_team.com.github.euroone_kotlin.ui.screens.educador

import IV_ONE_team.com.github.euroone_kotlin.model.AlertItem
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroTopBar
import IV_ONE_team.com.github.euroone_kotlin.ui.components.MetricCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.SectionHeader
import IV_ONE_team.com.github.euroone_kotlin.ui.components.StatusChip
import IV_ONE_team.com.github.euroone_kotlin.ui.components.statusAccent
import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.AuthViewModel
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.EducadorViewModel
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Painel do Educador. Consolida perfil, metricas gerais das turmas,
 * atalhos para as listas (Turmas, Alunos) e a fila de alertas
 * pedagogicos.
 */
@Composable
fun EducadorOverviewScreen(
    authViewModel: AuthViewModel,
    educadorViewModel: EducadorViewModel,
    onNavigateTurmas: () -> Unit,
    onNavigateAlunos: () -> Unit,
    onLogout: () -> Unit
) {
    val user by authViewModel.currentUser.collectAsStateWithLifecycle()
    val state by educadorViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(user) { user?.let { educadorViewModel.load(it) } }

    val profile = state.profile
    Scaffold(
        topBar = { EuroTopBar(title = "EuroOne - Educador", onLogout = onLogout) },
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
                Column {
                    Text(
                        text = "Ola, ${profile?.name.orEmpty()}",
                        style = MaterialTheme.typography.headlineMedium,
                        color = EuroPalette.Ink0
                    )
                    Text(
                        text = "Matricula ${profile?.registration.orEmpty()}",
                        style = MaterialTheme.typography.bodySmall,
                        color = EuroPalette.Brand100
                    )
                    Text(
                        text = profile?.disciplines?.joinToString(" - ").orEmpty(),
                        style = MaterialTheme.typography.bodySmall,
                        color = EuroPalette.Brand100
                    )
                }
            }

            if (profile != null) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        MetricCard(
                            modifier = Modifier.weight(1f),
                            label = "Turmas",
                            value = profile.classes.toString(),
                            helper = "Ativas neste semestre",
                            accent = EuroPalette.Brand500
                        )
                        MetricCard(
                            modifier = Modifier.weight(1f),
                            label = "Alunos",
                            value = profile.totalStudents.toString(),
                            helper = "Sob sua tutoria",
                            accent = EuroPalette.Teal
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        MetricCard(
                            modifier = Modifier.weight(1f),
                            label = "Presenca",
                            value = "${profile.averageAttendance}%",
                            helper = "Media geral",
                            accent = EuroPalette.Engaged
                        )
                        MetricCard(
                            modifier = Modifier.weight(1f),
                            label = "Engajamento",
                            value = "${profile.averageEngagement}%",
                            helper = "Media geral",
                            accent = EuroPalette.Autodidact
                        )
                    }
                }
            }

            SectionHeader(title = "Ir para")
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                NavigationTile(
                    label = "Minhas turmas",
                    icon = Icons.Default.School,
                    color = EuroPalette.Brand500,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateTurmas
                )
                NavigationTile(
                    label = "Meus alunos",
                    icon = Icons.Default.Group,
                    color = EuroPalette.Teal,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateAlunos
                )
            }

            SectionHeader(
                title = "Alertas ativos",
                subtitle = "Educandos que precisam de acao imediata."
            )
            state.alerts.forEach { alert ->
                AlertCard(alert)
                Spacer(Modifier.height(8.dp))
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun NavigationTile(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    EuroCard(
        modifier = modifier.clickable { onClick() },
        contentPadding = PaddingValues(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(color.copy(alpha = 0.15f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = label, tint = color)
            }
            Spacer(Modifier.width(12.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                color = EuroPalette.Ink900
            )
        }
    }
}

@Composable
private fun AlertCard(alert: AlertItem) {
    val accent = statusAccent(alert.level)
    EuroCard(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = alert.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Ink900
                )
                Text(
                    text = alert.studentName,
                    style = MaterialTheme.typography.labelLarge,
                    color = EuroPalette.Brand700
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = alert.reason,
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink600
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Sugestao: ${alert.recommendedAction}",
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink500
                )
            }
            StatusChip(text = alert.level, accent = accent)
        }
    }
}

