package IV_ONE_team.com.github.euroone_kotlin.repository

import IV_ONE_team.com.github.euroone_kotlin.data.MockDataProvider
import IV_ONE_team.com.github.euroone_kotlin.model.User
import kotlinx.coroutines.delay

/**
 * Repositorio de autenticacao. Nesta Sprint 3 nao ha backend integrado:
 * o repositorio consulta [MockDataProvider] e simula latencia de rede
 * com um pequeno delay, mantendo o contrato assincrono esperado pelo
 * ViewModel (que se apoia em corrotinas).
 */
class AuthRepository {

    /**
     * Tenta autenticar o usuario pelo par email/senha nos dados mockados.
     * Retorna o [User] correspondente ou `null` se as credenciais forem
     * invalidas.
     */
    suspend fun signIn(email: String, password: String): User? {
        // Simula um roundtrip de rede.
        delay(400)
        return MockDataProvider.findUser(email = email, password = password)
    }
}
