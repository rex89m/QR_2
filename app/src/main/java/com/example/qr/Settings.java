package com.example.qr;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class Settings extends AppCompatActivity {
    public SharedPreferences fille_5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fille_5 = getSharedPreferences("dane_5", Activity.MODE_PRIVATE);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void zapisz(View view) {
        SharedPreferences.Editor edit_5 = fille_5.edit();
        EditText editText = findViewById(R.id.ilosc_miejc);
        edit_5.putString("settings", String.valueOf(editText));
        edit_5.apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
