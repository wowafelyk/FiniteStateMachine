package com.example.fenix.finitestatemashine.FSM;

import com.example.fenix.finitestatemashine.BuildConfig;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class StateTransitionTest {
    private static StateTransition transition;

    @BeforeClass
    public static void init(){
        transition = new StateTransition("Start","End");
    }

    @Test
    public void testGetEndState() throws Exception {
        assertEquals("Get End State error","End",transition.getEndState());
    }



    @Test
    public void testGetStartState() throws Exception {
        assertEquals("Get Start State error","Start",transition.getStartState());
    }


}