package IV_ONE_team.com.github.euroone_kotlin.model

/** Perfil do educador no dashboard. */
data class EducatorProfile(
    val name: String,
    val registration: String,
    val disciplines: List<String>,
    val classes: Int,
    val totalStudents: Int,
    val averageAttendance: Int,
    val averageEngagement: Int,
    val activeAlerts: Int
)

/** Turma que o educador ministra. */
data class EducatorClassInfo(
    val id: String,
    val discipline: String,
    val className: String,
    val schedule: String,
    val room: String,
    val currentModule: String,
    val startDate: String,
    val endDate: String,
    val students: Int,
    val attendance: Int,
    val engagement: Int,
    val status: String
)

/** Aluno visto pela perspectiva do educador. */
data class StudentListItem(
    val id: String,
    val name: String,
    val registration: String,
    val discipline: String,
    val className: String,
    val attendance: Int,
    val engagement: Int,
    val points: Int,
    val pendingAssignments: Int,
    val status: String,
    val recommendedAction: String
)

/** Alerta pedagogico sugerido pelo sistema. */
data class AlertItem(
    val id: String,
    val studentName: String,
    val level: String,
    val title: String,
    val reason: String,
    val recommendedAction: String
)
