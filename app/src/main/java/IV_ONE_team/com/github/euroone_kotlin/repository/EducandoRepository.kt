package IV_ONE_team.com.github.euroone_kotlin.repository

import IV_ONE_team.com.github.euroone_kotlin.data.MockDataProvider
import IV_ONE_team.com.github.euroone_kotlin.model.ActivityDeadline
import IV_ONE_team.com.github.euroone_kotlin.model.BadgeInfo
import IV_ONE_team.com.github.euroone_kotlin.model.CourseProgress
import IV_ONE_team.com.github.euroone_kotlin.model.EducandoSnapshot
import IV_ONE_team.com.github.euroone_kotlin.model.Mission
import IV_ONE_team.com.github.euroone_kotlin.model.RewardItem
import IV_ONE_team.com.github.euroone_kotlin.model.User

/** Camada de dados do perfil Educando (usa [MockDataProvider]). */
class EducandoRepository {

    fun snapshot(user: User): EducandoSnapshot =
        MockDataProvider.educandoSnapshot(user)

    fun courses(): List<CourseProgress> = MockDataProvider.educandoCourses

    fun courseById(id: String): CourseProgress? =
        MockDataProvider.educandoCourses.firstOrNull { it.id == id }

    fun missions(): List<Mission> = MockDataProvider.educandoMissions

    fun deadlines(): List<ActivityDeadline> = MockDataProvider.educandoDeadlines

    fun rewards(): List<RewardItem> = MockDataProvider.educandoRewards

    fun badges(): List<BadgeInfo> = MockDataProvider.educandoBadges
}
