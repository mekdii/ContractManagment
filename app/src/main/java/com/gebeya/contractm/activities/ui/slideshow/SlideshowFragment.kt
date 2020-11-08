package com.gebeya.contractm.activities.ui.slideshow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gebeya.contractm.R
import com.gebeya.contractm.activities.Event
import com.gebeya.contractm.activities.Login
import com.gebeya.contractm.activities.Navigation
import com.gebeya.contractm.activities.ui.DetailTemp
import com.gebeya.contractm.activities.ui.gallery.GalleryFragment

import com.gebeya.contractm.activities.ui.home.HomeFragment
import com.gebeya.contractm.databinding.FragmentHomeBinding
import com.gebeya.contractm.databinding.FragmentSlideshowBinding
import com.gebeya.contractm.framework.base.BaseActivity

import kotlinx.android.synthetic.main.fragment_slideshow.*
import kotlinx.android.synthetic.main.fragment_slideshow.view.*


class SlideshowFragment : Fragment() {

   // private lateinit var slideshowViewModel: SlideshowViewModel


 // private  lateinit var binding  : FragmentSlideshowBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       // val view=  inflater?.inflate(R.layout.fragment_slideshow , container , false)

        val view = inflater.inflate(R.layout.fragment_slideshow, container, false)
      view.home_rental.setOnClickListener {
//           // findNavController().navigate(R.id.action_nav_slideshow_to_nav_gallery)
//            val ft = fragmentManager!!.beginTransaction()
//            ft.replace(R.id.nav_host_fragment, GalleryFragment(), "NewFragmentTag")
//            ft.commit()

            activity?.let{
                val intent = Intent (it, DetailTemp::class.java)
               // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                it.startActivity(intent)
            }



        }
        return view


    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//
//        binding.text.setOnClickListener {
//            val nextFrag = Event()
//            activity!!.supportFragmentManager.beginTransaction()
//                .replace(R.id.event, nextFrag, "findThisFragment")
//                .addToBackStack(null)
//                .commit()
//
//
//        }
//    }

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


}