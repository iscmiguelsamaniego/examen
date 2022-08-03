package samtech.org.bussinesssample.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groceries")
class Groceries(
    @ColumnInfo(name = "fiIdZeus") var IdZeus: String?,
    @ColumnInfo(name = "fcIdComercio") var idGrocery: String?,
    @ColumnInfo(name = "nombre") var name: String?,
    @ColumnInfo(name = "descripcion") var description: String?,
    @ColumnInfo(name = "email") var email: String?,
    @ColumnInfo(name = "telefono") var phone: String?,
    @ColumnInfo(name = "tipoComercio") var typeGrocery: Int,
    @ColumnInfo(name = "idGiro") var idBussiness: Int,
    @ColumnInfo(name = "idCategoria") var idCategory: Int,
    @ColumnInfo(name = "idSubcategoria") var idSubcategory: Int,
    @ColumnInfo(name = "idDisponibilidad") var idAvailability: String?,
    @ColumnInfo(name = "idEstatusLevantamiento") var idStatus: Int,
    @ColumnInfo(name = "urlImagen") var urlImage: String?,
    @ColumnInfo(name = "idDistrito") var idDistrict: Int
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}