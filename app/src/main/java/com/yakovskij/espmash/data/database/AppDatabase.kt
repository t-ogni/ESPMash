package com.yakovskij.espmash.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yakovskij.espmash.data.model.EspDevice
import com.yakovskij.espmash.data.model.EspFeature
import com.yakovskij.espmash.data.model.EspFeatureConfig


@Database(entities = [EspDevice::class, EspFeature::class, EspFeatureConfig::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun espDeviceDao(): EspDeviceDao
    abstract fun espFeatureDao(): EspFeatureDao
    abstract fun espFeatureConfigDao(): EspFeatureConfigDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
