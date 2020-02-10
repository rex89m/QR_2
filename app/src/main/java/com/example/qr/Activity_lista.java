package com.example.qr;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;


public class Activity_lista extends AppCompatActivity {
    public SharedPreferences fille;
    public SharedPreferences fille_2;
    public SharedPreferences fille_3;
    public SharedPreferences fille_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fille = getSharedPreferences("dane", Activity.MODE_PRIVATE);
        fille_2 = getSharedPreferences("dane_2", Activity.MODE_PRIVATE);
        fille_3 = getSharedPreferences("dane_3", Activity.MODE_PRIVATE);
        fille_4 = getSharedPreferences("dane_4", Activity.MODE_PRIVATE);
        odczyt();
    }

    public void odczyt() {
        @SuppressLint("WrongViewCast")
        LinearLayout scrollView = findViewById(R.id.lista_2);
        scrollView.removeAllViews();
        /*
        for (String i : fille.getAll().keySet()) {
            TextView text = new TextView(this);
            text.setTextSize(20);
            text.setText("(" + i + ") " + fille_2.getString(i, "") + " " + fille.getString(i, ""));
            if (fille_3.getString(i, "").equals("red")) {
                text.setTextColor(Color.RED);
            } else {

            }
            if (fille_3.getString(i, "").equals("d")) {
                text.setTextColor(Color.RED);
                text.setText("(" + i + ")" + fille_2.getString(i, "") + " " + fille.getString(i, "") + "(D)");
            }

            scrollView.addView(text);

         */
            //TextView liczba_text = findViewById(R.id.ilosc_osob);
            //int suma = liczba+Integer.parseInt(fille_4.getString("liczba",""));
            //liczba_text.setText(String.valueOf(suma));
        for (int i=0;i<50;i++){
            TextView test = new TextView(this);
            test.setText(i+"");
            test.setTextSize(20);
            scrollView.addView(test);
        }
    }
}
