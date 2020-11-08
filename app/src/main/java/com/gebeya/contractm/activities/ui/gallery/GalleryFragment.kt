package com.gebeya.contractm.activities.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gebeya.contractm.R
import com.gebeya.contractm.activities.Navigation
import com.gebeya.contractm.activities.ui.FormContract
import com.gebeya.contractm.activities.ui.ShowForm
import com.gebeya.contractm.api.RetrofitClient
import com.gebeya.contractm.models.CellClickListener
import com.gebeya.contractm.models.Contract
import com.gebeya.contractm.models.CustomAdapter
import com.gebeya.contractm.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_form_contract.*
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import kotlinx.android.synthetic.main.table_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GalleryFragment : Fragment(), CellClickListener {
    private lateinit var sessionManager: SharedPrefManager
    private var contractList = arrayListOf<Contract>()
    private var finalList = arrayListOf<String>()

    //private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        sessionManager = activity?.let { SharedPrefManager(it) }!!
        getAllReport()
        //  fragmentManager!!.beginTransaction().remove(HomeFragment()).commit();


//        view.logout.setOnClickListener {
//            findNavController().navigate(R.id.action_nav_gallery_to_signin)
//
//        }

        //adding a layoutmanager
        view.recyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        //search.setText(sessionManager.fetchAuthToken()?.getValue(SharedPrefManager.USER_NAME))

        //crating an arraylist to store users using the data class user
        //  val users = ArrayList<Contract>()

        //adding some dummy data to the list
//        users.add(Contract("Belal Khan", "addis abeba", "hi", "active"))
//        users.add(Contract("Ramiz Khan", "bdr", "ho", "ha"))
//        users.add(Contract("Faiz Khan", "kosober", "he", "la"))
//        users.add(Contract("Yashar Khan", "lalibela", "lom", "lam"))
//
//        //creating our adapter
//        val adapter = CustomAdapter(users, activity)


        //now adding the adapter to recyclerview
        // view.recyclerView.adapter = adapter


        return view
    }

    override fun onCellClickListener(data: Contract?) {

        activity?.let { it1 ->
            RetrofitClient.getApiService(it1).deleteForm(data!!._id)
                .enqueue(object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Toast.makeText(activity, "PLease Try Again!!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.isSuccessful) {

                            Toast.makeText(activity, "successfully deleted", Toast.LENGTH_LONG)
                                .show()

                        }

                    }
                })
        }
//
//        if (data != null) {
//            Toast.makeText(activity, data._id, Toast.LENGTH_SHORT).show()
//        }

        //  Log.d("fuck" , data!!.lFirstName )
//        if (data != null) {
//            Toast.makeText(activity, "lihed new", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            Toast.makeText(activity, "shint bet", Toast.LENGTH_SHORT).show()
//
//        }


        //  deleteForms()

        //  Toast.makeText(activity, "worked", Toast.LENGTH_SHORT).show()

    }

    override fun onInfoClickListener(data: Contract?) {


        activity?.let {
            val intent = Intent(it, FormContract::class.java)
            intent.putExtra("SOME_FLAG", true)
            intent.putExtra("id", data!!._id)
            intent.putExtra("lFname", data!!.lFirstName)
            intent.putExtra("lLname", data!!.lLastName)
            intent.putExtra("lemail", data!!.lemail)
            intent.putExtra("lphone", data!!.lPhone)
            intent.putExtra("address", data!!.address)
            intent.putExtra("tFname", data!!.tFirstName)
            intent.putExtra("tLname", data!!.tLastName)
            intent.putExtra("temail", data!!.temail)

            intent.putExtra("tphone", data!!.tPhone)
            intent.putExtra("country", data!!.country)
            intent.putExtra("state", data!!.state)
            intent.putExtra("city", data!!.city)

            intent.putExtra("postal", data!!.postal)
            intent.putExtra("startdate", data!!.startDate)
            intent.putExtra("enddate", data!!.endDate)
            intent.putExtra("payperiod", data!!.payPeriod)

            intent.putExtra("rentamount", data!!.rentAmount)
            intent.putExtra("securitiyDeposit", data!!.securityDeposit)
            intent.putExtra("latecharge", data!!.lateCharge)
            intent.putExtra("paymentMethod", data!!.paymentMethod)

            intent.putExtra("collector", data!!.collector)
            intent.putExtra("terms", data!!.terms)
            intent.putExtra("lsgnature", data!!.lSignature)
            intent.putExtra("tsignature", data!!.tSignature)
            // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            it.startActivity(intent)
        }


        //  Toast.makeText(activity, "info icon clicked", Toast.LENGTH_SHORT).show()

    }

    override fun onEditClickListener(data: Contract?) {
        onInfoClickListener(data)

//                activity?.let{
//            val intent = Intent (it, FormContract::class.java)
//            // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            it.startActivity(intent)
//        }

//        activity?.let { it1 ->
//            RetrofitClient.getApiService(it1).updateForm(data!!._id)
//                .enqueue(object : Callback<String> {
//                    override fun onFailure(call: Call<String>, t: Throwable) {
//                        Toast.makeText(activity, "PLease Try Again!!", Toast.LENGTH_SHORT).show()
//                    }
//                    override fun onResponse(call: Call<String>, response: Response<String>) {
//                        if (response.isSuccessful) {
//                            onInfoClickListener(data)
//                            activity?.let{
//                           val intent = Intent (it, FormContract::class.java)
//                          // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                           it.startActivity(intent)
//        }
//
//
//                            Toast.makeText(activity, "successfully updated", Toast.LENGTH_LONG).show()
//
//                        }
//
//                    }
//                }) }
    }
