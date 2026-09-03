package IV_ONE_team.com.github.euroone_kotlin.viewmodel

import IV_ONE_team.com.github.euroone_kotlin.model.AlertItem
import IV_ONE_team.com.github.euroone_kotlin.model.EducatorClassInfo
import IV_ONE_team.com.github.euroone_kotlin.model.EducatorProfile
import IV_ONE_team.com.github.euroone_kotlin.model.StudentListItem
import IV_ONE_team.com.github.euroone_kotlin.model.User
import IV_ONE_team.com.github.euroone_kotlin.repository.EducadorRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Estado agregado das telas do educador. */
data class EducadorState(
    val profile: EducatorProfile? = null,
    val classes: List<EducatorClassInfo> = emptyList(),
    val students: List<StudentListItem> = emptyList(),
    val alerts: List<AlertItem> = emptyList()
)

/** ViewModel do perfil Educador. */
class EducadorViewModel(
    private val repository: EducadorRepository = EducadorRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(EducadorState())
    val state: StateFlow<EducadorState> = _state.asStateFlow()

    fun load(user: User) {
        _state.value = EducadorState(
            profile = repository.profile(user),
            classes = repository.classes(),
            students = repository.students(),
            alerts = repository.alerts()
        )
    }

    fun classById(id: String): EducatorClassInfo? = repository.classById(id)
    fun studentById(id: String): StudentListItem? = repository.studentById(id)
}
