package id.qibee.catatanhutang

import androidx.annotation.WorkerThread
import id.qibee.catatanhutang.db.Utang
import id.qibee.catatanhutang.db.UtangDao
import id.qibee.catatanhutang.db.UtangRoomDatabase
import kotlinx.coroutines.flow.Flow

class UtangRepository(private val utangDao: UtangDao) {

    val semuaUtang: Flow<List<Utang>> = utangDao.getListUtang()

    @Suppress
    @WorkerThread
    suspend fun insert(utang: Utang){
        utangDao.insert(utang)
    }
}