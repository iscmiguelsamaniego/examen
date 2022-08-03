package samtech.org.bussinesssample.network.pokos

import com.google.gson.annotations.SerializedName

data class GroceryResponse (
    @SerializedName("comercio") var grocery: Grocery,
    @SerializedName("horariosAtencion") var attentionScheduleList: List<AttentionSchedule>
)