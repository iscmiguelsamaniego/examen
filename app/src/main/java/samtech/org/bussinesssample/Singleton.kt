package samtech.org.bussinesssample

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import samtech.org.bussinesssample.database.BussinessRoomDatabase
import samtech.org.bussinesssample.database.repositories.AddressRepository
import samtech.org.bussinesssample.database.repositories.EmployeesRepository
import samtech.org.bussinesssample.database.repositories.GroceriesRepository
import samtech.org.bussinesssample.database.repositories.SchedulesRepository

class Singleton  : Application(){

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { BussinessRoomDatabase.getDatabase(this, applicationScope) }
    val groceriesRepository by lazy { GroceriesRepository(database.groceriesDao()) }
    val addressRepository by lazy { AddressRepository(database.addressesDao()) }
    val schedulesRepository by lazy { SchedulesRepository(database.schedulesDao()) }
    val employeesRepository by lazy { EmployeesRepository(database.employeesDao()) }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private val TAG = Singleton::class.java.simpleName
        @get:Synchronized var instance: Singleton? = null
            private set
    }
}