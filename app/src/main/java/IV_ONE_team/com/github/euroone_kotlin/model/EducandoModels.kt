package IV_ONE_team.com.github.euroone_kotlin.model

/** Metricas principais exibidas na home do educando. */
data class EducandoSnapshot(
    val studentName: String,
    val progress: Int,
    val faltas: Int,
    val entregasPendentes: Int,
    val points: Int,
    val level: Int,
    val streakDays: Int,
    val ranking: Int,
    val rankingTotal: Int
)

/** Missao gamificada. Ao concluir, o educando ganha [points] pontos. */
data class Mission(
    val id: String,
    val title: String,
    val description: String,
    val points: Int,
    val completed: Boolean,
    val dueLabel: String
)

/** Item de recompensa disponivel no catalogo. */
data class RewardItem(
    val id: String,
    val title: String,
    val description: String,
    val costPoints: Int,
    val unlocked: Boolean,
    val stock: String
)

/** Curso em andamento ou concluido pelo educando. */
data class CourseProgress(
    val id: String,
    val name: String,
    val teacher: String,
    val className: String,
    val currentModule: String,
    val nextLesson: String,
    val watchedLessons: Int,
    val totalLessons: Int,
    val completedAssignments: Int,
    val pendingAssignments: Int,
    val progress: Int,
    val status: String
)

/** Prazo/atividade proxima. */
data class ActivityDeadline(
    val id: String,
    val title: String,
    val context: String,
    val dueLabel: String,
    val status: String,
    val progress: Int
)

/** Selo/emblema conquistado pelo educando. */
data class BadgeInfo(
    val id: String,
    val name: String,
    val description: String,
    val earned: Boolean,
    val progress: Int,
    val total: Int
)
