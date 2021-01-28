package com.example.baselib.bean

sealed class StateActionEvent

/**
 * 显示
 */
object ShowProgress : StateActionEvent()

/**
 * 隐藏
 */
object DismissProgress : StateActionEvent()

/**
 * 错误提示
 */
class ErrorState(val message: String?) : StateActionEvent()