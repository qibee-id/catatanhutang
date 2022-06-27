package id.qibee.catatanhutang.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "utang_table")
class Utang(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val pengutang: String,
    val nominal: Int,
    val jatuhTempo: String
)