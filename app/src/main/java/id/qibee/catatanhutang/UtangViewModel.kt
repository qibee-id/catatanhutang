package id.qibee.catatanhutang

import androidx.lifecycle.*
import id.qibee.catatanhutang.db.Utang
import kotlinx.coroutines.launch

class UtangViewModel(private val repository: UtangRepository) : ViewModel() {
    val allUtangs: LiveData<List<Utang>> = repository.semuaUtang.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(utang: Utang) = viewModelScope.launch {
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