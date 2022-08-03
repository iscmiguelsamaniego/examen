package samtech.org.bussinesssample.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import samtech.org.bussinesssample.database.entities.Schedules

@Dao
interface SchedulesDao {
    @Query("SELECT COUNT(*)  FROM schedules")
    fun getSchedulesCount(): Int

    @Query("SELECT * FROM schedules ORDER BY id ASC")
    fun getSchedules(): Flow<List<Schedules>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(schedules: Schedules)

    @Query("DELETE FROM schedules")
    suspend fun deleteAll()
}