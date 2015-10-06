package com.example.fenix.finitestatemashine.FSM;

/** Class keep transitions with StartState and EndState
 *  May be modified for keeping appropriate listener
 *
 *  @autor Felyk Volodymyr
 *  @version 1.0
 */
public class StateTransition {
    private String mStartState;
    private String mEndState;

    public StateTransition(String mStartState, String mEndState) {
        this.mStartState = mStartState;
        this.mEndState = mEndState;
    }

    public String getEndState() {
        return mEndState;
    }

    public void setEndState(String mEndState) {
        this.mEndState = mEndState;
    }

    public String getStartState() {
        return mStartState;
    }

    public void setStartState(String mStartState) {
        this.mStartState = mStartState;
    }
}
