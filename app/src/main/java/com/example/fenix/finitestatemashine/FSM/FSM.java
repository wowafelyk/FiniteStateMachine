package com.example.fenix.finitestatemashine.FSM;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @autor Felyk Volodymyr
 * @since 04.10.2015.
 * @version 1.0
 */
public class FSM {

    private String mCurrentState;
    private HashMap<String,HashMap<String,StateTransition>> mActions
            = new HashMap<String,HashMap<String,StateTransition>>();
    private HashMap<String,StateTransition> mStates
            = new HashMap<String,StateTransition>();
    private StateChangeListener mListener;

    /** Creates Finite State Machine.
     *
     * @param config       JSON file for configuring FSM
     * @throws JSONException    Occurs when set JSON file with wrong data
     */
    public FSM(JSONObject config,Context context){
        try {
            mCurrentState = config.getString("StartState");
            JSONObject jsonObjectAction = config.getJSONObject("actions");
            Iterator<String> iter = jsonObjectAction.keys();
            while(iter.hasNext()) {
                String action = iter.next();
                JSONObject obj = jsonObjectAction.getJSONObject(action);
                Iterator<String> kIter = obj.keys();
                mStates = new HashMap<String,StateTransition>();
                while(kIter.hasNext()) {
                    String state = kIter.next();
                    String endState = obj.getString(state);
                    mStates.put(state,new StateTransition(state,endState));
                }
                mActions.put(action,mStates);
            }
        } catch (JSONException e) {
            Toast.makeText(context, "Error in JSON file. Please check again", Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }
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

        mCurrentState = mActions.get(action).get(mCurrentState).getEndState();

        if(mListener!=null){
            mListener.onStateChange(mCurrentState);
        }
        return true;
    }




}
