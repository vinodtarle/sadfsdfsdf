package com.construction.app.report.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AdapterViewPager(
    fragmentManager: FragmentManager,
    fragments: Array<Fragment>,
    titles: Array<String>
) :
    FragmentPagerAdapter(fragmentManager) {
    private var fragments = emptyArray<Fragment>()
    private var titles = emptyArray<String>()

    init {
        this.fragments = fragments
        this.titles = titles
    }

    override fun getItem(position: Int): Fragment {
        return this.fragments[position]
    }

    override fun getCount(): Int {
        return this.fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return this.titles[position]
    }
}
