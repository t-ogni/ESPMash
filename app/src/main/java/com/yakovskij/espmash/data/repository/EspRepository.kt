
import com.yakovskij.espmash.data.database.EspDeviceDao
import com.yakovskij.espmash.data.database.EspFeatureConfigDao
import com.yakovskij.espmash.data.database.EspFeatureDao
import com.yakovskij.espmash.data.model.EspDevice
import com.yakovskij.espmash.data.model.EspFeature
import com.yakovskij.espmash.data.model.EspFeatureConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EspRepository(
    private val deviceDao: EspDeviceDao,
    private val featureDao: EspFeatureDao,
    private val configDao: EspFeatureConfigDao
) {
    suspend fun getAllDevices(): List<EspDevice> = deviceDao.getAllDevices()

    suspend fun getDeviceWithFeatures(deviceId: Long): Pair<EspDevice, List<EspFeature>>? {
        val device = deviceDao.getDeviceById(deviceId) ?: return null
        val features = featureDao.getFeaturesForDevice(deviceId)
        return device to features
    }
    suspend fun getFeaturesForDevice(deviceId: Long): List<EspFeature> {
        val device = deviceDao.getDeviceById(deviceId) ?: return emptyList()
        return featureDao.getFeaturesForDevice(deviceId)
    }

    suspend fun getFeatureWithConfig(featureId: Long): Pair<EspFeature, List<EspFeatureConfig>>? {
        val feature = featureDao.getFeatureById(featureId) ?: return null
        val configs = configDao.getConfigForFeature(featureId)
        return feature to configs
    }

    fun getFeatureWithConfigFlow(featureId: Long): Flow<Pair<EspFeature, List<EspFeatureConfig>>?> = flow {
        val feature = featureDao.getFeatureById(featureId)
        if (feature != null) {
            val configs = configDao.getConfigForFeature(featureId)
            emit(feature to configs)
        } else {
            emit(null)
        }
    }.flowOn(Dispatchers.IO) // Выполняем в фоновом потоке

    suspend fun saveDeviceWithFeatures(device: EspDevice, features: List<Pair<EspFeature, List<EspFeatureConfig>>>) {
        val deviceId = deviceDao.insertDevice(device)
        features.forEach { (feature, configs) ->
            val featureId = featureDao.insertFeature(feature.copy(deviceId = deviceId))
            configs.forEach { configDao.insertConfig(it.copy(featureId = featureId)) }
        }
    }

}
