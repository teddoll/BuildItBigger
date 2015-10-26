package com.teddoll.telljokelibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;



public class TellJokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "joke";
    public static final String EXTRA_ANSWER = "answer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tell_joke);
    }


}
