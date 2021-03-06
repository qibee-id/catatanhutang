package id.qibee.catatanhutang.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UtangDao {
    @Query("SELECT * FROM utang_table ORDER BY jatuh_tempo ASC")
    fun getListUtang(): Flow<List<Utang>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(utang: Utang)

    @Query("DELETE FROM utang_table")
    fun deleteAll()

}