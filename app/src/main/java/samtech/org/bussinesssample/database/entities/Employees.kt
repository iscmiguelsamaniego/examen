package samtech.org.bussinesssample.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
class Employees(
    @ColumnInfo(name = "number") var number: String?,
    @ColumnInfo(name = "fullname") var fullname: String?,
    @ColumnInfo(name = "phone") var phone: String?,
    @ColumnInfo(name = "email") var email: String?,
    @ColumnInfo(name = "address") var address: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}