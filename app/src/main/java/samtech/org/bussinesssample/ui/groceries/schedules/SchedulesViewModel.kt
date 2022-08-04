package samtech.org.bussinesssample.ui.groceries.schedules

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import samtech.org.bussinesssample.database.entities.Schedules
import samtech.org.bussinesssample.database.repositories.SchedulesRepository

class SchedulesViewModel(schedulesRepository: SchedulesRepository) : ViewModel(){

    val allSchedules: LiveData<List<Schedules>> = schedulesRepository.getSchedules().asLiveData()

    class OperatingSchedulesViewModelFactory(
        private val schedulesRepo: SchedulesRepository
    ): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SchedulesViewModel::class.java)) {
                return SchedulesViewModel(
                    schedulesRepo
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}