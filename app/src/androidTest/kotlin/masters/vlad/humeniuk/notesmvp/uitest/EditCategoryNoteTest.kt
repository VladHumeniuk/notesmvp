package masters.vlad.humeniuk.notesmvp.uitest

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import masters.vlad.humeniuk.notesmvp.R
import masters.vlad.humeniuk.notesmvp.domain.utils.CategoryUtil
import masters.vlad.humeniuk.notesmvp.presentation.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditCategoryNoteTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testEdit() {
        var avg = 0L
//        for (i in 1..10) {
            createNote()

            var time = System.currentTimeMillis()

            onView(ViewMatchers.withText("new test note")).perform(click())
            onView(ViewMatchers.withId(R.id.title)).perform(clearText()).perform(closeSoftKeyboard())
            onView(ViewMatchers.withId(R.id.description)).perform(clearText())

            onView(ViewMatchers.withId(R.id.title))
                    .perform(typeText("edit test note"))
                    .perform(closeSoftKeyboard())
            onView(ViewMatchers.withId(R.id.description))
                    .perform(typeText("test edit note description"))
            onView(ViewMatchers.withId(R.id.action_save)).perform(click())
            avg += System.currentTimeMillis() - time
            onView(withText("edit test note")).check(matches(isDisplayed()))

            removeNote()
//        }
//        avg /= 10;
//        Log.i("AVERAGE", "" + avg);
    }

    @Test
    fun testEmptyDialog() {
        createNote()

        onView(ViewMatchers.withText("new test note")).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.title)).perform(clearText()).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.action_save)).perform(click())
        onView(withText(R.string.empty_note_fields_error)).check(matches(isDisplayed()))
        onView(withText(R.string.ok_label)).perform(click())
        onView(ViewMatchers.withId(R.id.title))
                .perform(typeText("edit test note"))
                .perform(ViewActions.closeSoftKeyboard())

        removeNote()
    }

    @Test
    fun testDeleteNote() {
        var avg = 0L
//        for (i in 1..10) {
            createNote()
            var time = System.currentTimeMillis()
            onView(withText("new test note")).perform(click())
            onView(withId(R.id.action_delete)).perform(click())
            onView(withText(R.string.delete_note_confirmation_text)).check(matches(isDisplayed()))
            onView(withText(R.string.delete_label)).perform(click())
            avg += System.currentTimeMillis() - time
//        }
//        avg /= 10;
//        Log.i("AVERAGE", "" + avg);
    }

    private fun createNote() {
        onView(withId(R.id.action_categories)).perform(click())
        onView(withText(CategoryUtil.getDefaultCategory().name)).perform(click())
        onView(ViewMatchers.withId(R.id.add_note)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.title)).perform(ViewActions.typeText("new test note")).perform(ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.description)).perform(ViewActions.typeText("test note description"))
        onView(ViewMatchers.withId(R.id.action_save)).perform(ViewActions.click())
    }

    private fun removeNote() {
        onView(withText("edit test note")).perform(click())
        onView(withId(R.id.action_delete)).perform(click())
        onView(withText(R.string.delete_label)).perform(click())
    }
}