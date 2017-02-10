package com.example.halquotes;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import java.lang.reflect.Field;
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
        /* create a full screen window */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_display_message);

        /* adapt the image to the size of the display */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.hal_01),size.x,size.y,true);

        /* fill the background ImageView with the resized image */
        ImageView iv_background = (ImageView) findViewById(R.id.iv_background);
        iv_background.setImageBitmap(bmp);

        /* define quotes array and buttons */
        quotes = getResources().getStringArray(R.array.quotes_array);
        restart = (Button)findViewById(R.id.RestartButton);
        clear = (Button)findViewById(R.id.ClearButton);

        /* begin sound resource */
        mp = MediaPlayer.create(context, R.raw.fart01);

        /* message and textview initialization and attributes */
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        textView = new TextView(this);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER_VERTICAL);

        /* create random for quote array */
        Random rand = new Random();
        int num = rand.nextInt(quotes.length);
        String quote = quotes[num];
        textView.setText(quote);
        quotes = ArrayUtils.remove(quotes, num);

        /* insert first quote */
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

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);


            int[] halpics = {R.drawable.hal_01, R.drawable.hal_02, R.drawable.hal_03, R.drawable.hal_04, R.drawable.hal_05};
            Random randpics = new Random();
            int numpics = randpics.nextInt(halpics.length);
            int halpic = halpics[numpics];
            Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),halpic),size.x,size.y,true);

            /* fill the background ImageView with the resized image */
            ImageView iv_background = (ImageView) findViewById(R.id.iv_background);
            iv_background.setImageBitmap(bmp);

            /* Grab new random quote from the array list */
            Random rand = new Random();
            int num = rand.nextInt(quotes.length);
            String quote = quotes[num];
            textView.setText(quote);
            /*remove quotes from the Array so it's only used once */
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



