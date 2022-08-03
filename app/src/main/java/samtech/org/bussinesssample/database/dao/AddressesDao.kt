package samtech.org.bussinesssample.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import samtech.org.bussinesssample.database.entities.Addresses

@Dao
interface AddressesDao {
    @Query("SELECT COUNT(*)  FROM addresses")
    fun getAddressesCount(): Int


    @Query("SELECT * FROM addresses ORDER BY id ASC")
    fun getAddresses(): Flow<List<Addresses>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(addresses: Addresses)

    @Query("DELETE FROM addresses")
    suspend fun deleteAll()

}