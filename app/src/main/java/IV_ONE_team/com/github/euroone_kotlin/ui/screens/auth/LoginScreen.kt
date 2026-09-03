package IV_ONE_team.com.github.euroone_kotlin.ui.screens.auth

import IV_ONE_team.com.github.euroone_kotlin.R
import IV_ONE_team.com.github.euroone_kotlin.data.MockDataProvider
import IV_ONE_team.com.github.euroone_kotlin.ui.theme.EuroPalette
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.AuthUiState
import IV_ONE_team.com.github.euroone_kotlin.viewmodel.AuthViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Tela de login do EuroOne. Reproduz a identidade visual do prototipo
 * original: fundo com gradiente noturno da marca, mascote Euri em destaque
 * e cartao com credenciais.
 *
 * Usuarios de demonstracao (senha padrao "123456") estao exibidos na parte
 * inferior para facilitar o teste durante a avaliacao da Sprint 3.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(authViewModel: AuthViewModel) {
    val uiState by authViewModel.uiState.collectAsStateWithLifecycle()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(email, password) {
        authViewModel.clearError()
    }

    val nightGradient = Brush.verticalGradient(
        colors = listOf(
            EuroPalette.Brand800,
            EuroPalette.Brand900,
            EuroPalette.Brand950
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(nightGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "EuroOne",
                style = MaterialTheme.typography.displayLarge,
                color = EuroPalette.Ink0,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Instituto Eurofarma - Jornada de aprendizagem",
                style = MaterialTheme.typography.bodyMedium,
                color = EuroPalette.Brand100
            )

            Spacer(Modifier.height(24.dp))

            // Mascote Euri, referencia visual da marca.
            Image(
                painter = painterResource(id = R.drawable.euri_mascot),
                contentDescription = "Mascote Euri",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(180.dp)
            )

            Spacer(Modifier.height(24.dp))

            // Cartao de credenciais.
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = EuroPalette.Ink0,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(20.dp)
            ) {
                Column {
                    Text(
                        text = "Entrar",
                        style = MaterialTheme.typography.headlineMedium,
                        color = EuroPalette.Brand800
                    )
                    Text(
                        text = "Acesse com seu email institucional.",
                        style = MaterialTheme.typography.bodySmall,
                        color = EuroPalette.Ink500
                    )
                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        colors = eurofieldColors()
                    )
                    Spacer(Modifier.height(12.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Senha") },
                        singleLine = true,
                        visualTransformation = if (passwordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) {
                                        Icons.Default.VisibilityOff
                                    } else {
                                        Icons.Default.Visibility
                                    },
                                    contentDescription = "Alternar visibilidade da senha"
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = eurofieldColors()
                    )
                    Spacer(Modifier.height(16.dp))

                    if (uiState is AuthUiState.Error) {
                        Text(
                            text = (uiState as AuthUiState.Error).message,
                            style = MaterialTheme.typography.bodySmall,
                            color = EuroPalette.Critical
                        )
                        Spacer(Modifier.height(8.dp))
                    }

                    Button(
                        onClick = { authViewModel.signIn(email, password) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EuroPalette.Brand500,
                            contentColor = EuroPalette.Ink0
                        ),
                        shape = RoundedCornerShape(12.dp),
                        enabled = uiState !is AuthUiState.Loading
                    ) {
                        if (uiState is AuthUiState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = EuroPalette.Ink0,
                                strokeWidth = 2.dp
                            )
                            Spacer(Modifier.width(12.dp))
                            Text("Entrando...")
                        } else {
                            Text(
                                text = "Entrar",
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Ajuda de demonstracao.
            DemoAccountsPanel(onQuickFill = { demoEmail ->
                email = demoEmail
                password = MockDataProvider.DEMO_PASSWORD
            })

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Desenvolvido pela equipe IV-ONE",
                style = MaterialTheme.typography.bodySmall,
                color = EuroPalette.Brand200
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun eurofieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = EuroPalette.Brand500,
    unfocusedBorderColor = EuroPalette.Ink300,
    focusedLabelColor = EuroPalette.Brand500,
    cursorColor = EuroPalette.Brand500
)

/** Painel com os usuarios de demonstracao (facilita a avaliacao da Sprint). */
@Composable
private fun DemoAccountsPanel(onQuickFill: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = EuroPalette.Brand800.copy(alpha = 0.6f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Acessos de demonstracao",
                style = MaterialTheme.typography.titleMedium,
                color = EuroPalette.Ink0
            )
            Text(
                text = "Senha padrao: 123456 (toque em um perfil para preencher)",
                style = MaterialTheme.typography.bodySmall,
                color = EuroPalette.Brand100
            )
            Spacer(Modifier.height(12.dp))
            MockDataProvider.demoUsers.forEach { user ->
                DemoAccountRow(
                    role = user.role.label,
                    email = user.email,
                    onClick = { onQuickFill(user.email) }
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun DemoAccountRow(role: String, email: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = EuroPalette.Brand700,
            contentColor = EuroPalette.Ink0
        ),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = role,
                style = MaterialTheme.typography.labelLarge,
                color = EuroPalette.Yellow
            )
            Text(
                text = email,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
