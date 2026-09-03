package IV_ONE_team.com.github.euroone_kotlin.model

/**
 * Representa uma pessoa autenticada dentro do EuroOne. O campo [badgeCode]
 * armazena a matricula/cracha institucional exibido no perfil.
 */
data class User(
    val id: String,
    val name: String,
    val role: UserRole,
    val email: String,
    val badgeCode: String,
    val campus: String,
    val segment: String,
    val className: String? = null,
    val learningTrack: String? = null
)
