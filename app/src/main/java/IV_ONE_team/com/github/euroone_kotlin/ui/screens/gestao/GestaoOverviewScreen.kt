package IV_ONE_team.com.github.euroone_kotlin.ui.screens.gestao

import IV_ONE_team.com.github.euroone_kotlin.model.CareQueueItem
import IV_ONE_team.com.github.euroone_kotlin.model.DashboardMetric
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroTopBar
import IV_ONE_team.com.github.euroone_kotlin.ui.components.MetricCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.SectionHeader
import IV_ONE_team.com.github.euroone_kotlin.ui.components.StatusChip
import IV_ONE_team.com.github.euroone_kotlin.ui.components.statusAccent
import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.AuthViewModel
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Person
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
 * Dashboard executivo da Gestao. Consolida perfil, metricas globais,
 * atalhos para Cursos/Educadores e fila prioritaria de cuidado.
 */
@Composable
fun GestaoOverviewScreen(
    authViewModel: AuthViewModel,
    gestaoViewModel: GestaoViewModel,
    onNavigateCursos: () -> Unit,
    onNavigateEducadores: () -> Unit,
    onLogout: () -> Unit
) {
    val user by authViewModel.currentUser.collectAsStateWithLifecycle()
    val state by gestaoViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(user) { user?.let { gestaoViewModel.load(it) } }

    val profile = state.profile
    Scaffold(
        topBar = { EuroTopBar(title = "EuroOne - Gestao", onLogout = onLogout) },
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
                            listOf(EuroPalette.Brand950, EuroPalette.Brand700)
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
                        text = profile?.roleTitle.orEmpty(),
                        style = MaterialTheme.typography.bodySmall,
                        color = EuroPalette.Brand100
                    )
                    Text(
                        text = "Matricula: ${profile?.registration.orEmpty()}",
                        style = MaterialTheme.typography.bodySmall,
                        color = EuroPalette.Brand100
                    )
                }
            }

            SectionHeader(title = "Indicadores globais")
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                state.metrics.chunked(2).forEach { pair ->
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        pair.forEach { metric ->
                            MetricCardWithStatus(metric = metric, modifier = Modifier.weight(1f))
                        }
                        if (pair.size == 1) Spacer(Modifier.weight(1f))
                    }
                    Spacer(Modifier.height(12.dp))
                }
            }

            SectionHeader(title = "Ir para")
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                NavTile(
                    label = "Cursos",
                    icon = Icons.Default.LibraryBooks,
                    color = EuroPalette.Brand500,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateCursos
                )
                NavTile(
                    label = "Educadores",
                    icon = Icons.Default.Person,
                    color = EuroPalette.Teal,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateEducadores
                )
            }

            SectionHeader(
                title = "Fila de cuidado",
                subtitle = "Casos priorizados pelo algoritmo pedagogico."
            )
            state.careQueue.forEach { item ->
                CareQueueCard(item)
                Spacer(Modifier.height(8.dp))
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun MetricCardWithStatus(metric: DashboardMetric, modifier: Modifier = Modifier) {
    MetricCard(
        modifier = modifier,
        label = metric.label,
        value = metric.value,
        helper = metric.helper,
        accent = statusAccent(metric.status),
        trend = metric.trend
    )
}

@Composable
private fun NavTile(
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
private fun CareQueueCard(item: CareQueueItem) {
    val accent = statusAccent(item.severity)
    EuroCard(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Ink900
                )
                Text(
                    text = item.profile,
                    style = MaterialTheme.typography.labelSmall,
                    color = EuroPalette.Ink500
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = item.reason,
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink700
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Sugestao: ${item.suggestedAction}",
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink600
                )
                Text(
                    text = "Intervencao: ${item.interventionStatus}",
                    style = MaterialTheme.typography.labelSmall,
                    color = EuroPalette.Ink500
                )
            }
            StatusChip(text = item.severity, accent = accent)
        }
    }
}
