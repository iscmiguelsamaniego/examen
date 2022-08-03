package samtech.org.bussinesssample.ui.groceries

import android.app.Activity
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.runBlocking
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import samtech.org.bussinesssample.R
import samtech.org.bussinesssample.Singleton
import samtech.org.bussinesssample.Utils.Constants
import samtech.org.bussinesssample.Utils.Constants.WARNING
import samtech.org.bussinesssample.Utils.MapUtils.setMap
import samtech.org.bussinesssample.Utils.MsgUtils.showCustomToast
import samtech.org.bussinesssample.Utils.NetworkUtils.isOnline
import samtech.org.bussinesssample.Utils.TextUtils.setHtml
import samtech.org.bussinesssample.network.APIService
import samtech.org.bussinesssample.network.RetrofitClient
import samtech.org.bussinesssample.network.pokos.GroceryRequest
import samtech.org.bussinesssample.network.pokos.GroceryResponse


class GroceriesFragment : Fragment() {
    private lateinit var map: MapView

    private val groceriesViewModel: GroceriesViewModel by activityViewModels {
        GroceriesViewModel.OperatingGroceriesViewModelFactory(
            Singleton.instance!!.groceriesRepository,
            Singleton.instance!!.addressRepository,
            Singleton.instance!!.schedulesRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_grocery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val titleGrocery: TextView = view.findViewById(R.id.fr_grocery_title)
       /* val imageGrocery: ImageView = view.findViewById(R.id.fr_grocery_image)
        val generalData: TextView = view.findViewById(R.id.fr_grocery_general_data)
        val addressData: TextView = view.findViewById(R.id.fr_grocery_address_data)
        val scheduleData: TextView = view.findViewById(R.id.fr_grocery_schedule_data)*/

        titleGrocery.setText("check this values")
        runBlocking {
            run {
                if (isOnline(context)) {
                    groceriesViewModel.clearTables()
                    groceriesViewModel.downloadGroceryValues()
                }else{
                    showCustomToast(context as Activity, getString(R.string.no_internet), WARNING)
                }


                //todo show values
            }
        }
        //MAP STARS
       // map = view.findViewById(R.id.map)
       // map.setTileSource(TileSourceFactory.MAPNIK)
        //Configuration.getInstance().userAgentValue = "value"
        //MAP ENDS
        //getGroceries(imageGrocery, generalData, addressData)
    }

    private fun getGroceries(pramImage: ImageView, paramData: TextView, paramAddress: TextView) {
        val retrofit = RetrofitClient.getInstance()
        val apiService = retrofit.create(APIService::class.java)

        lifecycleScope.launchWhenCreated {
            try {
                val request = GroceryRequest("149010")

                apiService.getGroceryStores(request).enqueue(
                    object : Callback<GroceryResponse> {
                        override fun onFailure(call: Call<GroceryResponse>, t: Throwable) {
                            Log.d("", "" + t.localizedMessage)
                        }

                        override fun onResponse(
                            call: Call<GroceryResponse>,
                            response: Response<GroceryResponse>
                        ) {
                            if (response.isSuccessful) {

                                val grocery = response.body()!!.grocery
                                Picasso.get().load(grocery.urlImage).into(pramImage)

                                setHtml(
                                    paramData,
                                    "<html>" +
                                            "<body>" +
                                            "<strong>fiIdZeus : </strong>" + grocery.id +
                                            "<br/>" +
                                            "<strong>IdComercio :</strong>" + grocery.idGrocery +
                                            "<br/>" +
                                            "<strong>Nombre :</strong>" + grocery.name +
                                            "<br/>" +
                                            "<strong>Descripción :</strong>" + grocery.description +
                                            "<br/>" +
                                            "<strong>Email :</strong>" + grocery.email +
                                            "<br/>" +
                                            "<strong>Teléfono :</strong>" + grocery.phone +
                                            "<br/>" +
                                            "<strong>Tipo de comercio :</strong>" + grocery.typeGrocery +
                                            "<br/>" +
                                            "<strong>IdGiro :</strong>" + grocery.idBussiness +
                                            "<br/>" +
                                            "<strong>IdCategoria :</strong>" + grocery.idCategory +
                                            "<br/>" +
                                            "<strong>IdSubCategoria :</strong>" + grocery.idSubcategory +
                                            "<br/>" +
                                            "<strong>IdDisponibilidad :</strong>" + grocery.idAvailability +
                                            "<br/>" +
                                            "<strong>IdEstatusLevantamiento :</strong>" + grocery.idStatus +
                                            "<br/>" +
                                            "<strong>IdDistrito :</strong>" + grocery.idDistrict +
                                            "</body>" +
                                            "<html>"
                                )

                                for (address in grocery.addressList) {

                                }
                                setHtml(
                                    paramAddress,
                                    "<html>" +
                                            "<body>" +
                                            "<strong>fiIdDireccion : </strong>" +
                                            grocery.addressList.get(0).id +
                                            "<br/>" +
                                            "<strong>Calle :</strong>" + grocery.addressList.get(0).street +
                                            "<br/>" +
                                            "<strong>Número Exterior :</strong>" + grocery.addressList.get(
                                        0
                                    ).externalNumber +
                                            "<br/>" +
                                            "<strong>Número Interior :</strong>" + grocery.addressList.get(
                                        0
                                    ).internalNumber +
                                            "<br/>" +
                                            "<strong>Código postal :</strong>" + grocery.addressList.get(
                                        0
                                    ).zipcode +
                                            "<br/>" +
                                            "<strong>Latitud :</strong>" + grocery.addressList.get(0).latitude +
                                            "<br/>" +
                                            "<strong>longitud :</strong>" + grocery.addressList.get(
                                        0
                                    ).longitude +
                                            "<br/>" +
                                            "<strong>IdPais :</strong>" + grocery.addressList.get(0).idCountry +
                                            "<br/>" +
                                            "<strong>Nombre del Pais :</strong>" + grocery.addressList.get(
                                        0
                                    ).countryName +
                                            "<br/>" +
                                            "<strong>idEstado :</strong>" + grocery.addressList.get(
                                        0
                                    ).idState +
                                            "<br/>" +
                                            "<strong>Nombre del Estado :</strong>" + grocery.addressList.get(
                                        0
                                    ).stateName +
                                            "<br/>" +
                                            "<strong>IdMunicipio :</strong>" + grocery.addressList.get(
                                        0
                                    ).idMunicipality +
                                            "<br/>" +
                                            "<strong>Nombre del Municipio :</strong>" + grocery.addressList.get(
                                        0
                                    ).nameMunicipality +
                                            "<br/>" +
                                            "<strong>Colonia :</strong>" + grocery.addressList.get(0).colony +
                                            "<br/>" +
                                            "<strong>IdColonia :</strong>" + grocery.addressList.get(
                                        0
                                    ).idColony +
                                            "<br/>" +
                                            "<strong>Cuadrante :</strong>" + grocery.addressList.get(
                                        0
                                    ).idQuadrant +
                                            "</body>" +
                                            "<html>"
                                )
                                val vLocation = Location("")
                                vLocation.latitude = grocery.addressList.get(0).latitude
                                vLocation.longitude = grocery.addressList.get(0).longitude
                                setMap(
                                    map,
                                    false,
                                    false,
                                    17.5,
                                    vLocation,
                                    ContextCompat.getDrawable(
                                        context!!,
                                        R.drawable.ic_action_marker
                                    ),
                                    "HERE COMES"
                                )
                            }
                        }
                    }
                )

            } catch (Ex: Exception) {
                Ex.localizedMessage?.let { Log.e("Error", it) }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        map.onDetach()
    }

    override fun onResume() {
        super.onResume()
        map.onResume();
    }

    override fun onPause() {
        super.onPause()
        map.onPause();
    }
}
