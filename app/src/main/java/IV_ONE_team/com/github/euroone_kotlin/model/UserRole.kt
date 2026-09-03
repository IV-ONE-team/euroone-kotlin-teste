package IV_ONE_team.com.github.euroone_kotlin.model

/**
 * Perfis de usuario suportados pelo EuroOne. Cada perfil possui uma
 * jornada distinta na aplicacao (educando, educador e gestao) alinhada
 * aos publicos-alvo definidos no pitch da Sprint 1.
 */
enum class UserRole(val label: String, val description: String) {
    EDUCANDO(
        label = "Educando",
        description = "Acompanha pontos, missoes, cursos e presenca."
    ),
    EDUCADOR(
        label = "Educador",
        description = "Monitora turmas, gera intervencoes e faz chamada."
    ),
    GESTAO(
        label = "Gestao",
        description = "Visualiza indicadores executivos consolidados."
    );

    val homeRoute: String
        get() = when (this) {
            EDUCANDO -> "educando/home"
            EDUCADOR -> "educador/overview"
            GESTAO -> "gestao/overview"
        }
}
