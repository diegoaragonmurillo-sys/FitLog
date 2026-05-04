package com.example.fitlog.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitlog.data.repository.FitLogRepository

class FitLogViewModelFactory(
    private val repository: FitLogRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FitLogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FitLogViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel no encontrado")
    }
}