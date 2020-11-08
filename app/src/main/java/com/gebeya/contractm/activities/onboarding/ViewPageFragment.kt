package com.gebeya.contractm.activities.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gebeya.contractm.R
import com.gebeya.contractm.activities.onboarding.screens.FirstScreen
import com.gebeya.contractm.activities.onboarding.screens.SecondScreen
import com.gebeya.contractm.activities.onboarding.screens.ThirdScreen
import kotlinx.android.synthetic.main.fragment_view_page.view.*


class ViewPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_page, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPageAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.viewPager.adapter = adapter

        return view
    }

}


