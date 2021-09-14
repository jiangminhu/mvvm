package com.example.weightdemo.adpater

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPageAdapter(fm: FragmentManager, fragments: List<Fragment>, titles: List<String>) :
    FragmentPagerAdapter(fm) {
    private val fragments = fragments
    private val titles = titles

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}