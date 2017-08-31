package com.batchmates.android.roboelectricpoc;

import android.content.Intent;
import android.widget.TextView;

import org.apache.tools.ant.Main;
import org.codehaus.plexus.interpolation.InterpolationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Android on 8/31/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTester {

    private MainActivity mainActivity;

    @Before
    public void setUp()
    {
        mainActivity= Robolectric.setupActivity(MainActivity.class);
    }


    //cheks if the text is correct
    @Test
    public void checkWorld()
    {
        TextView textView=(TextView)mainActivity.findViewById(R.id.tvHelloWorld);
        assertNotNull("Not Null",textView);
        assertTrue("is True",textView.getText().equals("Hello World!"));
    }

    //checks intent
    @Test
    public void clickedNextButton()
    {
        mainActivity.findViewById(R.id.btnToNext).performClick();
        Intent expectedIntent = new Intent(mainActivity,Main2Activity.class);

        ShadowActivity shadowActivity= Shadows.shadowOf(mainActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue("Is True",actualIntent.filterEquals(expectedIntent));
    }

}
