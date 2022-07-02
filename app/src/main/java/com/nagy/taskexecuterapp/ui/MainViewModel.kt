package com.nagy.taskexecuterapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nagy.taskexecuterapp.ui.model.UITaskLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val executor : ExecutorService) : ViewModel() {


    val viewEffects: SharedFlow<UITaskLog> get() = _viewEffects
    private val _viewEffects = MutableSharedFlow<UITaskLog>()



    var task1 = Runnable {
        println("Executing Task1 inside : " + Thread.currentThread().name)
        try {
            TimeUnit.SECONDS.sleep(2)
        } catch (ex: InterruptedException) {
            throw IllegalStateException(ex)
        }
        sendLog(UITaskLog("task 1 " , getCurrentTime()))

    }

    var task2 = Runnable {
        println("Executing Task2 inside : " + Thread.currentThread().name)
        try {
            TimeUnit.SECONDS.sleep(4)
        } catch (ex: InterruptedException) {
            throw IllegalStateException(ex)
        }
        sendLog(UITaskLog("task 2 " , getCurrentTime()))

    }

    var task3 = Runnable {
        println("Executing Task3 inside : " + Thread.currentThread().name)
        try {
            TimeUnit.SECONDS.sleep(3)
        } catch (ex: InterruptedException) {
            throw IllegalStateException(ex)
        }
        sendLog(UITaskLog("task 3 " , getCurrentTime()))

    }

    var task4 = Runnable {
        println("Executing Task4 inside : " + Thread.currentThread().name)
        try {
            TimeUnit.SECONDS.sleep(5)
        } catch (ex: InterruptedException) {
            throw IllegalStateException(ex)
        }
        sendLog(UITaskLog("task 4 " , getCurrentTime()))
    }


    fun onEvent(task: RunTaskEvent) {

        when(task) {
            RunTaskEvent.TaskOne -> executor.submit(task1)
            RunTaskEvent.TaskTwo -> executor.submit(task2)
            RunTaskEvent.TaskTree -> executor.submit(task3)
            RunTaskEvent.TaskFour -> executor.submit(task4)
        }
    }

    private fun getCurrentTime(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        return sdf.format(Date())
    }

    private fun sendLog(taskLog : UITaskLog){

        viewModelScope.launch {
            _viewEffects.emit(taskLog)
        }
    }
}