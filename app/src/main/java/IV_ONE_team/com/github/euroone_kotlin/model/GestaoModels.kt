package IV_ONE_team.com.github.euroone_kotlin.model

/** Perfil da gestao executiva. */
data class ManagementProfile(
    val name: String,
    val registration: String,
    val roleTitle: String,
    val courses: Int,
    val educators: Int,
    val activeStudents: Int,
    val overallEngagement: Int
)

/** Curso sob gestao executiva. */
data class ManagedCourse(
    val id: String,
    val name: String,
    val students: Int,
    val engagement: Int,
    val completion: Int,
    val startDate: String,
    val endDate: String,
    val delta: String,
    val alert: Boolean,
    val classes: String,
    val educators: String
)

/** Educador visto pela gestao. */
data class EducatorSummary(
    val id: String,
    val name: String,
    val initials: String,
    val mainCourse: String,
    val classes: Int,
    val students: Int,
    val engagement: Int,
    val delta: String,
    val alert: Boolean,
    val highlight: String
)

/** Metrica generica para dashboards. */
data class DashboardMetric(
    val id: String,
    val label: String,
    val value: String,
    val helper: String,
    val status: String,
    val trend: String? = null
)

/** Item da fila de cuidado (educandos que precisam de atencao imediata). */
data class CareQueueItem(
    val id: String,
    val name: String,
    val profile: String,
    val reason: String,
    val severity: String,
    val attendance: Int,
    val engagement: Int,
    val suggestedAction: String,
    val interventionStatus: String
)
