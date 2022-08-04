package samtech.org.bussinesssample.ui.groceries

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso
import kotlinx.coroutines.runBlocking
import samtech.org.bussinesssample.R
import samtech.org.bussinesssample.Singleton
import samtech.org.bussinesssample.Utils.Constants.ALERT
import samtech.org.bussinesssample.Utils.Constants.WARNING
import samtech.org.bussinesssample.Utils.MsgUtils.showCustomToast
import samtech.org.bussinesssample.Utils.NetworkUtils.isOnline
import samtech.org.bussinesssample.Utils.TextUtils.setHtml


class GroceriesFragment : Fragment(), View.OnClickListener {

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


    override fun onStart() {
        super.onStart()

        groceriesViewModel.allGroceries.observe(viewLifecycleOwner) { groceries ->
            if (groceries.size == 0) {
                if (isOnline(context)) {
                    runBlocking {
                        run {
                            groceriesViewModel.clearTables()
                            groceriesViewModel.downloadGroceryValues()
                        }
                    }
                } else {
                    showCustomToast(context as Activity, getString(R.string.no_internet), WARNING)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val background: ImageView = view.findViewById(R.id.fr_grocery_background)
        val titleGrocery: TextView = view.findViewById(R.id.fr_grocery_title)
        val detailsGrocery: TextView = view.findViewById(R.id.fr_grocery_details)
        val addresses: MaterialButton = view.findViewById(R.id.fr_grocery_view_addresses)
        val schedules: MaterialButton = view.findViewById(R.id.fr_grocery_view_schedules)

        addresses.setOnClickListener(this)
        schedules.setOnClickListener(this)

        groceriesViewModel.allGroceries.observe(viewLifecycleOwner) { groceries ->
            if (groceries.size > 0) {
                for (groceriesValues in groceries) {
                    Picasso.get().load(groceriesValues.urlImage).into(background)
                    titleGrocery.setText(groceriesValues.name)

                    setHtml(
                        detailsGrocery,
                        "<html>" +
                                "<body>" +
                                "<strong>fiIdZeus : </strong>" + groceriesValues.id +
                                "<br/>" +
                                "<strong>IdComercio :</strong>" + groceriesValues.idGrocery +
                                "<br/>" +
                                "<strong>Nombre :</strong>" + groceriesValues.name +
                                "<br/>" +
                                "<strong>Descripción :</strong>" + groceriesValues.description +
                                "<br/>" +
                                "<strong>Email :</strong>" + groceriesValues.email +
                                "<br/>" +
                                "<strong>Teléfono :</strong>" + groceriesValues.phone +
                                "<br/>" +
                                "<strong>Tipo de comercio :</strong>" + groceriesValues.typeGrocery +
                                "<br/>" +
                                "<strong>IdGiro :</strong>" + groceriesValues.idBussiness +
                                "<br/>" +
                                "<strong>IdCategoria :</strong>" + groceriesValues.idCategory +
                                "<br/>" +
                                "<strong>IdSubCategoria :</strong>" + groceriesValues.idSubcategory +
                                "<br/>" +
                                "<strong>IdDisponibilidad :</strong>" + groceriesValues.idAvailability +
                                "<br/>" +
                                "<strong>IdEstatusLevantamiento :</strong>" + groceriesValues.idStatus +
                                "<br/>" +
                                "<strong>IdDistrito :</strong>" + groceriesValues.idDistrict +
                                "</body>" +
                                "<html>"
                    )
                }
            } else {
                showCustomToast(context as Activity, getString(R.string.no_download_values), ALERT)
            }
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.fr_grocery_view_addresses) {
            findNavController().navigate(R.id.action_navigation_home_to_adressesFragment)
        }

        if (v?.id == R.id.fr_grocery_view_schedules) {
            findNavController().navigate(R.id.action_navigation_home_to_schedulesFragment)
        }

    }

}
