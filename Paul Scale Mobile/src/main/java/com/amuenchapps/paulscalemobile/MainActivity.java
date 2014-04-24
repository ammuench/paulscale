package com.amuenchapps.paulscalemobile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {

    private RelativeLayout mainLayout;
    private TextView scale0, scale1, scale2, scale3, scale4, scale5, scale6, scale7, scale8, scale9;

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        System.out.println("saved instance state");
//        super.onSaveInstanceState(outState);
//        outState.putInt("scaleVal", prevClick);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        System.out.println("restored instance state");
//        super.onRestoreInstanceState(savedInstanceState);
//        onClick((View)findViewById(savedInstanceState.getInt("scaleVal")));
//
//    }

    private TextView lastSelectedScale;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Store values between instances here
        SharedPreferences saveState = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = saveState.edit();  // Put the values from the UI
        System.out.println("Pausing, prevClick val = " + prevClick);
        if (prevClick != 0) {
            View colorView = findViewById(prevClick);
            int savedColor = ((ColorDrawable)colorView.getBackground()).getColor();
            editor.putInt("scaleID", prevClick);
            editor.putString("oldText", prevText);
            editor.putInt("oldColor", savedColor);
            // Commit to storage
            editor.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
            scale0 = (TextView) findViewById(R.id.scale0);
            scale1 = (TextView) findViewById(R.id.scale1);
            scale2 = (TextView) findViewById(R.id.scale2);
            scale3 = (TextView) findViewById(R.id.scale3);
            scale4 = (TextView) findViewById(R.id.scale4);
            scale5 = (TextView) findViewById(R.id.scale5);
            scale6 = (TextView) findViewById(R.id.scale6);
            scale7 = (TextView) findViewById(R.id.scale7);
            scale8 = (TextView) findViewById(R.id.scale8);
            scale9 = (TextView) findViewById(R.id.scale9);
            scale0.setOnClickListener(this);
            scale1.setOnClickListener(this);
            scale2.setOnClickListener(this);
            scale3.setOnClickListener(this);
            scale4.setOnClickListener(this);
            scale5.setOnClickListener(this);
            scale6.setOnClickListener(this);
            scale7.setOnClickListener(this);
            scale8.setOnClickListener(this);
            scale9.setOnClickListener(this);
            scale0.setOnLongClickListener(this);
            scale1.setOnLongClickListener(this);
            scale2.setOnLongClickListener(this);
            scale3.setOnLongClickListener(this);
            scale4.setOnLongClickListener(this);
            scale5.setOnLongClickListener(this);
            scale6.setOnLongClickListener(this);
            scale7.setOnLongClickListener(this);
            scale8.setOnLongClickListener(this);
            scale9.setOnLongClickListener(this);

            SharedPreferences saveState = getPreferences(MODE_PRIVATE);
            int sample = saveState.getInt("scaleID", 0);
            View scale = (View)findViewById(sample);
            View mainView = findViewById(R.id.layout);
            if(scale != null)
            {
                lastSelectedScale = (TextView)findViewById(scale.getId());
                prevClick = sample;
            }
            if(lastSelectedScale != null)
            {
                TextView clickedTV = (TextView)findViewById(scale.getId());
                prevText = clickedTV.getText().toString();
                clickedTV.setTextSize(20);
                clickedTV.setTypeface(null, Typeface.BOLD);
                ColorDrawable lastBackground = (ColorDrawable)lastSelectedScale.getBackground();
                mainView.setBackgroundColor(lastBackground.getColor());
            }
            else
            {
                Log.d("ON_RESUME", "NoView");
            }
        } else {
            setContentView(R.layout.layout_landscape);
            TextView l_fullText = (TextView) findViewById(R.id.fs_textView);
            SharedPreferences saveState = getPreferences(MODE_PRIVATE);
            l_fullText.setText(saveState.getString("oldText", null));
            LinearLayout l_layout = (LinearLayout) findViewById(R.id.l_layout);
            l_layout.setBackgroundColor(saveState.getInt("oldColor", 0));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int prevClick = 0;
    private String prevText;

    @Override
    public void onClick(View scale) {
        if(scale.getId() == prevClick)
        {
            //do nothing
            System.out.println("Same button!");
        }else if (0 == prevClick){
            //set new button
            prevClick = scale.getId();
            Drawable clickedColor = scale.getBackground();
            TextView clickedTV = (TextView)findViewById(scale.getId());
            prevText = clickedTV.getText().toString();
            clickedTV.setTextSize(20);
            clickedTV.setTypeface(null, Typeface.BOLD);
            mainLayout = (RelativeLayout) findViewById(R.id.layout);
            mainLayout.setBackground(clickedColor);
        }else{
            //reset previous button
            TextView oldTV = (TextView)findViewById(prevClick);
            oldTV.setText(prevText);
            oldTV.setTextSize(14);
            oldTV.setTypeface(Typeface.SANS_SERIF);
            //set new button
            prevClick = scale.getId();
            Drawable clickedColor = scale.getBackground();
            TextView clickedTV = (TextView)findViewById(scale.getId());
            prevText = clickedTV.getText().toString();
            clickedTV.setTextSize(20);
            clickedTV.setTypeface(null, Typeface.BOLD);
            mainLayout = (RelativeLayout) findViewById(R.id.layout);
            mainLayout.setBackground(clickedColor);
        }
    }

    @Override
    public boolean onLongClick(View scale) {
        onClick(scale);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "CURRENT PAUL SCALE STATUS: \"" + ((TextView) findViewById(scale.getId())).getText().toString() + "\"");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
        return true;
    }
}
