package samtech.org.bussinesssample.database.repositories

import samtech.org.bussinesssample.database.dao.AddressesDao
import samtech.org.bussinesssample.database.entities.Addresses
import kotlinx.coroutines.flow.Flow

class AddressRepository(private val addressesDao: AddressesDao) {

    suspend fun deleteAdressValues() {
        addressesDao.deleteAll()
    }

    fun getAddresses(): Flow<List<Addresses>> {
        return addressesDao.getAddresses()
    }

    suspend fun insertAddressesValues(addresses: Addresses) {
        return addressesDao.insert(addresses)
    }
}