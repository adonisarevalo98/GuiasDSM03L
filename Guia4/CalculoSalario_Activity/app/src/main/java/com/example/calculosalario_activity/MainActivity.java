package com.example.calculosalario_activity;

 import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
private EditText nombre;
private EditText horas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre =(EditText) findViewById(R.id.etnombre);
        horas=(EditText) findViewById(R.id.ethoras);
    }
    public void segundaactivity (View view){
        Intent i=new Intent(this, segundaactividad.class);
        i.putExtra("etnombre", nombre.getText().toString());
        i.putExtra("ethoras",Double.parseDouble( String.valueOf(horas.getText())));
        startActivity(i);
    }
}