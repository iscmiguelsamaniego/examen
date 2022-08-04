package samtech.org.bussinesssample.ui.groceries.addresses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import samtech.org.bussinesssample.R
import samtech.org.bussinesssample.Singleton
import samtech.org.bussinesssample.ui.adapters.AddressAdapter


class AdressesFragment : Fragment() {

    private val addressesViewModel: AddressesViewModel by activityViewModels {
        AddressesViewModel.OperatingAddressesViewModelFactory(
            Singleton.instance!!.addressRepository
        )
    }

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_addresses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.fr_addresses_recyclerview)

        loadAddresses()

    }


    fun loadAddresses() {
        val adapter = AddressAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        addressesViewModel.allAddresses.observe(viewLifecycleOwner) { addresses ->
            addresses.let {
                adapter.submitList(it)
            }
        }
    }
}