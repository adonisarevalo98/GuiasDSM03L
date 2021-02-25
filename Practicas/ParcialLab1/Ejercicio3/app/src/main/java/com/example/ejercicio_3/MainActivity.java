package com.example.ejercicio_3;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
private EditText nombre1, horas1, cargo1, nombre2, horas2, cargo2, nombre3, horas3, cargo3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre1 = (EditText) findViewById(R.id.etnombre1);
        nombre2 = (EditText) findViewById(R.id.etnombre2);
        nombre3 = (EditText) findViewById(R.id.etnombre3);
        cargo1 = (EditText) findViewById(R.id.etcargo1);
        cargo2 = (EditText) findViewById(R.id.etcargo2);
        cargo3 = (EditText) findViewById(R.id.etcargo3);
        horas1 = (EditText) findViewById(R.id.ethoras1);
        horas2 = (EditText) findViewById(R.id.ethoras2);
        horas3 = (EditText) findViewById(R.id.ethoras3);
    }
    public void segundaactivity (View view){
        Toast alert1 = Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_LONG);
        Toast alert2 = Toast.makeText(this, "Dato de Horas trabajadas incorrecto", Toast.LENGTH_LONG);
       if (nombre1.getText().toString().equals("") || nombre2.getText().toString().equals("")
        || nombre3.getText().toString().equals("") || cargo1.getText().toString().equals("")
        || cargo2.getText().toString().equals("") || cargo3.getText().toString().equals("")
        || horas1.getText().toString().equals("") || horas2.getText().toString().equals("") || horas3.getText().toString().equals("")){
            alert1.show();
        }else if (Double.parseDouble(horas1.getText().toString()) <= 0.00
        || Double.parseDouble(horas2.getText().toString()) <= 0.00
        || Double.parseDouble(horas3.getText().toString()) <= 0.00){
            alert2.show();
        }else{


         Intent i = new Intent(this, MainActivity2.class);
         i.putExtra("etnombre1", String.valueOf(nombre1.getText()));
            i.putExtra("etnombre2", String.valueOf(nombre2.getText()));
            i.putExtra("etnombre3", String.valueOf(nombre3.getText()));
            i.putExtra("etcargo1", String.valueOf(cargo1.getText()));
            i.putExtra("etcargo2", String.valueOf(cargo2.getText()));
            i.putExtra("etcargo3", String.valueOf(cargo3.getText()));
            i.putExtra("ethoras1", Double.parseDouble(horas1.getText().toString()));
            i.putExtra("ethoras2", Double.parseDouble(horas2.getText().toString()));
            i.putExtra("ethoras3", Double.parseDouble(horas3.getText().toString()));
            startActivity(i);
        }
    }
}