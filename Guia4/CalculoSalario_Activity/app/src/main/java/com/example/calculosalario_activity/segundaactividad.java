package com.example.calculosalario_activity;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

public class segundaactividad extends AppCompatActivity {

    private TextView salario;
    private TextView isss;
    private TextView afp;
    private TextView renta;
    private TextView neto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundaactividad);

        salario = (TextView) findViewById(R.id.tvsalario);
        isss = (TextView) findViewById(R.id.tvisss);
        afp = (TextView) findViewById(R.id.tvafp);
        renta = (TextView) findViewById(R.id.tvrenta);
        neto = (TextView) findViewById(R.id.tvneto);

        Bundle bundle = getIntent().getExtras();
        Double horast = bundle.getDouble("ethoras");

        double salariot = (horast * 8.50);
        double disss = (salariot * 0.02);
        double dafp = (salariot * 0.03);
        double drenta = (salariot * 0.04);
        double netot = (salariot - disss - dafp - drenta);

        salario.setText("$" + String.valueOf(salariot));
        isss.setText("$"+String.valueOf( disss));
        afp.setText("$"+String.valueOf( dafp));
        renta.setText("$"+String.valueOf( drenta));
        neto.setText("$"+String.valueOf( netot));
    }
    public void Finalizar(View v){
        finish();
    }
}