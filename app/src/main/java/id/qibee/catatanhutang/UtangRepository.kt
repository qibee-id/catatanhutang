package id.qibee.catatanhutang

import androidx.annotation.WorkerThread
import id.qibee.catatanhutang.db.Utang
import id.qibee.catatanhutang.db.UtangDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UtangRepository(private val utangDao: UtangDao) {

    val semuaUtang: Flow<List<Utang>> = utangDao.getListUtang()

    @Suppress
    @WorkerThread
    suspend fun insert(utang: Utang) = withContext(Dispatchers.IO){
        utangDao.insert(utang)
    }
}