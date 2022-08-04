package samtech.org.bussinesssample.ui.adapters

import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import samtech.org.bussinesssample.R
import samtech.org.bussinesssample.Utils.MsgUtils.showOSMMapAlert
import samtech.org.bussinesssample.Utils.TextUtils
import samtech.org.bussinesssample.database.entities.Addresses

class AddressAdapter :
    ListAdapter<Addresses, AddressAdapter.AddressViewHolder>(AddressComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val testImage: TextView = itemView.findViewById(R.id.item_address)
        private val viewMap: MaterialButton = itemView.findViewById(R.id.item_address_view_map)

        fun bind(addresses: Addresses) {
            setValues(testImage, addresses)

            viewMap.setOnClickListener {
                val vLocation = Location("")
                vLocation.latitude = addresses.latitude
                vLocation.longitude = addresses.longitude

                showOSMMapAlert(
                    itemView.context,
                    vLocation,
                    addresses.street + "\nNúm. " + addresses.externalNumber + "\n" +
                            addresses.colony + "\n" + addresses.nameMunicipality + "\n" +
                            addresses.stateName + "\n" + addresses.countryName
                )
            }
        }

        fun setValues(paramTView: TextView, addresses: Addresses) {
            TextUtils.setHtml(
                paramTView,
                "<html>" +
                        "<body>" +
                        "<strong>fiIdDireccion : </strong>" +
                        addresses.id +
                        "<br/>" +
                        "<strong>Calle :</strong>" + addresses.street +
                        "<br/>" +
                        "<strong>Número Exterior :</strong>" + addresses.externalNumber +
                        "<br/>" +
                        "<strong>Número Interior :</strong>" + addresses.internalNumber +
                        "<br/>" +
                        "<strong>Código postal :</strong>" + addresses.zipcode +
                        "<br/>" +
                        "<strong>Latitud :</strong>" + addresses.latitude +
                        "<br/>" +
                        "<strong>longitud :</strong>" + addresses.longitude +
                        "<br/>" +
                        "<strong>IdPais :</strong>" + addresses.idCountry +
                        "<br/>" +
                        "<strong>Nombre del Pais :</strong>" + addresses.countryName +
                        "<br/>" +
                        "<strong>idEstado :</strong>" + addresses.idState +
                        "<br/>" +
                        "<strong>Nombre del Estado :</strong>" + addresses.stateName +
                        "<br/>" +
                        "<strong>IdMunicipio :</strong>" + addresses.idMunicipality +
                        "<br/>" +
                        "<strong>Nombre del Municipio :</strong>" + addresses.nameMunicipality +
                        "<br/>" +
                        "<strong>Colonia :</strong>" + addresses.colony +
                        "<br/>" +
                        "<strong>IdColonia :</strong>" + addresses.idColony +
                        "<br/>" +
                        "<strong>Cuadrante :</strong>" + addresses.idQuadrant +
                        "</body>" +
                        "<html>"
            )
        }

        companion object {
            fun create(parent: ViewGroup): AddressViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.address_item, parent, false)
                return AddressViewHolder(view)
            }
        }

    }

    class AddressComparator : DiffUtil.ItemCallback<Addresses>() {
        override fun areItemsTheSame(oldItem: Addresses, newItem: Addresses): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Addresses, newItem: Addresses): Boolean {
            return oldItem.street == newItem.street
        }
    }
}
