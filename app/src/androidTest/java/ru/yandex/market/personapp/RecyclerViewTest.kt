package ru.yandex.market.personapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.yandex.market.personapp.presentation.mvp.view.MainActivity

@RunWith(AndroidJUnit4::class)
class RecyclerViewTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewVisibility() {
        Thread.sleep(IDLE_TIME)
        onView(withId(R.id.list))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewPosition() {
        Thread.sleep(IDLE_TIME)
        var numberOfItems = 0

        activityRule.scenario.onActivity {
            val recycler: RecyclerView = it.findViewById(R.id.list)
            numberOfItems = recycler.adapter?.itemCount ?: 0
        }
        onView(withId(R.id.list))
            .perform(RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(numberOfItems - 1))
    }

    companion object {
        private const val IDLE_TIME = 2000L
    }

}
