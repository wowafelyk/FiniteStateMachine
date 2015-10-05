package com.example.fenix.finitestatemashine.FSM;

import com.example.fenix.finitestatemashine.BuildConfig;
import com.example.fenix.finitestatemashine.MainActivity;
import com.example.fenix.finitestatemashine.R;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * @author Felyk Volodymyr
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class FSMTest {
    MainActivity activity;

    @BeforeClass
    public void init(){
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void testAddAction() throws Exception {

        FSM fsm = new FSM(activity.getJSONFile(R.raw.fsm_states),activity);
        assertTrue("Initial state error", fsm.getCurrentState().equals("AlarmDisarmed_AllUnlocked"));
        fsm.addAction("LOCK");
        assertTrue("LOCK test error", fsm.getCurrentState().equals("AlarmDisarmed_AllUnlocked"));
        fsm.addAction("LOCKx2");
        assertTrue("LOCKx2 test error", fsm.getCurrentState().equals("AlarmArmed_AllLocked"));


    }
}