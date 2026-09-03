# Plano de commits - EuroOne (Sprint 3 - FIAP)

Este documento organiza o plano de commits do projeto **EuroOne (Kotlin)** distribuindo o trabalho de forma equilibrada entre os 4 integrantes da equipe **IVONE**. Cada integrante possui pelo menos 3 commits significativos, cobrindo escopos coerentes entre si (scaffold, dados, viewmodels/navegação e telas/documentação).

> Antes de iniciar os commits, cada membro deve configurar seu autor local no repositório clonado, para que os commits fiquem atribuídos à pessoa correta:
>
> ```bash
> git config user.name  "Nome Completo"
> git config user.email "email@dominio.com"
> ```

---

## Karine Nascimento - Scaffold e identidade visual

### Commit 1
```bash
git add settings.gradle.kts build.gradle.kts gradle.properties gradle/ .gitignore \
        gradlew gradlew.bat
git commit -m "chore: scaffold inicial do projeto Android (Gradle + wrapper)"
```
Cria a base do projeto Kotlin: `build.gradle.kts` (root e app), `settings.gradle.kts`, `gradle.properties`, catálogo `libs.versions.toml`, wrapper e `.gitignore`.

### Commit 2
```bash
git add app/build.gradle.kts app/proguard-rules.pro app/src/main/AndroidManifest.xml \
        app/src/main/res/values/ app/src/main/res/xml/ \
        app/src/main/res/mipmap-* app/src/main/res/drawable/ic_launcher_*
git commit -m "chore: manifest, gradle do app e recursos (icones + strings + backup)"
```
Configura o `AndroidManifest.xml`, `build.gradle.kts` do módulo `app` (namespace `IV_ONE_team.com.github.euroone_kotlin`), ícones de launcher e `strings.xml`/`colors.xml`/`themes.xml`.

### Commit 3
```bash
git add app/src/main/java/IV_ONE_team/com/github/euroone_kotlin/ui/theme/ \
        app/src/main/res/drawable-nodpi/euri_mascot.png \
        app/src/main/res/drawable-nodpi/euri_logo.png
git commit -m "feat(theme): paleta EuroPalette + tipografia + tema Material 3 + mascote Euri"
```
Adiciona `Color.kt` (paleta completa Eurofarma), `Type.kt` (tipografia Material 3) e `Theme.kt` (`EuroOneTheme`), além do mascote **Euri** e do logo em `drawable-nodpi/`.

---

## Lucas Almeida Bel Correa - Modelo de domínio, mocks e repositórios

### Commit 4
```bash
git add app/src/main/java/IV_ONE_team/com/github/euroone_kotlin/model/
git commit -m "feat(model): data classes de dominio (User, Educando, Educador, Gestao)"
```
Cria os modelos: `UserRole`, `User`, `EducandoModels`, `EducadorModels`, `GestaoModels`.

### Commit 5
```bash
git add app/src/main/java/IV_ONE_team/com/github/euroone_kotlin/data/MockDataProvider.kt
git commit -m "feat(data): MockDataProvider com usuarios demo e mocks Eurofarma"
```
Adiciona `MockDataProvider` com 5 usuários de demonstração (senha `123456`), cursos, missões, recompensas, turmas, alunos, alertas, métricas executivas e fila de cuidado.

### Commit 6
```bash
git add app/src/main/java/IV_ONE_team/com/github/euroone_kotlin/repository/
git commit -m "feat(repository): camadas Auth, Educando, Educador e Gestao"
```
Cria `AuthRepository` (com `delay` simulando rede), `EducandoRepository`, `EducadorRepository` e `GestaoRepository`, todos consumindo o `MockDataProvider`.

---

## Guilherme Tusita - ViewModels, navegação e tela de login

### Commit 7
```bash
git add app/src/main/java/IV_ONE_team/com/github/euroone_kotlin/viewmodel/
git commit -m "feat(viewmodel): AuthViewModel + ViewModels dos tres perfis (MVVM + StateFlow)"
```
Adiciona `AuthViewModel` (com `sealed class AuthUiState`), `EducandoViewModel`, `EducadorViewModel` e `GestaoViewModel` com `StateFlow`.

### Commit 8
```bash
git add app/src/main/java/IV_ONE_team/com/github/euroone_kotlin/navigation/ \
        app/src/main/java/IV_ONE_team/com/github/euroone_kotlin/MainActivity.kt
git commit -m "feat(navigation): rotas centralizadas + NavHost multi-perfil + MainActivity"
```
Cria `Routes.kt` (rotas centralizadas com builders para parâmetros), `AppNavigation.kt` (NavHost com todas as telas e passagem de parâmetros) e `MainActivity.kt`.

### Commit 9
```bash
git add app/src/main/java/IV_ONE_team/com/github/euroone_kotlin/ui/components/ \
        app/src/main/java/IV_ONE_team/com/github/euroone_kotlin/ui/screens/auth/
git commit -m "feat(ui): componentes reutilizaveis + tela de login com Euri e demo accounts"
```
Adiciona componentes reutilizáveis (`EuroCard`, `EuroTopBar`, `MetricCard`, `SectionHeader`, `StatusChip`) e a `LoginScreen` completa com gradiente noturno, mascote Euri e painel de acessos de demonstração.

---

## Matheus Richard Hadermeck - Telas dos três perfis e documentação

### Commit 10
```bash
git add app/src/main/java/IV_ONE_team/com/github/euroone_kotlin/ui/screens/educando/
git commit -m "feat(ui/educando): home, curso, recompensas e perfil do educando"
```
Adiciona as 4 telas do perfil Educando: `EducandoHomeScreen`, `EducandoCursoScreen`, `EducandoRecompensasScreen` e `EducandoPerfilScreen`.

### Commit 11
```bash
git add app/src/main/java/IV_ONE_team/com/github/euroone_kotlin/ui/screens/educador/ \
        app/src/main/java/IV_ONE_team/com/github/euroone_kotlin/ui/screens/gestao/
git commit -m "feat(ui/educador+gestao): dashboards, listagens e detalhes dos dois perfis"
```
Adiciona as 5 telas do perfil Educador (Overview, Turmas, TurmaDetalhe, Alunos, AlunoDetalhe) e as 5 telas do perfil Gestão (Overview, Cursos, CursoDetalhe, Educadores, EducadorDetalhe).

### Commit 12
```bash
git add README.md commits.md images/
git commit -m "docs: README com equipe IVONE, prints de execucao, credenciais demo e plano de commits"
```
Finaliza o projeto com o `README.md` (equipe IVONE, credenciais de teste, telas, stack), os prints reais das telas capturados no emulador (pasta `images/`) e este `commits.md`.

---

## Resumo por integrante

| Integrante                           | Commits | Escopo principal                              |
|--------------------------------------|---------|-----------------------------------------------|
| Karine Nascimento                    | 1, 2, 3 | Scaffold, Gradle, identidade visual/tema      |
| Lucas Almeida Bel Correa             | 4, 5, 6 | Modelo de domínio, mocks e repositórios       |
| Guilherme Tusita                     | 7, 8, 9 | ViewModels, navegação e tela de login         |
| Matheus Richard Hadermeck            | 10-12   | Telas dos três perfis e documentação          |

**Total**: 12 commits significativos + eventuais commits de ajustes pontuais durante a integração.

> Observação: as mensagens de commit acima estão sem acento porque é uma convenção comum evitar caracteres não-ASCII em `git commit -m` para prevenir problemas de encoding em diferentes ambientes/plataformas. O texto descritivo dos commits neste arquivo segue a norma culta da língua portuguesa.