//        activity?.let{
//            val intent = Intent (it, FormContract::class.java)
//            // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            it.startActivity(intent)
//        }
//
//        Toast.makeText(activity, "edit icon clicked", Toast.LENGTH_SHORT).show()
//    }

//    override fun OnItemClick(item: Contract, position: Int) {
//        Toast.makeText(activity, "worked", Toast.LENGTH_SHORT).show()
//        //Toast.makeText(this , "worked fine " , Toast.LENGTH_SHORT).show()
////        val intent =  Intent(this , Detail::class.java)
////        intent.putExtra("name" , item.name)
////        Log.d("fuck" , KEY_SELECTED_NAME)
////        intent.putExtra("address", item.address)
////        startActivity(intent)
//
//
//    }
//    private fun deleteForms(){
//    for (i in contractList){
//        val id = i._id
//        Toast.makeText(activity , id, Toast.LENGTH_LONG).show()


//    activity?.let { it1 ->
//        RetrofitClient.getApiService(it1).deleteForm(id)
//            .enqueue(object : Callback<String> {
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    Toast.makeText(activity, "PLease Try Again!!", Toast.LENGTH_SHORT).show()
//                }
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    if (response.isSuccessful) {
//
//                        Toast.makeText(activity, "successfully deleted", Toast.LENGTH_LONG).show()
//
//                    }
//
//                }
//            }) }

    private fun getAllReport() {


        activity?.let { it1 ->
            RetrofitClient.getApiService(it1).fetchRentals()
                .enqueue(object : Callback<ArrayList<Contract>> {

                    override fun onFailure(call: Call<ArrayList<Contract>>, t: Throwable) {
                        Toast.makeText(activity, "PLease Try Again!!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<ArrayList<Contract>>, response: Response<ArrayList<Contract>>
                    ) {
                        if (response.isSuccessful) {
                            //Toast.makeText(activity, response.message(), Toast.LENGTH_LONG).show()
                            contractList = response.body()!!

//                           Toast.makeText(activity, "${contractList}", Toast.LENGTH_LONG).show()

//                            for(  i in contractList) {
//                                val name = i.tFirstName
//                                val category = "Rental"
//                                val status = "active"
//                                val date = i.lemail
//                                Log.e("name", i.tFirstName);
//                                finalList.add(category)
//                                finalList.add(date)
//                                finalList.add(name)
//                                finalList.add(status)
//                                val users = ArrayList<Contract>()
//                                users.add(Contract(category, date, name, status))
//                            }

                            val adapter = CustomAdapter(contractList, this@GalleryFragment)
                            view?.recyclerView?.adapter = adapter
                            Log.e("Success", response.body().toString())

                        }

                    }

                })
        }
    }
}



