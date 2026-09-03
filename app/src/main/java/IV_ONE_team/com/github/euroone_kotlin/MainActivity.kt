package IV_ONE_team.com.github.euroone_kotlin

import IV_ONE_team.com.github.euroone_kotlin.navigation.AppNavigation
import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroOneTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

/**
 * Activity unica do EuroOne (padrao single-activity + Jetpack Compose).
 *
 * A navegacao entre telas dos tres perfis (Educando, Educador, Gestao) e
 * feita pelo [AppNavigation] via NavHost. A activity apenas monta o tema
 * institucional Eurofarma e delega para o grafo de navegacao.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EuroOneTheme {
                AppNavigation()
            }
        }
    }
}
