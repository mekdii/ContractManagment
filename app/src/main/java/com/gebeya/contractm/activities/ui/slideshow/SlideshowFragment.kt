package com.gebeya.contractm.activities.ui.slideshow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.*
import androidx.fragment.app.Fragment
import com.gebeya.contractm.R
import com.gebeya.contractm.activities.Event
import com.gebeya.contractm.activities.Registor
import com.gebeya.contractm.activities.ui.home.HomeFragment
import com.gebeya.contractm.databinding.FragmentHomeBinding
import com.gebeya.contractm.databinding.FragmentSlideshowBinding
import kotlinx.android.synthetic.main.fragment_slideshow.*


class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel


  private  lateinit var binding  : FragmentSlideshowBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       // val view=  inflater?.inflate(R.layout.fragment_slideshow , container , false)

        binding = FragmentSlideshowBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.setOnClickListener {
            d(this::class.simpleName , "text clicked")

        }

//        binding.text.setOnClickListener {
//            val nextFrag = Event()
//            activity!!.supportFragmentManager.beginTransaction()
//                .replace(R.id.event, nextFrag, "findThisFragment")
//                .addToBackStack(null)
//                .commit()


       // }
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        home_rental.setOnClickListener {
//            val nextFrag = Event()
//            activity!!.supportFragmentManager.beginTransaction()
//                .replace(R.id.event, nextFrag, "findThisFragment")
//                .addToBackStack(null)
//                .commit()
//
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.mainmenu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemview = item.itemId
        when(itemview){
//            R.id.search -> Toast.makeText(applicationContext , "this is search" , Toast.LENGTH_SHORT)
//            R.id.person -> Toast.makeText(applicationContext , "person clicked" , Toast.LENGTH_SHORT)
        }
        return false
    }
}