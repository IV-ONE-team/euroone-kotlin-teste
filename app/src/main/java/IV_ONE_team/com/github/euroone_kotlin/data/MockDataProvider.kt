package IV_ONE_team.com.github.euroone_kotlin.data

import IV_ONE_team.com.github.euroone_kotlin.model.ActivityDeadline
import IV_ONE_team.com.github.euroone_kotlin.model.AlertItem
import IV_ONE_team.com.github.euroone_kotlin.model.BadgeInfo
import IV_ONE_team.com.github.euroone_kotlin.model.CareQueueItem
import IV_ONE_team.com.github.euroone_kotlin.model.CourseProgress
import IV_ONE_team.com.github.euroone_kotlin.model.DashboardMetric
import IV_ONE_team.com.github.euroone_kotlin.model.EducandoSnapshot
import IV_ONE_team.com.github.euroone_kotlin.model.EducatorClassInfo
import IV_ONE_team.com.github.euroone_kotlin.model.EducatorProfile
import IV_ONE_team.com.github.euroone_kotlin.model.EducatorSummary
import IV_ONE_team.com.github.euroone_kotlin.model.ManagedCourse
import IV_ONE_team.com.github.euroone_kotlin.model.ManagementProfile
import IV_ONE_team.com.github.euroone_kotlin.model.Mission
import IV_ONE_team.com.github.euroone_kotlin.model.RewardItem
import IV_ONE_team.com.github.euroone_kotlin.model.StudentListItem
import IV_ONE_team.com.github.euroone_kotlin.model.User
import IV_ONE_team.com.github.euroone_kotlin.model.UserRole

/**
 * Fonte de dados mockados do EuroOne.
 *
 * Como a Sprint 3 nao exige integracao com API/Firebase/banco de dados, todo o
 * comportamento da aplicacao e alimentado por este objeto. Todos os dados sao
 * coerentes com o dominio do Instituto Eurofarma (Farmacovigilancia,
 * bioequivalencia, epidemiologia, etc.) e replicam a estrutura do prototipo
 * original em Flutter, preservando nomes, turmas e metricas.
 */
object MockDataProvider {

    // ---------------------------------------------------------------------
    // Autenticacao / usuarios de demonstracao (senha padrao "123456").
    // ---------------------------------------------------------------------

    const val DEMO_PASSWORD = "123456"

    val demoUsers: List<User> = listOf(
        User(
            id = "u-educando-1",
            name = "Marina Sales",
            role = UserRole.EDUCANDO,
            email = "aluno@gmail.com",
            badgeCode = "IE-2024-0281",
            campus = "Instituto Eurofarma - Itapevi",
            segment = "Especializacao",
            className = "Turma 24-B - Noturno",
            learningTrack = "Farmacovigilancia"
        ),
        User(
            id = "u-educador-1",
            name = "Dr. Renato Lima",
            role = UserRole.EDUCADOR,
            email = "pf_9821@gmail.com",
            badgeCode = "PF-9821",
            campus = "Instituto Eurofarma - Itapevi",
            segment = "Corpo Docente",
            learningTrack = "Farmacocinetica avancada"
        ),
        User(
            id = "u-educador-2",
            name = "Dra. Sandra Pires",
            role = UserRole.EDUCADOR,
            email = "pf_102030@gmail.com",
            badgeCode = "PF-102030",
            campus = "Instituto Eurofarma - Itapevi",
            segment = "Corpo Docente",
            learningTrack = "Boas praticas em pesquisa clinica"
        ),
        User(
            id = "u-gestao-1",
            name = "Camila Ferreira",
            role = UserRole.GESTAO,
            email = "eurone_1029@gmail.com",
            badgeCode = "GE-1029",
            campus = "Sede Eurofarma - Sao Paulo",
            segment = "Diretoria Educacional"
        ),
        User(
            id = "u-gestao-2",
            name = "Roberto Aoki",
            role = UserRole.GESTAO,
            email = "eurone_998877@gmail.com",
            badgeCode = "GE-998877",
            campus = "Sede Eurofarma - Sao Paulo",
            segment = "Coordenacao Pedagogica"
        )
    )

    fun findUser(email: String, password: String): User? {
        if (password != DEMO_PASSWORD) return null
        val normalized = email.trim().lowercase()
        return demoUsers.firstOrNull { it.email.lowercase() == normalized }
    }

    // ---------------------------------------------------------------------
    // Educando
    // ---------------------------------------------------------------------

