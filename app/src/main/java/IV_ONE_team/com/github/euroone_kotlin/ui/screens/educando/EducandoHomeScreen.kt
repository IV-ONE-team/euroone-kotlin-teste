package IV_ONE_team.com.github.euroone_kotlin.ui.screens.educando

import IV_ONE_team.com.github.euroone_kotlin.R
import IV_ONE_team.com.github.euroone_kotlin.model.ActivityDeadline
import IV_ONE_team.com.github.euroone_kotlin.model.CourseProgress
import IV_ONE_team.com.github.euroone_kotlin.model.EducandoSnapshot
import IV_ONE_team.com.github.euroone_kotlin.model.Mission
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.EuroTopBar
import IV_ONE_team.com.github.euroone_kotlin.ui.components.MetricCard
import IV_ONE_team.com.github.euroone_kotlin.ui.components.SectionHeader
import IV_ONE_team.com.github.euroone_kotlin.ui.components.StatusChip
import IV_ONE_team.com.github.euroone_kotlin.ui.components.statusAccent
import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.AuthViewModel
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.EducandoViewModel
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Home do Educando. Apresenta o resumo do progresso, missoes ativas,
 * cursos em andamento, prazos e atalhos para as demais telas.
 *
 * O layout foi projetado para ser scrollavel verticalmente em qualquer
 * tamanho de tela, mantendo hierarquia visual consistente com o prototipo.
 */
@Composable
fun EducandoHomeScreen(
    authViewModel: AuthViewModel,
    educandoViewModel: EducandoViewModel,
    onNavigateCurso: (String) -> Unit,
    onNavigateRecompensas: () -> Unit,
    onNavigatePerfil: () -> Unit,
    onLogout: () -> Unit
) {
    val user by authViewModel.currentUser.collectAsStateWithLifecycle()
    val state by educandoViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(user) { user?.let { educandoViewModel.load(it) } }

    val snapshot = state.snapshot
    Scaffold(
        topBar = { EuroTopBar(title = "EuroOne - Educando", onLogout = onLogout) },
        containerColor = EuroPalette.Ink50
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            WelcomeHeader(name = snapshot?.studentName ?: user?.name.orEmpty())

            if (snapshot != null) {
                MetricsRow(snapshot)
            }

            SectionHeader(title = "Atalhos rapidos")
            QuickActionsRow(
                onCurso = {
                    state.courses.firstOrNull()?.id?.let(onNavigateCurso)
                },
                onRecompensas = onNavigateRecompensas,
                onPerfil = onNavigatePerfil
            )

            SectionHeader(
                title = "Missoes desta semana",
                subtitle = "Complete para acumular pontos e desbloquear recompensas."
            )
            state.missions.forEach { mission ->
                MissionCard(mission)
                Spacer(Modifier.height(8.dp))
            }

            SectionHeader(
                title = "Cursos em andamento",
                subtitle = "Toque em um curso para ver detalhes."
            )
            state.courses.forEach { course ->
                CourseSummaryCard(course = course, onClick = { onNavigateCurso(course.id) })
                Spacer(Modifier.height(10.dp))
            }

            SectionHeader(title = "Prazos importantes")
            state.deadlines.forEach { deadline ->
                DeadlineCard(deadline)
                Spacer(Modifier.height(8.dp))
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun WelcomeHeader(name: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(EuroPalette.Brand800, EuroPalette.Brand500)
                )
            )
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Bom dia,",
                    color = EuroPalette.Brand100,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = name,
                    color = EuroPalette.Ink0,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Voce esta com uma sequencia ativa. Continue evoluindo!",
                    color = EuroPalette.Brand100,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Image(
                painter = painterResource(id = R.drawable.euri_mascot),
                contentDescription = "Mascote Euri",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(84.dp)
            )
        }
    }
}

