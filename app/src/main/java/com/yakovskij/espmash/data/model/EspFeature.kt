package com.yakovskij.espmash.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "esp_feature",
    foreignKeys = [ForeignKey(
        entity = EspDevice::class,
        parentColumns = ["id"],
        childColumns = ["deviceId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("deviceId")]
)
data class EspFeature(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "deviceId") val deviceId: Long,  // Ссылка на устройство
    @ColumnInfo(name = "deviceType") val deviceType: String  // Например, "camera", "relay", "led"
)

