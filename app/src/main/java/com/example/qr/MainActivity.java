package com.example.qr;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.Date;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scannerView;
    public SharedPreferences fille;
    public SharedPreferences fille_2;
    public SharedPreferences fille_3;
    public SharedPreferences fille_4;
    public SharedPreferences fille_5;

    int liczba = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    @Override
    @SuppressLint({"WrongViewCast", "SetTextI18n"})
    public void handleResult(Result rawResult) {
        if (rawResult.getText()!=null) {
            Date date = new Date();
            SharedPreferences.Editor edit = fille.edit();
            SharedPreferences.Editor edit_2 = fille_2.edit();
            SharedPreferences.Editor edit_3 = fille_3.edit();
            for (String i : fille.getAll().keySet()){
                if (fille.getString(i,"").equals(rawResult.getText())){
                   // edit_3.putString(i, "red");
                    edit_3.putString(String.valueOf(fille.getAll().size()+1), "red");
                    edit_3.apply();
                }
            }
            edit.putString(String.valueOf(fille.getAll().size()+1), rawResult.getText());
            edit_2.putString(String.valueOf(fille.getAll().size()+1), date.getHours()+":"+date.getMinutes());
            edit.apply();
            edit_2.apply();
        }
        try {
            Thread.sleep(1000);
            scannerView.setResultHandler(MainActivity.this);
            scannerView.startCamera();
            scannerView.setAutoFocus(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        podlicz();
        Empti_slot();
    }

    public void clear(View view) {
        if (view.getId()==R.id.clear){
            clear();
        }
    }

    public void clear(){
        new AlertDialog.Builder(this)
                .setTitle("Czy chcesz skasować wszystkie dane?")
                .setMessage("")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor edit = fille.edit();
                        SharedPreferences.Editor edit_2 = fille_2.edit();
                        SharedPreferences.Editor edit_3 = fille_3.edit();
                        SharedPreferences.Editor edit_4 = fille_4.edit();
                        edit.clear();
                        edit.apply();
                        edit_2.clear();
                        edit_2.apply();
                        edit_3.clear();
                        edit_3.apply();
                        edit_4.putString("liczba", "0");
                        edit_4.apply();
                        TextView liczba_text = findViewById(R.id.ilosc_osob);
                        liczba_text.setText(String.valueOf(0));
                        podlicz();
                        Empti_slot();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void add_1(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Accept")
                .setMessage("")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        add(1);

                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    public void add_2(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Accept")
                .setMessage("")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        add(2);

                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void add_3(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Accept")
                .setMessage("")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        add(3);

                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void add_4(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Accept")
                .setMessage("")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        add(4);

                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void add(int liczba_int){
        int i = Integer.parseInt(fille_4.getString("liczba",""));
        SharedPreferences.Editor edit = fille_4.edit();
        String cos = i+liczba_int+"";
        edit.putString("liczba", cos);
        edit.apply();
        podlicz();
        Empti_slot();
    }

    public void click_lista(View view) {
        Intent intent = new Intent(this, Activity_lista.class);
        startActivity(intent);
    }


    public void setting(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void podlicz(){
        liczba=0;
        for (String i : fille.getAll().keySet()) {
            TextView text = new TextView(this);
            text.setTextSize(20);
            text.setText("(" + i + ") " + fille_2.getString(i, "") + " " + fille.getString(i, ""));
            if (fille_3.getString(i, "").equals("red")) {
                text.setTextColor(Color.RED);
            } else {
                liczba++;
            }
            if (fille_3.getString(i, "").equals("d")) {
                text.setTextColor(Color.RED);
                text.setText("(" + i + ")" + fille_2.getString(i, "") + " " + fille.getString(i, "") + "(D)");
            }
        }
        TextView liczba_text = findViewById(R.id.ilosc_osob);
        int suma = liczba+Integer.parseInt(fille_4.getString("liczba",""));
        liczba_text.setText(String.valueOf(suma));
    }

    public void Empti_slot(){
        TextView textView = findViewById(R.id.wolne_miejsca);
        int liczba_2;
        try {
            liczba_2 = Integer.parseInt((fille_5.getString("settings", "")));

        } catch (NumberFormatException e) {
            liczba_2=0;
        }
        int suma = liczba+Integer.parseInt(fille_4.getString("liczba",""));

        textView.setText(String.valueOf(liczba_2-suma));
    }

    @Override
    protected void onStart() {
        super.onStart();
        fille = getSharedPreferences("dane", Activity.MODE_PRIVATE);
        fille_2 = getSharedPreferences("dane_2", Activity.MODE_PRIVATE);
        fille_3 = getSharedPreferences("dane_3", Activity.MODE_PRIVATE);
        fille_4 = getSharedPreferences("dane_4", Activity.MODE_PRIVATE);
        fille_5 = getSharedPreferences("dane_5", Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit_5 = fille_5.edit();

        if (fille_4.getAll().isEmpty()) {
            SharedPreferences.Editor edit = fille_4.edit();
            edit.putString("liczba", String.valueOf(0));
            edit.apply();
        }
        scannerView = findViewById(R.id.screen);
        podlicz();

        if (fille_5.getAll().isEmpty()){
            edit_5.putString("settings", "0");
            edit_5.apply();
        }
        Empti_slot();
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        scannerView.setResultHandler(MainActivity.this);
                        scannerView.startCamera();
                        scannerView.setAutoFocus(true);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();


    }
}
