package samtech.org.bussinesssample.database.entities

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "addresses")
class Addresses(
    @ColumnInfo(name = "fiIdDireccion") var idDireccion: String?,
    @ColumnInfo(name = "calle") var street: String?,
    @ColumnInfo(name = "numeroExterior") var externalNumber: String?,
    @ColumnInfo(name = "numeroInterior") var internalNumber: String?,
    @ColumnInfo(name = "codigoPostal") var zipcode: String?,
    @ColumnInfo(name = "latitud") var latitude: Double,
    @ColumnInfo(name = "longitud") var longitude: Double,
    @ColumnInfo(name = "idPais") var idCountry: Int,
    @ColumnInfo(name = "nombrePais") var countryName: String?,
    @ColumnInfo(name = "idEstado") var idState: Int,
    @ColumnInfo(name = "nombreEstado") var stateName: String?,
    @ColumnInfo(name = "idMunicipio") var idMunicipality: Int,
    @ColumnInfo(name = "nombreMunicipio") var nameMunicipality: String?,
    @ColumnInfo(name = "colonia") var colony: String?,
    @ColumnInfo(name = "idColonia") var idColony: Int,
    @ColumnInfo(name = "idCuadrante") var idQuadrant: Int
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
