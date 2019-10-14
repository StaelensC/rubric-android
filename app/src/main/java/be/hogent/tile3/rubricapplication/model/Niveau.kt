package be.hogent.tile3.rubricapplication.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(foreignKeys = arrayOf(
    ForeignKey(
        entity = Criterium::class,
        parentColumns = arrayOf("criteriumId"),
        childColumns = arrayOf("criteriumId"))
), tableName = "niveau_table"
)
data class Niveau(
    @PrimaryKey @ColumnInfo(name = "niveauId") val niveauId: String = "",
    @ColumnInfo(name = "titel") val titel: String = "",
    @ColumnInfo(name = "omschrijving") val omschrijving: String = "",
    @ColumnInfo(name = "ondergrens") val ondergrens: Int = 0,
    @ColumnInfo(name = "bovengrens") val bovengrens: Int = 0,
    @ColumnInfo(name = "volgnummer") val volgnummer: Int,
    @ColumnInfo(name = "criteriumId") val criteriumId: String
) : Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(niveauId)
        parcel.writeString(titel)
        parcel.writeString(omschrijving)
        parcel.writeInt(ondergrens)
        parcel.writeInt(bovengrens)
        parcel.writeInt(volgnummer)
        parcel.writeString(criteriumId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Niveau> {
        override fun createFromParcel(parcel: Parcel): Niveau {
            return Niveau(parcel)
        }

        override fun newArray(size: Int): Array<Niveau?> {
            return arrayOfNulls(size)
        }
    }
}