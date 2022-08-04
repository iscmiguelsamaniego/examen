package samtech.org.bussinesssample.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import samtech.org.bussinesssample.R
import samtech.org.bussinesssample.Utils.TextUtils
import samtech.org.bussinesssample.database.entities.Schedules

class SchedulesAdapter : ListAdapter<Schedules, SchedulesAdapter.SchedulesViewholder>(
    SchedulesComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchedulesViewholder {
        return SchedulesViewholder.create(parent)
    }

    override fun onBindViewHolder(holder: SchedulesViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class SchedulesViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val testImage: TextView = itemView.findViewById(R.id.item_schedules)

        fun bind(schedules: Schedules) {
            setValues(testImage, schedules)
        }


        fun setValues(paramTView: TextView, schedules: Schedules) {
            TextUtils.setHtml(
                paramTView,
                "<html>" +
                        "<body>" +
                        "<strong>codeDia : </strong>" +
                        schedules.dayCode +
                        "<br/>" +
                        "<strong>idTipoHora :</strong>" + schedules.idTypeHour +
                        "<br/>" +
                        "<strong>tipoHora :</strong>" + schedules.typeHour +
                        "<br/>" +
                        "<strong>hora :</strong>" + schedules.hour +
                        "</body>" +
                        "<html>"
            )
        }

        companion object {
            fun create(parent: ViewGroup): SchedulesViewholder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.schedules_item, parent, false)
                return SchedulesViewholder(view)
            }
        }

    }

    class SchedulesComparator : DiffUtil.ItemCallback<Schedules>() {
        override fun areItemsTheSame(oldItem: Schedules, newItem: Schedules): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Schedules, newItem: Schedules): Boolean {
            return oldItem.id == newItem.id
        }
    }
}