package id.qibee.catatanhutang.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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
                    "utang_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(UtangDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class UtangDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.utangDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        fun populateDatabase(utangDao: UtangDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            utangDao.deleteAll()

            var utang = Utang(1, "Johan", 200000, "12 Januari 2020")
            utangDao.insert(utang)
            utang = Utang(2, "Amir", 450000, "13 Januari 2020")
            utangDao.insert(utang)
        }
    }
}