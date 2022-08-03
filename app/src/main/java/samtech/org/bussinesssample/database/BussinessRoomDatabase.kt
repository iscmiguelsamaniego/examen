package samtech.org.bussinesssample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import samtech.org.bussinesssample.database.dao.AddressesDao
import samtech.org.bussinesssample.database.dao.GroceriesDao
import samtech.org.bussinesssample.database.dao.SchedulesDao
import samtech.org.bussinesssample.database.entities.Addresses
import samtech.org.bussinesssample.database.entities.Groceries
import samtech.org.bussinesssample.database.entities.Schedules

@Database(
    entities = arrayOf(Groceries::class, Addresses::class, Schedules::class),
    version = 1,
    exportSchema = false
)
abstract class BussinessRoomDatabase : RoomDatabase(){
    abstract fun groceriesDao(): GroceriesDao
    abstract fun addressesDao(): AddressesDao
    abstract fun schedulesDao(): SchedulesDao

    companion object {
        @Volatile
        private var INSTANCE: BussinessRoomDatabase? = null

        fun getDatabase(
            context: Context, scope: CoroutineScope
        ): BussinessRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BussinessRoomDatabase::class.java,
                    "bussiness_database"
                )
                    .addCallback(BussinessDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class BussinessDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                   // database.clearAllTables()
                }
            }
        }
    }
}