package samtech.org.bussinesssample.ui.groceries.schedules

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
import samtech.org.bussinesssample.ui.adapters.SchedulesAdapter

class SchedulesFragment : Fragment() {

    private val schedulesViewModel: SchedulesViewModel by activityViewModels {
        SchedulesViewModel.OperatingSchedulesViewModelFactory(
            Singleton.instance!!.schedulesRepository
        )
    }

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedules, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.fr_schedules_recyclerview)
        loadSchedules()
    }

    fun loadSchedules() {
        val adapter = SchedulesAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        schedulesViewModel.allSchedules.observe(viewLifecycleOwner) { schedules ->
            schedules.let {
                adapter.submitList(it)
            }
        }
    }
}