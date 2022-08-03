package samtech.org.bussinesssample.network.pokos

import com.google.gson.annotations.SerializedName

class Grocery(
    @SerializedName("fiIdZeus") var id: String,
    @SerializedName("fcIdComercio") var idGrocery: String,
    @SerializedName("nombre") var name: String,
    @SerializedName("descripcion") var description: String,
    @SerializedName("email") var email: String,
    @SerializedName("telefono") var phone: String,
    @SerializedName("tipoComercio") var typeGrocery: Int,
    @SerializedName("idGiro") var idBussiness: Int,
    @SerializedName("idCategoria") var idCategory: Int,
    @SerializedName("idSubcategoria") var idSubcategory: Int,
    @SerializedName("idDisponibilidad") var idAvailability: String,
    @SerializedName("idEstatusLevantamiento") var idStatus: Int,
    @SerializedName("urlImagen") var urlImage: String,
    @SerializedName("direcciones") var addressList: List<Address>,
    @SerializedName("idDistrito") var idDistrict: Int
)