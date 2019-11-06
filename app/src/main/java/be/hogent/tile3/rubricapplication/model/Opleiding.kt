package be.hogent.tile3.rubricapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "opleiding_table")
data class Opleiding(

    @PrimaryKey(autoGenerate = true)
    var opleidingId: Int = 0,

    @ColumnInfo(name = "naam")
    var naam: String,

    @ColumnInfo(name = "rubrics")
    var rubrics: Rubric
)