@Composable
private fun MetricsRow(snapshot: EducandoSnapshot) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            MetricCard(
                modifier = Modifier.weight(1f),
                label = "Progresso",
                value = "${snapshot.progress}%",
                helper = "Trilha atual",
                accent = EuroPalette.Brand500,
                trend = "+3%"
            )
            MetricCard(
                modifier = Modifier.weight(1f),
                label = "Pontos",
                value = snapshot.points.toString(),
                helper = "Nivel ${snapshot.level}",
                accent = EuroPalette.Gold
            )
        }
        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            MetricCard(
                modifier = Modifier.weight(1f),
                label = "Faltas",
                value = snapshot.faltas.toString(),
                helper = "Ultimos 30 dias",
                accent = EuroPalette.Attention
            )
            MetricCard(
                modifier = Modifier.weight(1f),
                label = "Sequencia",
                value = "${snapshot.streakDays}d",
                helper = "Sem faltas",
                accent = EuroPalette.Engaged
            )
        }
    }
}

@Composable
private fun QuickActionsRow(
    onCurso: () -> Unit,
    onRecompensas: () -> Unit,
    onPerfil: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        QuickAction(
            modifier = Modifier.weight(1f),
            label = "Curso",
            icon = Icons.Default.School,
            color = EuroPalette.Brand500,
            onClick = onCurso
        )
        QuickAction(
            modifier = Modifier.weight(1f),
            label = "Recompensas",
            icon = Icons.Default.CardGiftcard,
            color = EuroPalette.Coral,
            onClick = onRecompensas
        )
        QuickAction(
            modifier = Modifier.weight(1f),
            label = "Perfil",
            icon = Icons.Default.Person,
            color = EuroPalette.Teal,
            onClick = onPerfil
        )
    }
}

@Composable
private fun QuickAction(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    EuroCard(
        modifier = modifier.clickable { onClick() },
        contentPadding = PaddingValues(14.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(color.copy(alpha = 0.15f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = label, tint = color)
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = EuroPalette.Ink900
            )
        }
    }
}

@Composable
private fun MissionCard(mission: Mission) {
    EuroCard(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = if (mission.completed) EuroPalette.Engaged.copy(alpha = 0.15f)
                        else EuroPalette.Brand500.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = null,
                    tint = if (mission.completed) EuroPalette.Engaged else EuroPalette.Brand500
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = mission.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Ink900
                )
                Text(
                    text = mission.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink500
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = mission.dueLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = EuroPalette.Attention
                )
            }
            Text(
                text = "+${mission.points}",
                style = MaterialTheme.typography.titleMedium,
                color = EuroPalette.Gold
            )
        }
    }
}

@Composable
private fun CourseSummaryCard(course: CourseProgress, onClick: () -> Unit) {
    EuroCard(modifier = Modifier
        .padding(horizontal = 16.dp)
        .clickable { onClick() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = course.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Ink900
                )
                Text(
                    text = "${course.teacher} - ${course.className}",
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink500
                )
            }
            StatusChip(text = course.status, accent = statusAccent(course.status))
        }
        Spacer(Modifier.height(10.dp))
        Text(
            text = course.currentModule,
            style = MaterialTheme.typography.bodyMedium,
            color = EuroPalette.Ink700
        )
        Spacer(Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { course.progress / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(EuroPalette.Ink100, RoundedCornerShape(4.dp)),
            color = EuroPalette.Brand500,
            trackColor = EuroPalette.Ink100
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = "${course.progress}% - Proxima: ${course.nextLesson}",
            style = MaterialTheme.typography.labelSmall,
            color = EuroPalette.Ink600
        )
    }
}

@Composable
private fun DeadlineCard(deadline: ActivityDeadline) {
    val accent = statusAccent(deadline.status)
    EuroCard(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = deadline.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = EuroPalette.Ink900
                )
                Text(
                    text = deadline.context,
                    style = MaterialTheme.typography.bodySmall,
                    color = EuroPalette.Ink500
                )
            }
            Text(
                text = deadline.dueLabel,
                style = MaterialTheme.typography.labelLarge,
                color = accent
            )
        }
        if (deadline.progress > 0) {
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { deadline.progress / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                color = accent,
                trackColor = EuroPalette.Ink100
            )
        }
    }
}
