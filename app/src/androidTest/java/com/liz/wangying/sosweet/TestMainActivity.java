package com.liz.wangying.sosweet;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * author WangYing
 * time   2017/10/17/10/30:下午7:12
 * email  wangying@growingio.com
 */
@RunWith(JUnit4.class)
public class TestMainActivity {

    @Rule
    public ActivityTestRule testRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void whenClickButton_Expect_ShowToast() {
        onView(withId(R.id.et_username)).perform(typeText("hello"));
        onView(withId(R.id.btn_toast)).perform(click());

    }
}
