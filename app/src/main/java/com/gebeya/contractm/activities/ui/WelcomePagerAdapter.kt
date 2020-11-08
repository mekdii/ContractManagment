package com.gebeya.contractm.activities.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class WelcomePagerAdapter(
    manager: FragmentManager,
    val images: Array<Int>
) :
    FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        val fragment = WelcomeFragment()


        val args = Bundle()
        args.putInt(KEY_IMAGE, images[position])

        fragment.arguments = args

        return fragment

/*        return WelcomeFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_IMAGE, images[position])
            }
        }*/
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }
}