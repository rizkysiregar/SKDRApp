package com.rizkysiregar.skdrapp.home

import androidx.core.os.bundleOf
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest{
    @Test fun testHomeFragment(){
        val fragment = bundleOf("selectedItem" to 0)
//        val scenario = launchFragment<HomeFragment>()
    }
}