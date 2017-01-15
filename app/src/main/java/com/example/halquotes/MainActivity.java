package com.example.halquotes;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.lang.reflect.Field;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.halquotes.MESSAGE";
    //Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //String message = getResources().getString(R.string.quote1);

        String[] arr;
        arr=getResources().getStringArray(R.array.quotes_array);
        Random random = new Random();
        int select = random.nextInt(arr.length);
        String message = arr[select];

        intent.putExtra(EXTRA_MESSAGE, message);
        //intent.putExtra(EXTRA_MESSAGE, R.string.quote1);
        startActivity(intent);
        finish();
    }


}
