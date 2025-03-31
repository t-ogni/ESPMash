package com.yakovskij.espmash.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "esp_device")
data class EspDevice(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "ip") val ip: String,
    @ColumnInfo(name = "name") val name: String = "ESP-Device"
)