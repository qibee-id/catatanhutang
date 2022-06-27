package id.qibee.catatanhutang

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import id.qibee.catatanhutang.db.Utang
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UtangViewModel(private val repository: UtangRepository) : ViewModel() {
    val allWords: LiveData<List<Utang>> = repository.semuaUtang.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    suspend fun insert(utang: Utang) = viewModelScope.launch {
        repository.insert(utang)
    }
}

class WordViewModelFactory(private val repository: UtangRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UtangViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UtangViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}