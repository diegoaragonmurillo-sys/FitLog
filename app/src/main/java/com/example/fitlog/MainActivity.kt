package com.example.fitlog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitlog.data.local.FitLogDatabase
import com.example.fitlog.data.repository.FitLogRepository
import com.example.fitlog.ui.navigation.AppNavigation
import com.example.fitlog.ui.viewmodel.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = FitLogDatabase.getDatabase(this)
        val repo = FitLogRepository(db.dao())
        val factory = FitLogViewModelFactory(repo)

        setContent {
            val vm: FitLogViewModel = viewModel(factory = factory)
            AppNavigation(vm)
        }
    }
}