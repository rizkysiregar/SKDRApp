package com.rizkysiregar.skdrapp.ui

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.rizkysiregar.skdrapp.MainActivity
import com.rizkysiregar.skdrapp.add.AddDataActivity
import org.junit.Before
import com.rizkysiregar.skdrapp.R
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AddActivityTest {
    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    private fun getTopActivity() : Activity? {
        var activity : Activity? = null
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance()
                .getActivitiesInStage(Stage.RESUMED)
            if (resumedActivities.iterator().hasNext()){
                resumedActivities.iterator().next()?.let {
                    activity = it
                }
            }
        }
        return activity
    }

    @Test
    fun displayAddDataActivity(){
        onView(withId(R.id.fab_add)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add)).perform(click())

        // confirm
        val currentActivity = getTopActivity()
        Assert.assertTrue(currentActivity?.javaClass == AddDataActivity::class.java)
    }
}