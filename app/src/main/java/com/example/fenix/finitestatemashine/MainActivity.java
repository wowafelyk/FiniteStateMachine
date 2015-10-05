package com.example.fenix.finitestatemashine;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fenix.finitestatemashine.FSM.FSM;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class MainActivity extends AppCompatActivity implements FSM.StateChangeListener {

    private ImageView mImageView;
    private TextView mTextView;
    private FSM mFiniteStateMachine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageView = (ImageView)findViewById(R.id.imageView);
        mTextView = (TextView)findViewById(R.id.textView);

        JSONObject data = getJSONFile(R.raw.fsm_states);
        mFiniteStateMachine = new FSM(data,this);
        mFiniteStateMachine.setStateChangeListener(this);

    }

    /** Used for sending states from FSM to UI */
    @SuppressWarnings("deprecation")
    @Override
    public void onStateChange(String state) {
        mTextView.setText(state);
        if(state.contains("AlarmDisarmed")){
            mImageView.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        }else{
            mImageView.setBackgroundColor(getResources().getColor(R.color.colorRed));
        }

    }

    public void onClickListener(View v){
        switch (v.getId()) {
            case R.id.button_lock:
                if(!mFiniteStateMachine.addAction("LOCK")){
                    Toast.makeText(this,"Error in JSON file. Please check again",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_lockX2:
                if(!mFiniteStateMachine.addAction("LOCKx2")) {
                    Toast.makeText(this, "Error in JSON file. Please check again", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_unlock:
                if(!mFiniteStateMachine.addAction("UNLOCK")){
                    Toast.makeText(this, "Error in JSON file. Please check again", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_unlockX2:
                if(!mFiniteStateMachine.addAction("UNLOCKx2")){
                    Toast.makeText(this, "Error in JSON file. Please check again", Toast.LENGTH_LONG).show();
                }
                break;
        }

    }


    /**
     * Get JSONObject from raw package
     */
    public JSONObject getJSONFile(int id){
        InputStream is = getResources().openRawResource(id);
        Writer writer = new StringWriter();
        int n;

        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is));
            while((n=reader.read(buffer)) !=-1){
                writer.write(buffer,0,n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            JSONObject jsonObject = new JSONObject(writer.toString());
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}


