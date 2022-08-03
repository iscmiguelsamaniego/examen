package samtech.org.bussinesssample.network.pokos

import com.google.gson.annotations.SerializedName

data class AttentionSchedule(
    @SerializedName("codeDia") var dayCode: String,
    @SerializedName("idTipoHora") var idTypeHour: String,
    @SerializedName("tipoHora") var typeHour: String,
    @SerializedName("hora") var hour: String,
)