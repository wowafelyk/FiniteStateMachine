package com.example.fenix.finitestatemashine.FSM;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @autor Felyk Volodymyr
 * @since 04.10.2015.
 * @version 1.0
 */
public class FSM {

    private String mCurrentState;
    private String mPreviousState;
    private JSONObject mActions;
    private StateChangeListener mListener;

    /** Creates Finite State Machine.
     *
     * @param config       JSON file for configuring FSM
     * @throws JSONException    Occurs when set JSON file with wrong data
     */
    public FSM(JSONObject config)throws JSONException{
        mCurrentState = config.getString("StartState");
        mActions = config.getJSONObject("actions");
    }

    /** Listener for send back current state after change */
    public interface StateChangeListener{
        public void onStateChange(String state);
    }

    public String getCurrentState(){
        return mCurrentState;
    }

    public void setStateChangeListener(StateChangeListener listener){
        mListener = listener;
    }

    /** Returns true if Action is done */
    public boolean addAction(String action){
        mPreviousState = mCurrentState;
        try {
            mCurrentState = mActions.getJSONObject(action).getString(mPreviousState);
        }catch(JSONException e) {
            return false;
        }
        mListener.onStateChange(mCurrentState);
        return true;
    }




}