package com.yakovskij.espmash.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "esp_feature_config",
    foreignKeys = [ForeignKey(
        entity = EspFeature::class,
        parentColumns = ["id"],
        childColumns = ["featureId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("featureId")]
)
data class EspFeatureConfig(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "featureId") val featureId: Long,  // ID фичи (камеры, реле и т.д.)
    @ColumnInfo(name = "configKey") val configKey: String,  // Название параметра (например, "pin", "resolution", "fps")
    @ColumnInfo(name = "configValue") val configValue: String // Значение (например, "13", "1080p", "30")
)
