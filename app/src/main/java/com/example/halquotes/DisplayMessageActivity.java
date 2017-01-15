package com.example.halquotes;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

public class DisplayMessageActivity extends AppCompatActivity {


    TextView textView;
    String[] quotes;
    Button restart;
    Button clear;
    MediaPlayer mp;
    Context context = this;
    //String[] quotes = App.getContext().getResources().getStringArray(R.array.quotes_array);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        quotes = getResources().getStringArray(R.array.quotes_array);
        restart = (Button)findViewById(R.id.RestartButton);
        clear = (Button)findViewById(R.id.ClearButton);

        mp = MediaPlayer.create(context, R.raw.fart01);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        textView = new TextView(this);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        Random rand = new Random();
        int num = rand.nextInt(quotes.length);
        String quote = quotes[num];
        textView.setText(quote);
        quotes = ArrayUtils.remove(quotes, num);

        textView.setText(quote);

        //quotes=getResources().getStringArray(R.array.quotes_array);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
    }

    public void clear(View view) {

        textView.setText("");

        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(context, R.raw.fart01);
            } mp.start();
        } catch(Exception e) { e.printStackTrace(); }

        if (quotes.length > 0) {

            Random rand = new Random();
            int num = rand.nextInt(quotes.length);
            String quote = quotes[num];
            textView.setText(quote);
            quotes = ArrayUtils.remove(quotes, num);

        } else {

            textView.setText("You have seen all the Hal quotes, please go back to main screen to start again.");
            restart.setVisibility(View.VISIBLE);
            clear.setVisibility(View.INVISIBLE);

        }

    }

    public void restart(View view) {

        Intent mainIntent = new Intent(this, App.class);

        startActivity(mainIntent);
        finish();

    }

}



