package IV_ONE_team.com.github.euroone_kotlin.navigation

import IV_ONE_team.com.github.euroone_kotlin.model.UserRole
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.auth.LoginScreen
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.educador.EducadorAlunoDetalheScreen
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.educador.EducadorAlunosScreen
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.educador.EducadorOverviewScreen
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.educador.EducadorTurmaDetalheScreen
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.educador.EducadorTurmasScreen
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.educando.EducandoCursoScreen
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.educando.EducandoHomeScreen
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.educando.EducandoPerfilScreen
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.educando.EducandoRecompensasScreen
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.gestao.GestaoCursoDetalheScreen
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.gestao.GestaoCursosScreen
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.gestao.GestaoEducadorDetalheScreen
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.gestao.GestaoEducadoresScreen
import IV_ONE_team.com.github.euroone_kotlin.ui.screens.gestao.GestaoOverviewScreen
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.AuthViewModel
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.EducadorViewModel
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.EducandoViewModel
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.GestaoViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

/**
 * NavHost principal do EuroOne. Instancia todos os ViewModels (Auth,
 * Educando, Educador, Gestao) no escopo do NavHost para que possam ser
 * compartilhados entre as telas de cada perfil.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val authViewModel: AuthViewModel = viewModel()
    val educandoViewModel: EducandoViewModel = viewModel()
    val educadorViewModel: EducadorViewModel = viewModel()
    val gestaoViewModel: GestaoViewModel = viewModel()

    val currentUser by authViewModel.currentUser.collectAsStateWithLifecycle()

    // Sempre que um usuario faz login, aciona o carregamento do estado do
    // perfil correspondente e faz a navegacao inicial.
    LaunchedEffect(currentUser) {
        val user = currentUser ?: return@LaunchedEffect
        when (user.role) {
            UserRole.EDUCANDO -> educandoViewModel.load(user)
            UserRole.EDUCADOR -> educadorViewModel.load(user)
            UserRole.GESTAO -> gestaoViewModel.load(user)
        }
        navController.navigate(user.role.homeRoute) {
            popUpTo(Routes.LOGIN) { inclusive = true }
        }
    }

    NavHost(navController = navController, startDestination = Routes.LOGIN) {

        composable(Routes.LOGIN) {
            LoginScreen(authViewModel = authViewModel)
        }

        // -------------------- Educando --------------------
        composable(Routes.EDUCANDO_HOME) {
            EducandoHomeScreen(
                authViewModel = authViewModel,
                educandoViewModel = educandoViewModel,
                onNavigateCurso = { courseId ->
                    navController.navigate(Routes.educandoCurso(courseId))
                },
                onNavigateRecompensas = { navController.navigate(Routes.EDUCANDO_RECOMPENSAS) },
                onNavigatePerfil = { navController.navigate(Routes.EDUCANDO_PERFIL) },
                onLogout = { navigateToLogin(navController, authViewModel) }
            )
        }
        composable(
            route = Routes.EDUCANDO_CURSO,
            arguments = listOf(navArgument("courseId") { type = NavType.StringType })
        ) { entry ->
            val courseId = entry.arguments?.getString("courseId").orEmpty()
            EducandoCursoScreen(
                courseId = courseId,
                educandoViewModel = educandoViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.EDUCANDO_RECOMPENSAS) {
            EducandoRecompensasScreen(
                educandoViewModel = educandoViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.EDUCANDO_PERFIL) {
            EducandoPerfilScreen(
                authViewModel = authViewModel,
                educandoViewModel = educandoViewModel,
                onBack = { navController.popBackStack() },
                onLogout = { navigateToLogin(navController, authViewModel) }
            )
        }

        // -------------------- Educador --------------------
        composable(Routes.EDUCADOR_OVERVIEW) {
            EducadorOverviewScreen(
                authViewModel = authViewModel,
                educadorViewModel = educadorViewModel,
                onNavigateTurmas = { navController.navigate(Routes.EDUCADOR_TURMAS) },
                onNavigateAlunos = { navController.navigate(Routes.EDUCADOR_ALUNOS) },
                onLogout = { navigateToLogin(navController, authViewModel) }
            )
        }
        composable(Routes.EDUCADOR_TURMAS) {
            EducadorTurmasScreen(
                educadorViewModel = educadorViewModel,
                onBack = { navController.popBackStack() },
                onSelectTurma = { classId ->
                    navController.navigate(Routes.educadorTurmaDetalhe(classId))
                }
            )
        }
        composable(
            route = Routes.EDUCADOR_TURMA_DETALHE,
            arguments = listOf(navArgument("classId") { type = NavType.StringType })
        ) { entry ->
            val classId = entry.arguments?.getString("classId").orEmpty()
            EducadorTurmaDetalheScreen(
                classId = classId,
                educadorViewModel = educadorViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.EDUCADOR_ALUNOS) {
            EducadorAlunosScreen(
                educadorViewModel = educadorViewModel,
                onBack = { navController.popBackStack() },
                onSelectAluno = { studentId ->
                    navController.navigate(Routes.educadorAlunoDetalhe(studentId))
                }
            )
        }
        composable(
            route = Routes.EDUCADOR_ALUNO_DETALHE,
            arguments = listOf(navArgument("studentId") { type = NavType.StringType })
        ) { entry ->
            val studentId = entry.arguments?.getString("studentId").orEmpty()
            EducadorAlunoDetalheScreen(
                studentId = studentId,
                educadorViewModel = educadorViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        // -------------------- Gestao --------------------
        composable(Routes.GESTAO_OVERVIEW) {
            GestaoOverviewScreen(
                authViewModel = authViewModel,
                gestaoViewModel = gestaoViewModel,
                onNavigateCursos = { navController.navigate(Routes.GESTAO_CURSOS) },
                onNavigateEducadores = { navController.navigate(Routes.GESTAO_EDUCADORES) },
                onLogout = { navigateToLogin(navController, authViewModel) }
            )
        }
        composable(Routes.GESTAO_CURSOS) {
            GestaoCursosScreen(
                gestaoViewModel = gestaoViewModel,
                onBack = { navController.popBackStack() },
                onSelectCurso = { id -> navController.navigate(Routes.gestaoCursoDetalhe(id)) }
            )
        }
        composable(
            route = Routes.GESTAO_CURSO_DETALHE,
            arguments = listOf(navArgument("courseId") { type = NavType.StringType })
        ) { entry ->
            val courseId = entry.arguments?.getString("courseId").orEmpty()
            GestaoCursoDetalheScreen(
                courseId = courseId,
                gestaoViewModel = gestaoViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.GESTAO_EDUCADORES) {
            GestaoEducadoresScreen(
                gestaoViewModel = gestaoViewModel,
                onBack = { navController.popBackStack() },
                onSelectEducador = { id ->
                    navController.navigate(Routes.gestaoEducadorDetalhe(id))
                }
            )
        }
        composable(
            route = Routes.GESTAO_EDUCADOR_DETALHE,
            arguments = listOf(navArgument("educatorId") { type = NavType.StringType })
        ) { entry ->
            val educatorId = entry.arguments?.getString("educatorId").orEmpty()
            GestaoEducadorDetalheScreen(
                educatorId = educatorId,
                gestaoViewModel = gestaoViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

private fun navigateToLogin(
    navController: androidx.navigation.NavController,
    authViewModel: AuthViewModel
) {
    authViewModel.signOut()
    navController.navigate(Routes.LOGIN) {
        popUpTo(0) { inclusive = true }
    }
}
