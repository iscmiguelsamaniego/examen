package samtech.org.bussinesssample.ui.addemployee

import androidx.lifecycle.*
import samtech.org.bussinesssample.database.entities.Employees
import samtech.org.bussinesssample.database.repositories.EmployeesRepository
import java.lang.IllegalArgumentException

class EmployeesViewModel(
    private val employeesRepository: EmployeesRepository
) : ViewModel() {

    val allEmployees: LiveData<List<Employees>> = employeesRepository.getEmployees().asLiveData()

    suspend fun insertEmployee(employees: Employees){
        employeesRepository.insertEmployeesValues(employees)
    }

    class OperatingEmployeesViewModelFactory(
        private val employeesRepository: EmployeesRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EmployeesViewModel::class.java)) {
                return EmployeesViewModel(
                    employeesRepository
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}