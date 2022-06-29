package id.qibee.catatanhutang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.qibee.catatanhutang.db.Utang

class UtangRvAdapter : ListAdapter<Utang, UtangRvAdapter.UtangViewHolder>(UTANGS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UtangViewHolder {
        return UtangViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UtangViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.pengutang)
    }

    class UtangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val UtangItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            UtangItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): UtangViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return UtangViewHolder(view)
            }
        }
    }

    companion object {
        private val UTANGS_COMPARATOR = object : DiffUtil.ItemCallback<Utang>() {
            override fun areItemsTheSame(oldItem: Utang, newItem: Utang): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Utang, newItem: Utang): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}