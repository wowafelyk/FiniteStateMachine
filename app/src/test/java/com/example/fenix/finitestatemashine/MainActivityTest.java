package com.example.fenix.finitestatemashine;

import android.widget.TextView;

import com.example.fenix.finitestatemashine.FSM.FSM;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;


import java.util.HashMap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
    MainActivity activity;

    @BeforeClass
    public void init(){
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void titleIsCorrect()throws Exception{
        assertTrue("Title is not correct",activity.getTitle().toString().equals("FiniteStateMachine"));
    }

    @Test
    public void stateIsCorrect(){
        String text =(String)((TextView)activity.findViewById(R.id.textView)).getText();
        assertTrue("Message", text.equals("AlarmDisarmed_AllUnlock"));
    }

    @Test
    public void loadJSONisCorrect(){
        assertNotNull("Not loading JSON file", activity.getJSONFile(R.raw.fsm_states));
    }

}