    fun educandoSnapshot(user: User): EducandoSnapshot = EducandoSnapshot(
        studentName = user.name,
        progress = 73,
        faltas = 2,
        entregasPendentes = 3,
        points = 1840,
        level = 12,
        streakDays = 18,
        ranking = 4,
        rankingTotal = 42
    )

    val educandoCourses: List<CourseProgress> = listOf(
        CourseProgress(
            id = "c-farmaco",
            name = "Especializacao em Farmacovigilancia",
            teacher = "Dr. Renato Lima",
            className = "Turma 24-B - Noturno",
            currentModule = "Modulo 5 - Farmacocinetica avancada",
            nextLesson = "Hoje, 19:00 - Auditorio 2",
            watchedLessons = 38,
            totalLessons = 52,
            completedAssignments = 14,
            pendingAssignments = 3,
            progress = 73,
            status = "ativo"
        ),
        CourseProgress(
            id = "c-clinica",
            name = "Boas praticas em pesquisa clinica",
            teacher = "Dra. Sandra Pires",
            className = "Turma 24-D",
            currentModule = "Modulo 4 - Protocolos de pesquisa",
            nextLesson = "Quinta, 20:00 - Sala 305",
            watchedLessons = 16,
            totalLessons = 39,
            completedAssignments = 7,
            pendingAssignments = 2,
            progress = 41,
            status = "ativo"
        ),
        CourseProgress(
            id = "c-epidemio",
            name = "Fundamentos de epidemiologia",
            teacher = "Dra. Claudia Mota",
            className = "Turma 23-A",
            currentModule = "Concluido",
            nextLesson = "Trilha finalizada em Set/2024",
            watchedLessons = 28,
            totalLessons = 28,
            completedAssignments = 10,
            pendingAssignments = 0,
            progress = 100,
            status = "concluida"
        ),
        CourseProgress(
            id = "c-intro",
            name = "Introducao a industria farmaceutica",
            teacher = "Dr. Marcos Vinicius",
            className = "Turma 23-B",
            currentModule = "Concluido",
            nextLesson = "Trilha finalizada em Jun/2024",
            watchedLessons = 24,
            totalLessons = 24,
            completedAssignments = 8,
            pendingAssignments = 0,
            progress = 100,
            status = "concluida"
        )
    )

    val educandoMissions: List<Mission> = listOf(
        Mission(
            id = "m-1",
            title = "Assista a aula de Farmacocinetica",
            description = "Aula ao vivo com Dr. Renato Lima no Auditorio 2.",
            points = 120,
            completed = false,
            dueLabel = "Hoje, 19:00"
        ),
        Mission(
            id = "m-2",
            title = "Estudo de caso: Paciente 47",
            description = "Analise clinica e proposta de conduta em Farmacovigilancia.",
            points = 200,
            completed = false,
            dueLabel = "Hoje, 23:59"
        ),
        Mission(
            id = "m-3",
            title = "Quiz do capitulo 7",
            description = "Epidemiologia aplicada - 15 questoes objetivas.",
            points = 80,
            completed = true,
            dueLabel = "Concluido"
        ),
        Mission(
            id = "m-4",
            title = "Foruns clinicos",
            description = "Participe da discussao sobre bioequivalencia.",
            points = 60,
            completed = false,
            dueLabel = "Sexta, 19:00"
        )
    )

    val educandoDeadlines: List<ActivityDeadline> = listOf(
        ActivityDeadline(
            id = "d-1",
            title = "Estudo de caso: Paciente 47",
            context = "Farmacovigilancia",
            dueLabel = "Hoje, 23:59",
            status = "attention",
            progress = 80
        ),
        ActivityDeadline(
            id = "d-2",
            title = "Relatorio Modulo 5",
            context = "Bioequivalencia",
            dueLabel = "3 dias",
            status = "info",
            progress = 30
        ),
        ActivityDeadline(
            id = "d-3",
            title = "Quiz - Capitulo 7",
            context = "Epidemiologia",
            dueLabel = "6 dias",
            status = "neutral",
            progress = 0
        )
    )

