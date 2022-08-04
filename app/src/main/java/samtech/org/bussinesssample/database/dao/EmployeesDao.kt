package samtech.org.bussinesssample.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import samtech.org.bussinesssample.database.entities.Employees

@Dao
interface EmployeesDao {
    @Query("SELECT COUNT(*) FROM employees")
    fun getEmployeesCount(): Int

    @Query("SELECT * FROM employees ORDER BY id DESC")
    fun getEmployees(): Flow<List<Employees>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(employees: Employees)

    @Query("DELETE FROM employees")
    suspend fun deleteAll()

}