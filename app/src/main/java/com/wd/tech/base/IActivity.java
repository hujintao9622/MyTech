package com.wd.tech.base;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.wd.tech.R;

public class IActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i);
    }
    public static void startActivity(Context context, Class cls){
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
    }
}
