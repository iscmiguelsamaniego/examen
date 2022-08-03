package samtech.org.bussinesssample.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import samtech.org.bussinesssample.database.entities.Groceries

@Dao
interface GroceriesDao {
    @Query("SELECT COUNT(*) FROM groceries")
    fun getGroceriesCount(): Int

    @Query("SELECT * FROM groceries ORDER BY id DESC")
    fun getGroceries(): Flow<List<Groceries>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(groceries: Groceries)

    @Delete
    suspend fun deleteGroceries(groceries: Groceries)

    @Query("DELETE FROM groceries")
    suspend fun deleteAll()
}