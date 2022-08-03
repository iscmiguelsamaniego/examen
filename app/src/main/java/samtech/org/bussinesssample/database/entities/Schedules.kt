package samtech.org.bussinesssample.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "schedules")
class Schedules(
    @ColumnInfo(name = "codeDia") var dayCode: String?,
    @ColumnInfo(name = "idTipoHora") var idTypeHour: String?,
    @ColumnInfo(name = "tipoHora") var typeHour: String?,
    @ColumnInfo(name = "hora") var hour: String?
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
