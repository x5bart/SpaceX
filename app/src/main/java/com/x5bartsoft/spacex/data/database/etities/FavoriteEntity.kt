package com.x5bartsoft.spacex.data.database.etities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.x5bartsoft.spacex.model.response.launches.Doc
import com.x5bartsoft.spacex.util.Constants.Companion.LAUNCHES_FAVORITE_TABLE

@Entity(tableName = LAUNCHES_FAVORITE_TABLE)
class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var launch: Doc,
) {
}