package IV_ONE_team.com.github.euroone_kotlin.repository

import IV_ONE_team.com.github.euroone_kotlin.data.MockDataProvider
import IV_ONE_team.com.github.euroone_kotlin.model.CareQueueItem
import IV_ONE_team.com.github.euroone_kotlin.model.DashboardMetric
import IV_ONE_team.com.github.euroone_kotlin.model.EducatorSummary
import IV_ONE_team.com.github.euroone_kotlin.model.ManagedCourse
import IV_ONE_team.com.github.euroone_kotlin.model.ManagementProfile
import IV_ONE_team.com.github.euroone_kotlin.model.User

/** Camada de dados do perfil Gestao (usa [MockDataProvider]). */
class GestaoRepository {

    fun profile(user: User): ManagementProfile = MockDataProvider.gestaoProfile(user)

    fun metrics(): List<DashboardMetric> = MockDataProvider.gestaoMetrics

    fun courses(): List<ManagedCourse> = MockDataProvider.gestaoCourses

    fun courseById(id: String): ManagedCourse? =
        MockDataProvider.gestaoCourses.firstOrNull { it.id == id }

    fun educators(): List<EducatorSummary> = MockDataProvider.gestaoEducators

    fun educatorById(id: String): EducatorSummary? =
        MockDataProvider.gestaoEducators.firstOrNull { it.id == id }

    fun careQueue(): List<CareQueueItem> = MockDataProvider.gestaoCareQueue
}
