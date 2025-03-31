package com.yakovskij.espmash.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yakovskij.espmash.data.model.EspDevice
import com.yakovskij.espmash.data.model.EspFeature
import com.yakovskij.espmash.data.model.EspFeatureConfig

@Dao
interface EspDeviceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevice(device: EspDevice): Long

    @Query("SELECT * FROM esp_device")
    suspend fun getAllDevices(): List<EspDevice>

    @Query("SELECT * FROM esp_device WHERE id=:deviceId")
    suspend fun getDeviceById(deviceId: Long): EspDevice?
}

@Dao
interface EspFeatureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeature(feature: EspFeature): Long

    @Query("SELECT * FROM esp_feature")
    suspend fun getAllFeatures(): List<EspFeature>

    @Query("SELECT * FROM esp_feature WHERE deviceId = :deviceId")
    suspend fun getFeaturesForDevice(deviceId: Long): List<EspFeature>

    @Query("SELECT * FROM esp_feature WHERE id = :featureId")
    suspend fun getFeatureById(featureId: Long): EspFeature?
}

@Dao
interface EspFeatureConfigDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfig(config: EspFeatureConfig)

    @Query("SELECT * FROM esp_feature_config")
    suspend fun getAllFeatureConfigs(): List<EspFeatureConfig>

    @Query("SELECT * FROM esp_feature_config WHERE id = :id")
    suspend fun getFeatureConfigById(id: Long): EspFeatureConfig?

    @Query("SELECT * FROM esp_feature_config WHERE featureId = :featureId")
    suspend fun getConfigForFeature(featureId: Long): List<EspFeatureConfig>

    @Query("SELECT * FROM esp_feature_config WHERE featureId = :featureId AND configKey = :key")
    suspend fun getConfigValue(featureId: Long, key: String): EspFeatureConfig?
}
