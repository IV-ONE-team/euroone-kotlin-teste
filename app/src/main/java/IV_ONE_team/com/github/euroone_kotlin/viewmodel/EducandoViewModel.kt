package IV_ONE_team.com.github.euroone_kotlin.viewmodel

import IV_ONE_team.com.github.euroone_kotlin.model.ActivityDeadline
import IV_ONE_team.com.github.euroone_kotlin.model.BadgeInfo
import IV_ONE_team.com.github.euroone_kotlin.model.CourseProgress
import IV_ONE_team.com.github.euroone_kotlin.model.EducandoSnapshot
import IV_ONE_team.com.github.euroone_kotlin.model.Mission
import IV_ONE_team.com.github.euroone_kotlin.model.RewardItem
import IV_ONE_team.com.github.euroone_kotlin.model.User
import IV_ONE_team.com.github.euroone_kotlin.repository.EducandoRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Estado agregado das telas do educando. */
data class EducandoState(
    val snapshot: EducandoSnapshot? = null,
    val courses: List<CourseProgress> = emptyList(),
    val missions: List<Mission> = emptyList(),
    val deadlines: List<ActivityDeadline> = emptyList(),
    val rewards: List<RewardItem> = emptyList(),
    val badges: List<BadgeInfo> = emptyList()
)

/**
 * ViewModel responsavel por alimentar as telas do perfil Educando com dados
 * mockados do [EducandoRepository]. Como nao ha operacoes assincronas
 * complexas na Sprint 3, o carregamento e sincrono.
 */
class EducandoViewModel(
    private val repository: EducandoRepository = EducandoRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(EducandoState())
    val state: StateFlow<EducandoState> = _state.asStateFlow()

    fun load(user: User) {
        _state.value = EducandoState(
            snapshot = repository.snapshot(user),
            courses = repository.courses(),
            missions = repository.missions(),
            deadlines = repository.deadlines(),
            rewards = repository.rewards(),
            badges = repository.badges()
        )
    }

    fun courseById(id: String): CourseProgress? = repository.courseById(id)
}
