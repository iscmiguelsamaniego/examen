package samtech.org.bussinesssample.database.repositories

import kotlinx.coroutines.flow.Flow
import samtech.org.bussinesssample.database.dao.EmployeesDao
import samtech.org.bussinesssample.database.entities.Employees

class EmployeesRepository(private val employeesDao: EmployeesDao) {
    suspend fun deleteEmployeesValues() {
        employeesDao.deleteAll()
    }

    fun getEmployees(): Flow<List<Employees>> {
        return employeesDao.getEmployees()
    }

    suspend fun insertEmployeesValues(employees: Employees) {
        return employeesDao.insert(employees)
    }
}