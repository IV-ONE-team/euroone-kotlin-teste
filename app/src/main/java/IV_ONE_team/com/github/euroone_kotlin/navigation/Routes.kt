package IV_ONE_team.com.github.euroone_kotlin.navigation

/**
 * Centraliza as rotas do NavHost do EuroOne. Facilita o refactor e evita
 * strings "magicas" espalhadas pelas telas.
 */
object Routes {
    const val LOGIN = "login"

    // Educando
    const val EDUCANDO_HOME = "educando/home"
    const val EDUCANDO_CURSO = "educando/curso/{courseId}"
    const val EDUCANDO_RECOMPENSAS = "educando/recompensas"
    const val EDUCANDO_PERFIL = "educando/perfil"
    fun educandoCurso(courseId: String) = "educando/curso/$courseId"

    // Educador
    const val EDUCADOR_OVERVIEW = "educador/overview"
    const val EDUCADOR_TURMAS = "educador/turmas"
    const val EDUCADOR_TURMA_DETALHE = "educador/turmas/{classId}"
    const val EDUCADOR_ALUNOS = "educador/alunos"
    const val EDUCADOR_ALUNO_DETALHE = "educador/alunos/{studentId}"
    fun educadorTurmaDetalhe(classId: String) = "educador/turmas/$classId"
    fun educadorAlunoDetalhe(studentId: String) = "educador/alunos/$studentId"

    // Gestao
    const val GESTAO_OVERVIEW = "gestao/overview"
    const val GESTAO_CURSOS = "gestao/cursos"
    const val GESTAO_CURSO_DETALHE = "gestao/cursos/{courseId}"
    const val GESTAO_EDUCADORES = "gestao/educadores"
    const val GESTAO_EDUCADOR_DETALHE = "gestao/educadores/{educatorId}"
    fun gestaoCursoDetalhe(courseId: String) = "gestao/cursos/$courseId"
    fun gestaoEducadorDetalhe(educatorId: String) = "gestao/educadores/$educatorId"
}
