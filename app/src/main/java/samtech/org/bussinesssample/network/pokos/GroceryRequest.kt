package samtech.org.bussinesssample.network.pokos

import com.google.gson.annotations.SerializedName

data class GroceryRequest(
    @SerializedName("idZeusComercio") var id: String,
)