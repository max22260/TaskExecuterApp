package com.nagy.taskexecuterapp.ui

sealed class RunTaskEvent {
    object TaskOne : RunTaskEvent()
    object TaskTwo : RunTaskEvent()
    object TaskTree : RunTaskEvent()
    object TaskFour : RunTaskEvent()
}
