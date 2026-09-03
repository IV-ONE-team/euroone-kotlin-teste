package IV_ONE_team.com.github.euroone_kotlin.viewmodel

import IV_ONE_team.com.github.euroone_kotlin.model.CareQueueItem
import IV_ONE_team.com.github.euroone_kotlin.model.DashboardMetric
import IV_ONE_team.com.github.euroone_kotlin.model.EducatorSummary
import IV_ONE_team.com.github.euroone_kotlin.model.ManagedCourse
import IV_ONE_team.com.github.euroone_kotlin.model.ManagementProfile
import IV_ONE_team.com.github.euroone_kotlin.model.User
import IV_ONE_team.com.github.euroone_kotlin.repository.GestaoRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Estado agregado das telas de gestao. */
data class GestaoState(
    val profile: ManagementProfile? = null,
    val metrics: List<DashboardMetric> = emptyList(),
    val courses: List<ManagedCourse> = emptyList(),
    val educators: List<EducatorSummary> = emptyList(),
    val careQueue: List<CareQueueItem> = emptyList()
)

/** ViewModel do perfil Gestao. */
class GestaoViewModel(
    private val repository: GestaoRepository = GestaoRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(GestaoState())
    val state: StateFlow<GestaoState> = _state.asStateFlow()

    fun load(user: User) {
        _state.value = GestaoState(
            profile = repository.profile(user),
            metrics = repository.metrics(),
            courses = repository.courses(),
            educators = repository.educators(),
            careQueue = repository.careQueue()
        )
    }

    fun courseById(id: String): ManagedCourse? = repository.courseById(id)
    fun educatorById(id: String): EducatorSummary? = repository.educatorById(id)
}
