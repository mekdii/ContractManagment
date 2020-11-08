package com.gebeya.contractm.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.gebeya.contractm.R
import com.gebeya.contractm.activities.ui.user
import kotlinx.android.synthetic.main.table_layout.view.*



interface CellClickListener {
    fun onCellClickListener(data: Contract?)



    fun onInfoClickListener(data: Contract?)

    fun onEditClickListener(data: Contract?)


}



class CustomAdapter(val userList: ArrayList<Contract>, private val cellClickListener: CellClickListener )  : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.table_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])
        holder.itemView.delete.setOnClickListener {

            cellClickListener.onCellClickListener(userList!![position])
        }
        holder.itemView.info.setOnClickListener {
            cellClickListener.onInfoClickListener(userList!![position])
        }
        holder.itemView.update.setOnClickListener {
            cellClickListener.onEditClickListener(userList!![position])
        }
       // holder.intialize(userList.get(position), clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: Contract) {
            var textViewCategory = itemView.findViewById(R.id.category) as TextView
            val textViewStart_Date = itemView.findViewById(R.id.start_date) as TextView
            var textViewBusiness = itemView.findViewById(R.id.business_partner) as TextView
          //  val textViewStatus = itemView.findViewById(R.id.status) as TextView
//            textViewCategory.text = user.category
//            textViewStart_Date.text = user.start_date
//            textViewBusiness.text = user.Busines_partner
//            textViewStatus.text = user.status
            textViewCategory.text = "Rental"
            textViewStart_Date.text = user.date.toString()
            textViewBusiness.text = user.tFirstName
          //  textViewStatus.text = "Active"
            var id  = user._id





        }

//        fun intialize(item: Contract, action: FragmentActivity?) {
//            var textViewCategory = itemView.findViewById(R.id.category) as TextView
//            val textViewStart_Date = itemView.findViewById(R.id.start_date) as TextView
//            var textViewBusiness = itemView.findViewById(R.id.business_partner) as TextView
//            val textViewStatus = itemView.findViewById(R.id.status) as TextView
//
//            textViewCategory.text = "Rental"
//            textViewStart_Date.text = item.lemail
//            textViewBusiness.text = item.tFirstName
//            textViewStatus.text = "Active"
//
//
//
//
//
//            itemView.setOnClickListener {
//                action.OnItemClick(item , adapterPosition)
//            }
//
//        }

    }


    interface OnNewsItemClickListener {
        fun OnItemClick(item: Contract, position: Int){


        }



    }


}

////

