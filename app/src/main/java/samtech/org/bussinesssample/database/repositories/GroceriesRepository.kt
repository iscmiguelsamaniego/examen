package samtech.org.bussinesssample.database.repositories

import kotlinx.coroutines.flow.Flow
import samtech.org.bussinesssample.database.dao.GroceriesDao
import samtech.org.bussinesssample.database.entities.Groceries

class GroceriesRepository(private val groceriesDao: GroceriesDao) {

    suspend fun deleteGroceryValues() {
        groceriesDao.deleteAll()
    }

    fun getGroceries(): Flow<List<Groceries>> {
        return groceriesDao.getGroceries()
    }

    suspend fun insertGroceryValues(groceries: Groceries) {
        return groceriesDao.insert(groceries)
    }
}