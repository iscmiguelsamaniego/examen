package samtech.org.bussinesssample.network.pokos

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("fiIdDireccion") var id: String,
    @SerializedName("calle") var street: String,
    @SerializedName("numeroExterior") var externalNumber: String,
    @SerializedName("numeroInterior") var internalNumber: String,
    @SerializedName("codigoPostal") var zipcode: String,
    @SerializedName("latitud") var latitude: Double,
    @SerializedName("longitud") var longitude: Double,
    @SerializedName("idPais") var idCountry: Int,
    @SerializedName("nombrePais") var countryName: String,
    @SerializedName("idEstado") var idState: Int,
    @SerializedName("nombreEstado") var stateName: String,
    @SerializedName("idMunicipio") var idMunicipality: Int,
    @SerializedName("nombreMunicipio") var nameMunicipality: String,
    @SerializedName("colonia") var colony: String,
    @SerializedName("idColonia") var idColony: Int,
    @SerializedName("idCuadrante") var idQuadrant: Int
)