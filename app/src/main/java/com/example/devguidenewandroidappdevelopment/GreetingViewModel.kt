package com.example.devguidenewandroidappdevelopment

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class GreetingViewModel : ViewModel() {
    private val _greetingDetails = getGreetingDetail().toMutableStateList()
    val greetingDetail: List<GreetingDetail>
        get() = _greetingDetails

    fun remove(item: GreetingDetail) {
        _greetingDetails.remove(item)
    }
}

private fun getGreetingDetail() = List(1000) { GreetingDetail(it, "#$it") }
