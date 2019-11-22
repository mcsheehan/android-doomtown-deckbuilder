package net.marksheehan.doomtowndeckbuilder;

import androidx.test.rule.ActivityTestRule;

import net.marksheehan.doomtowndeckbuilder.ui.MainNavHostActivity;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import tools.fastlane.screengrab.Screengrab;
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy;
import tools.fastlane.screengrab.locale.LocaleTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(JUnit4.class)
public class Screenshots
{
        @ClassRule
        public static final LocaleTestRule localeTestRule = new LocaleTestRule();

        @Rule
        public ActivityTestRule<MainNavHostActivity> activityRule = new ActivityTestRule<>(MainNavHostActivity.class);

        void delayMillis(long delay_time_millis){
            try
            {
                Thread.sleep(delay_time_millis);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        @Test
        public void testTakeScreenshot() {

            activityRule.getActivity();

            Screengrab.setDefaultScreenshotStrategy(new UiAutomatorScreenshotStrategy());

            delayMillis(1000);
            Screengrab.screenshot("launch_screen");

            openActionBarOverflowOrOptionsMenu(activityRule.getActivity());
            Screengrab.screenshot("option_menu");
            onView(withText(R.string.view_cards)).perform(click());
            delayMillis(1000);
            Screengrab.screenshot("view_cards");

            openActionBarOverflowOrOptionsMenu(activityRule.getActivity());
            onView(withText(R.string.choose_packs)).perform(click());
            Screengrab.screenshot("choose_packs");
        }
}
