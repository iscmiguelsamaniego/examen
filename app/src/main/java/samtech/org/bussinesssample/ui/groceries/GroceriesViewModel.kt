package samtech.org.bussinesssample.ui.groceries

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Response
import samtech.org.bussinesssample.database.entities.Addresses
import samtech.org.bussinesssample.database.entities.Groceries
import samtech.org.bussinesssample.database.entities.Schedules
import samtech.org.bussinesssample.database.repositories.AddressRepository
import samtech.org.bussinesssample.database.repositories.GroceriesRepository
import samtech.org.bussinesssample.database.repositories.SchedulesRepository
import samtech.org.bussinesssample.interfaces.MessagesGroceryListener
import samtech.org.bussinesssample.network.APIService
import samtech.org.bussinesssample.network.RetrofitClient
import samtech.org.bussinesssample.network.pokos.GroceryRequest
import samtech.org.bussinesssample.network.pokos.GroceryResponse
import java.lang.IllegalArgumentException

class GroceriesViewModel(
    private val groceriesRepository: GroceriesRepository,
    private val addressesRepository: AddressRepository,
    private val schedulesRepository: SchedulesRepository
) : ViewModel() {

    val allGroceries: LiveData<List<Groceries>> = groceriesRepository.getGroceries().asLiveData()

    suspend fun clearTables(){
        groceriesRepository.deleteGroceryValues()
        addressesRepository.deleteAdressValues()
        schedulesRepository.deleteScheduleValues()
    }

     suspend fun downloadGroceryValues() {
        val retrofit = RetrofitClient.getInstance()
        val apiService = retrofit.create(APIService::class.java)
        val request = GroceryRequest("149010")

        apiService.getGroceryStores(request).enqueue(
            object : retrofit2.Callback<GroceryResponse> {
                override fun onResponse(
                    call: Call<GroceryResponse>,
                    response: Response<GroceryResponse>
                ) {
                    if (response.isSuccessful) {
                        val groceryResponse = response.body()!!.grocery

                        val groceriesObj = Groceries(
                            groceryResponse.id,
                            groceryResponse.idGrocery,
                            groceryResponse.name,
                            groceryResponse.description,
                            groceryResponse.email,
                            groceryResponse.phone,
                            groceryResponse.typeGrocery,
                            groceryResponse.idBussiness,
                            groceryResponse.idCategory,
                            groceryResponse.idSubcategory,
                            groceryResponse.idAvailability,
                            groceryResponse.idStatus,
                            groceryResponse.urlImage,
                            groceryResponse.idDistrict
                        )

                       viewModelScope.launch {
                           groceriesRepository.insertGroceryValues(groceriesObj)

                           for (address in groceryResponse.addressList) {
                               val addressObj = Addresses(
                                   address.id,
                                   address.street,
                                   address.externalNumber,
                                   address.internalNumber,
                                   address.zipcode,
                                   address.latitude,
                                   address.longitude,
                                   address.idCountry,
                                   address.countryName,
                                   address.idState,
                                   address.stateName,
                                   address.idMunicipality,
                                   address.nameMunicipality,
                                   address.colony,
                                   address.idColony,
                                   address.idQuadrant)

                               addressesRepository.insertAddressesValues(addressObj)
                           }


                           for (schedules in response.body()!!.attentionScheduleList) {
                               val schedulesObj = Schedules(
                                    schedules.dayCode,
                                   schedules.idTypeHour,
                                   schedules.typeHour,
                                   schedules.hour,
                               )
                               schedulesRepository.insertSchedulesValues(schedulesObj)
                           }

                       }
                    }
                }

                override fun onFailure(call: Call<GroceryResponse>, t: Throwable) {
                    Log.d("", "" + t.localizedMessage)
                }
            }
        )
    }

    class OperatingGroceriesViewModelFactory(
        private val groceriesRepo: GroceriesRepository,
        private val addressesRepo: AddressRepository,
        private val schedulesRepo: SchedulesRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GroceriesViewModel::class.java)) {
                return GroceriesViewModel(
                    groceriesRepo,
                    addressesRepo,
                    schedulesRepo
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}