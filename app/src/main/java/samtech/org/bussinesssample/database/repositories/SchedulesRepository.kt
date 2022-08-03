package samtech.org.bussinesssample.database.repositories

import kotlinx.coroutines.flow.Flow
import samtech.org.bussinesssample.database.dao.SchedulesDao
import samtech.org.bussinesssample.database.entities.Schedules

class SchedulesRepository(private val schedulesDao: SchedulesDao) {

    suspend fun deleteScheduleValues() {
        schedulesDao.deleteAll()
    }

    fun getSchedules(): Flow<List<Schedules>> {
        return schedulesDao.getSchedules()
    }

    suspend fun insertSchedulesValues(schedules: Schedules) {
        return schedulesDao.insert(schedules)
    }
}