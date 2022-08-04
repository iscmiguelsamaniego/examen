package samtech.org.bussinesssample.ui.groceries.addresses

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import samtech.org.bussinesssample.database.entities.Addresses
import samtech.org.bussinesssample.database.repositories.AddressRepository

import java.lang.IllegalArgumentException

class AddressesViewModel(addressesRepository: AddressRepository) : ViewModel(){

    val allAddresses: LiveData<List<Addresses>> = addressesRepository.getAddresses().asLiveData()

    class OperatingAddressesViewModelFactory(
        private val addressesRepo: AddressRepository
    ): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddressesViewModel::class.java)) {
                return AddressesViewModel(
                    addressesRepo
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}