    val educandoRewards: List<RewardItem> = listOf(
        RewardItem(
            id = "r-1",
            title = "Voucher livraria tecnica",
            description = "R$ 80 em qualquer titulo da area farmaceutica.",
            costPoints = 1500,
            unlocked = true,
            stock = "12 disponiveis"
        ),
        RewardItem(
            id = "r-2",
            title = "Mentoria 1:1 com pesquisador Eurofarma",
            description = "Sessao de 45min agendada com um mentor senior.",
            costPoints = 2400,
            unlocked = false,
            stock = "4 disponiveis"
        ),
        RewardItem(
            id = "r-3",
            title = "Camiseta oficial Instituto Eurofarma",
            description = "Modelo P&D limitado, tamanhos P ao GG.",
            costPoints = 900,
            unlocked = true,
            stock = "27 disponiveis"
        ),
        RewardItem(
            id = "r-4",
            title = "Curso extra: Regulatorio Anvisa",
            description = "Trilha complementar de 12h com certificado.",
            costPoints = 3200,
            unlocked = false,
            stock = "Ilimitado"
        ),
        RewardItem(
            id = "r-5",
            title = "Vale-lanche cafeteria interna",
            description = "Cafe + snack no Restaurante Central.",
            costPoints = 350,
            unlocked = true,
            stock = "Diario"
        )
    )

    val educandoBadges: List<BadgeInfo> = listOf(
        BadgeInfo(
            id = "b-1",
            name = "Presenca de ouro",
            description = "15 aulas consecutivas sem faltas.",
            earned = true,
            progress = 15,
            total = 15
        ),
        BadgeInfo(
            id = "b-2",
            name = "Curador de discussoes",
            description = "10 respostas uteis no forum clinico.",
            earned = true,
            progress = 10,
            total = 10
        ),
        BadgeInfo(
            id = "b-3",
            name = "Mestre em quizzes",
            description = "Complete 20 quizzes com nota >= 8.",
            earned = false,
            progress = 12,
            total = 20
        ),
        BadgeInfo(
            id = "b-4",
            name = "Autodidata",
            description = "Assista 30h de conteudo extra em 1 mes.",
            earned = false,
            progress = 22,
            total = 30
        )
    )

    // ---------------------------------------------------------------------
    // Educador
    // ---------------------------------------------------------------------

    fun educadorProfile(user: User): EducatorProfile = EducatorProfile(
        name = user.name,
        registration = user.badgeCode,
        disciplines = listOf(
            "Farmacocinetica avancada",
            "Farmacovigilancia",
            "Metodologia cientifica"
        ),
        classes = 4,
        totalStudents = 118,
        averageAttendance = 87,
        averageEngagement = 76,
        activeAlerts = 6
    )

    val educadorClasses: List<EducatorClassInfo> = listOf(
        EducatorClassInfo(
            id = "t-1",
            discipline = "Farmacocinetica avancada",
            className = "Turma 24-B - Noturno",
            schedule = "Seg/Qua - 19:00 as 22:00",
            room = "Auditorio 2 - Bloco P&D",
            currentModule = "Modulo 5 - Absorcao e distribuicao",
            startDate = "12/02/2024",
            endDate = "20/12/2024",
            students = 32,
            attendance = 89,
            engagement = 78,
            status = "em_andamento"
        ),
        EducatorClassInfo(
            id = "t-2",
            discipline = "Farmacovigilancia",
            className = "Turma 24-A - Matutino",
            schedule = "Ter/Qui - 08:00 as 11:00",
            room = "Sala 305",
            currentModule = "Modulo 3 - Sinais de seguranca",
            startDate = "05/03/2024",
            endDate = "07/12/2024",
            students = 28,
            attendance = 91,
            engagement = 82,
            status = "em_andamento"
        ),
        EducatorClassInfo(
            id = "t-3",
            discipline = "Metodologia cientifica",
            className = "Turma 24-C",
            schedule = "Sex - 14:00 as 17:00",
            room = "Sala 210",
            currentModule = "Modulo 2 - Delineamento de estudos",
            startDate = "15/03/2024",
            endDate = "13/12/2024",
            students = 26,
            attendance = 84,
            engagement = 70,
            status = "atencao"
        ),
        EducatorClassInfo(
            id = "t-4",
            discipline = "Farmacovigilancia",
            className = "Turma 24-D - Noturno",
            schedule = "Qua/Sex - 19:00 as 21:00",
            room = "Sala 402",
            currentModule = "Modulo 4 - Notificacoes espontaneas",
            startDate = "01/04/2024",
            endDate = "15/12/2024",
            students = 32,
            attendance = 83,
            engagement = 74,
            status = "em_andamento"
        )
    )

