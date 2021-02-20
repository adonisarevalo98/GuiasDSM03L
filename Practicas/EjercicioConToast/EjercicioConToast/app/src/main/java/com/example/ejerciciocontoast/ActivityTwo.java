package com.example.ejerciciocontoast;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {
private TextView sueldoa;
private TextView añost;
private TextView aumento;
private TextView sueldot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        sueldoa = (TextView) findViewById(R.id.tvsueldoa);
        añost = (TextView) findViewById(R.id.tvañost);
        aumento = (TextView) findViewById(R.id.tvaumento);
        sueldot = (TextView) findViewById(R.id.tvsueldot);

        Bundle bundle = getIntent().getExtras();
        Double añostr = bundle.getDouble("etaños");
        Double sueldoac = bundle.getDouble("etsueldo");
        Double sueldotot = 0.00;
        String aumento2 = "0%";

        if (añostr >= 10 && sueldoac < 500){
            aumento2 = "20%";
             sueldotot = sueldoac * 1.20;

        }else if(añostr < 10 && sueldoac < 500){
            aumento2 = "5%";
            sueldotot = sueldoac * 1.05;
        }else if (sueldoac >= 500){
            aumento2 = "0.0%";
            sueldotot = sueldoac;
        }

        sueldoa.setText("$"+String.valueOf(sueldoac));
        añost.setText(String.valueOf(añostr));
        aumento.setText(String.valueOf(aumento2));
        sueldot.setText("$"+String.valueOf(sueldotot));


    }
    public void Regresar(View v){
        finish();
    }
}