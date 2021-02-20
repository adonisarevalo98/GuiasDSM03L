package com.example.ejerciciocontoast;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText sueldo;
private EditText años;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sueldo = (EditText) findViewById(R.id.etsueldo);
        años = (EditText) findViewById(R.id.etaños);
    }
    public void segundaactivity (View view){
        Toast alert1 = Toast.makeText(this, "No se permiten valores negativos", Toast.LENGTH_LONG);
        Toast alert2 = Toast.makeText(this, "El sueldo debe ser de 100 o más", Toast.LENGTH_LONG);
        if (Double.parseDouble(String.valueOf(sueldo.getText())) < 0.00 || Double.parseDouble(String.valueOf(años.getText())) < 0.00){
           alert1.show();
        }else if (Double.parseDouble(String.valueOf(sueldo.getText())) > 0.00 && Double.parseDouble(String.valueOf(sueldo.getText())) < 100.00){
           alert2.show();
        }else{
            Intent i=new Intent(this, ActivityTwo.class);
            i.putExtra("etsueldo",Double.parseDouble(String.valueOf(sueldo.getText())));
            i.putExtra("etaños",Double.parseDouble(String.valueOf(años.getText())));
            startActivity(i);
        }


    }
}