    val educadorStudents: List<StudentListItem> = listOf(
        StudentListItem(
            id = "s-1",
            name = "Marina Sales",
            registration = "IE-2024-0281",
            discipline = "Farmacocinetica avancada",
            className = "Turma 24-B",
            attendance = 96,
            engagement = 88,
            points = 1840,
            pendingAssignments = 1,
            status = "engajado",
            recommendedAction = "Convidar para monitoria da turma."
        ),
        StudentListItem(
            id = "s-2",
            name = "Bruno Yamamoto",
            registration = "IE-2024-0134",
            discipline = "Farmacocinetica avancada",
            className = "Turma 24-B",
            attendance = 78,
            engagement = 62,
            points = 980,
            pendingAssignments = 3,
            status = "atencao",
            recommendedAction = "Agendar conversa individual esta semana."
        ),
        StudentListItem(
            id = "s-3",
            name = "Rafaela Nogueira",
            registration = "IE-2024-0198",
            discipline = "Farmacovigilancia",
            className = "Turma 24-A",
            attendance = 65,
            engagement = 48,
            points = 620,
            pendingAssignments = 5,
            status = "risco",
            recommendedAction = "Acionar tutoria pedagogica e revisar plano."
        ),
        StudentListItem(
            id = "s-4",
            name = "Diego Prado",
            registration = "IE-2024-0102",
            discipline = "Metodologia cientifica",
            className = "Turma 24-C",
            attendance = 92,
            engagement = 91,
            points = 2110,
            pendingAssignments = 0,
            status = "engajado",
            recommendedAction = "Sugerir participacao em projeto de pesquisa."
        ),
        StudentListItem(
            id = "s-5",
            name = "Camila Duarte",
            registration = "IE-2024-0257",
            discipline = "Farmacovigilancia",
            className = "Turma 24-D",
            attendance = 71,
            engagement = 65,
            points = 810,
            pendingAssignments = 2,
            status = "atencao",
            recommendedAction = "Reforcar conteudo do Modulo 3 com material extra."
        ),
        StudentListItem(
            id = "s-6",
            name = "Pedro Alcantara",
            registration = "IE-2024-0311",
            discipline = "Farmacovigilancia",
            className = "Turma 24-D",
            attendance = 88,
            engagement = 74,
            points = 1420,
            pendingAssignments = 1,
            status = "engajado",
            recommendedAction = "Manter acompanhamento regular."
        )
    )

    val educadorAlerts: List<AlertItem> = listOf(
        AlertItem(
            id = "a-1",
            studentName = "Rafaela Nogueira",
            level = "critico",
            title = "Presenca abaixo de 70%",
            reason = "3 faltas consecutivas em Farmacovigilancia.",
            recommendedAction = "Acionar tutoria pedagogica em 48h."
        ),
        AlertItem(
            id = "a-2",
            studentName = "Bruno Yamamoto",
            level = "atencao",
            title = "3 entregas pendentes",
            reason = "Queda no engajamento nos ultimos 15 dias.",
            recommendedAction = "Conversa individual e plano de recuperacao."
        ),
        AlertItem(
            id = "a-3",
            studentName = "Camila Duarte",
            level = "atencao",
            title = "Notas em queda no Modulo 3",
            reason = "Media caiu de 8.2 para 6.4 em duas avaliacoes.",
            recommendedAction = "Sessao de reforco em notificacoes espontaneas."
        )
    )

    // ---------------------------------------------------------------------
    // Gestao
    // ---------------------------------------------------------------------

    fun gestaoProfile(user: User): ManagementProfile = ManagementProfile(
        name = user.name,
        registration = user.badgeCode,
        roleTitle = "Diretoria Educacional Eurofarma",
        courses = 12,
        educators = 34,
        activeStudents = 862,
        overallEngagement = 79
    )

    val gestaoMetrics: List<DashboardMetric> = listOf(
        DashboardMetric(
            id = "gm-1",
            label = "Educandos ativos",
            value = "862",
            helper = "+38 no ultimo mes",
            status = "engaged",
            trend = "+4,6%"
        ),
        DashboardMetric(
            id = "gm-2",
            label = "Engajamento geral",
            value = "79%",
            helper = "Meta trimestral: 82%",
            status = "attention",
            trend = "-1,2%"
        ),
        DashboardMetric(
            id = "gm-3",
            label = "Educadores ativos",
            value = "34",
            helper = "2 novos contratos este mes",
            status = "engaged",
            trend = "+2"
        ),
        DashboardMetric(
            id = "gm-4",
            label = "Cursos em execucao",
            value = "12",
            helper = "3 em revisao curricular",
            status = "info",
            trend = "-"
        )
    )

