package IV_ONE_team.com.github.euroone_kotlin.repository

import IV_ONE_team.com.github.euroone_kotlin.data.MockDataProvider
import IV_ONE_team.com.github.euroone_kotlin.model.AlertItem
import IV_ONE_team.com.github.euroone_kotlin.model.EducatorClassInfo
import IV_ONE_team.com.github.euroone_kotlin.model.EducatorProfile
import IV_ONE_team.com.github.euroone_kotlin.model.StudentListItem
import IV_ONE_team.com.github.euroone_kotlin.model.User

/** Camada de dados do perfil Educador (usa [MockDataProvider]). */
class EducadorRepository {

    fun profile(user: User): EducatorProfile = MockDataProvider.educadorProfile(user)

    fun classes(): List<EducatorClassInfo> = MockDataProvider.educadorClasses

    fun classById(id: String): EducatorClassInfo? =
        MockDataProvider.educadorClasses.firstOrNull { it.id == id }

    fun students(): List<StudentListItem> = MockDataProvider.educadorStudents

    fun studentById(id: String): StudentListItem? =
        MockDataProvider.educadorStudents.firstOrNull { it.id == id }

    fun alerts(): List<AlertItem> = MockDataProvider.educadorAlerts
}
