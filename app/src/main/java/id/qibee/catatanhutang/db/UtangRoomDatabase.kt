package id.qibee.catatanhutang.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope


// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Utang::class], version = 1, exportSchema = false)
abstract class UtangRoomDatabase : RoomDatabase() {

    abstract fun utangDao(): UtangDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: UtangRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): UtangRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UtangRoomDatabase::class.java,
                    "word_database"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}