    val gestaoCourses: List<ManagedCourse> = listOf(
        ManagedCourse(
            id = "gc-1",
            name = "Especializacao em Farmacovigilancia",
            students = 128,
            engagement = 84,
            completion = 71,
            startDate = "12/02/2024",
            endDate = "20/12/2024",
            delta = "+6%",
            alert = false,
            classes = "4 turmas",
            educators = "Dr. Renato Lima e 2 co-docentes"
        ),
        ManagedCourse(
            id = "gc-2",
            name = "Boas praticas em pesquisa clinica",
            students = 96,
            engagement = 72,
            completion = 58,
            startDate = "05/03/2024",
            endDate = "07/12/2024",
            delta = "-3%",
            alert = true,
            classes = "3 turmas",
            educators = "Dra. Sandra Pires e 1 co-docente"
        ),
        ManagedCourse(
            id = "gc-3",
            name = "Fundamentos de epidemiologia",
            students = 74,
            engagement = 81,
            completion = 100,
            startDate = "10/03/2023",
            endDate = "20/09/2024",
            delta = "+0%",
            alert = false,
            classes = "2 turmas",
            educators = "Dra. Claudia Mota"
        ),
        ManagedCourse(
            id = "gc-4",
            name = "Introducao a industria farmaceutica",
            students = 210,
            engagement = 77,
            completion = 100,
            startDate = "10/01/2023",
            endDate = "30/06/2024",
            delta = "+2%",
            alert = false,
            classes = "6 turmas",
            educators = "Dr. Marcos Vinicius e equipe"
        ),
        ManagedCourse(
            id = "gc-5",
            name = "Formacao de Jovens - Bloco 2",
            students = 354,
            engagement = 74,
            completion = 42,
            startDate = "01/03/2024",
            endDate = "15/12/2024",
            delta = "+1%",
            alert = false,
            classes = "10 turmas",
            educators = "Equipe multidisciplinar"
        )
    )

    val gestaoEducators: List<EducatorSummary> = listOf(
        EducatorSummary(
            id = "ge-1",
            name = "Dr. Renato Lima",
            initials = "RL",
            mainCourse = "Farmacovigilancia",
            classes = 4,
            students = 118,
            engagement = 82,
            delta = "+5%",
            alert = false,
            highlight = "Mentor destaque no ultimo trimestre."
        ),
        EducatorSummary(
            id = "ge-2",
            name = "Dra. Sandra Pires",
            initials = "SP",
            mainCourse = "Pesquisa clinica",
            classes = 3,
            students = 96,
            engagement = 71,
            delta = "-4%",
            alert = true,
            highlight = "Solicita apoio pedagogico para turma 24-D."
        ),
        EducatorSummary(
            id = "ge-3",
            name = "Dra. Claudia Mota",
            initials = "CM",
            mainCourse = "Epidemiologia",
            classes = 2,
            students = 74,
            engagement = 80,
            delta = "+2%",
            alert = false,
            highlight = "Turmas encerradas com aproveitamento >= 85%."
        ),
        EducatorSummary(
            id = "ge-4",
            name = "Dr. Marcos Vinicius",
            initials = "MV",
            mainCourse = "Industria farmaceutica",
            classes = 6,
            students = 210,
            engagement = 77,
            delta = "+1%",
            alert = false,
            highlight = "Bom equilibrio entre teoria e visitas tecnicas."
        )
    )

    val gestaoCareQueue: List<CareQueueItem> = listOf(
        CareQueueItem(
            id = "cq-1",
            name = "Rafaela Nogueira",
            profile = "Educanda - Farmacovigilancia",
            reason = "Presenca abaixo de 70% e 5 entregas pendentes.",
            severity = "critico",
            attendance = 65,
            engagement = 48,
            suggestedAction = "Tutoria pedagogica + plano de recuperacao.",
            interventionStatus = "Em analise"
        ),
        CareQueueItem(
            id = "cq-2",
            name = "Bruno Yamamoto",
            profile = "Educando - Farmacocinetica",
            reason = "Engajamento em queda ha 3 semanas.",
            severity = "atencao",
            attendance = 78,
            engagement = 62,
            suggestedAction = "Conversa individual com educador.",
            interventionStatus = "Agendado"
        ),
        CareQueueItem(
            id = "cq-3",
            name = "Turma 24-D",
            profile = "Turma - Pesquisa Clinica",
            reason = "Engajamento medio 6% abaixo da meta.",
            severity = "atencao",
            attendance = 82,
            engagement = 68,
            suggestedAction = "Revisar dinamica das aulas praticas.",
            interventionStatus = "Nao iniciado"
        )
    )
}
