package com.oguzhancetin.kitgames.viewModel

import android.content.Context
import android.view.View
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhancetin.kitgames.util.dataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    ViewModel() {


    private val PROGRESS_KEY = intPreferencesKey("progress_key")

    val progressFlow: Flow<Int> = context.dataStore.data
        .map {
            it[PROGRESS_KEY] ?: 0
        }

    fun addProgress(progress: Int) {
        viewModelScope.launch {
            writeProgress(progress)
        }
    }
    suspend fun clearProgress(){
        context.dataStore.edit {
            val progress: Int = it[PROGRESS_KEY] ?: 0
            it[PROGRESS_KEY] =  0
        }
    }

    private suspend fun writeProgress(newProgress: Int) {
        context.dataStore.edit {
            val progress: Int = it[PROGRESS_KEY] ?: 0
            it[PROGRESS_KEY] = progress + newProgress
        }
    }
}