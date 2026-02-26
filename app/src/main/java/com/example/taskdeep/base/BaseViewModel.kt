package com.example.taskdeep.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * @param State UI 상태
 * @param Event 사용자 액션
 * @param SideEffect 일회성 이벤트 (네비게이션, 토스트 등)
 */
abstract class BaseViewModel<State : BaseContract.UiState, Event : BaseContract.Event, SideEffect : BaseContract.SideEffect> :
    ViewModel() {

    // UI 상태
    protected abstract val _state: MutableStateFlow<State>
    val state: StateFlow<State> get() = _state.asStateFlow()
    private val currentUiState: State get() = state.value

    // 사이드 이펙트 (네비게이션 등)
    private val _sideEffect: Channel<SideEffect> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    /**
     * 이벤트 처리
     */
    abstract suspend fun handleEvent(event: Event)

    /**
     * 사이드 이펙트 발행
     */
    protected fun postSideEffect(effect: SideEffect) {
        viewModelScope.launch {
            _sideEffect.send(effect)
        }
    }

    /**
     * 이벤트 전달
     */
    fun intent(event: Event) {
        viewModelScope.launch {
            handleEvent(event)
        }
    }

    /**
     * 상태 업데이트
     */
    protected fun reduce(reduce: State.() -> State) {
        val state = currentUiState.reduce()
        _state.value = state
    }
}