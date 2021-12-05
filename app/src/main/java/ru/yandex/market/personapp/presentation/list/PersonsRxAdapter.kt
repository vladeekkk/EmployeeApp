package ru.yandex.market.personapp.presentation.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.yandex.market.personapp.R
import ru.yandex.market.personapp.presentation.viewobjects.PersonViewObject

class PersonsRxAdapter(var context: Context) :
    PagingDataAdapter<PersonViewObject, PersonsRxAdapter.PersonViewHolder>(COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(LayoutInflater.from(context).inflate
            (R.layout.item_person, parent, false))
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val PHOTO_SIZE: Int = 120

        private val tvName: TextView = itemView.findViewById(R.id.tvPersonName)
        private val tvLogin: TextView = itemView.findViewById(R.id.tvPersonLogin)
        private val tvCityAge: TextView = itemView.findViewById(R.id.tvCityAge)
        private val ivPhoto: ImageView = itemView.findViewById(R.id.imageViewPerson)
        fun bind(person: PersonViewObject) {
            tvName.text = person.fullName
            tvLogin.text = person.login
            tvCityAge.text = person.cityAgeString

            Glide.with(itemView.context)
                .load(person.photoUrl)
                .placeholder(R.drawable.ic_baseline_person_outline_24)
                .override(PHOTO_SIZE, PHOTO_SIZE)
                .circleCrop()
                .into(ivPhoto)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PersonViewObject>() {
            override fun areItemsTheSame(
                oldItem: PersonViewObject, newItem: PersonViewObject
            ): Boolean {
                return oldItem.login == newItem.login
            }

            override fun areContentsTheSame(
                oldItem: PersonViewObject, newItem: PersonViewObject
            ): Boolean {
                return oldItem.login == newItem.login
            }
        }
    }
}


