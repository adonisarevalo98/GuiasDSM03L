package com.example.ecuacioncuadratica;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText a, b, c;
private TextView x1, x2, nr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = (EditText) findViewById(R.id.eta);
        b = (EditText) findViewById(R.id.etb);
        c = (EditText) findViewById(R.id.etc);
        x1 = (TextView) findViewById(R.id.tvx1);
        x2 = (TextView) findViewById(R.id.tvx2);
        nr = (TextView) findViewById(R.id.tvnr);

    }
    public void calcular (View view){
        //Validacion de entradas
        if (a.getText().toString().equals("") || b.getText().toString().equals("") ||
                c.getText().toString().equals("")){
            Toast alert1 = Toast.makeText(this, "Ingrese todas las componentes", Toast.LENGTH_LONG);
        alert1.show();
        }else if (Double.parseDouble(a.getText().toString()) == 0.00){
            Toast alert2 = Toast.makeText(this, "La componente cuadrática (a) no puede ser cero", Toast.LENGTH_LONG);
           alert2.show();
        }else{
            //variables que almacenan componentes y resultados
            Double va = Double.parseDouble(a.getText().toString()),
                    vb = Double.parseDouble(b.getText().toString()),
                    vc= Double.parseDouble(c.getText().toString());



            //mensaje a mostrar en caso de que no haya solucion real
            String msg = "";

            //verificando si la ecuacion tiene solucion real y obteniemdo solucion
            Double p1 = (4.00*va*vc);
            if(Math.pow(vb,2.0) < p1){
                msg = "La ecuación no tiene solución real.";
                nr.setText(msg);
            }else{
                //resolviendo ecuacion general

                Double rx1 = (-vb-Math.sqrt(Math.pow(vb,2.0)-(4.0 * va * vc)))/(2.0 * va);
                Double rx2 = (-vb+Math.sqrt(Math.pow(vb,2.0)-(4.0 * va * vc)))/(2.0 * va);
                x1.setText(String.valueOf(rx1));
                x2.setText(String.valueOf(rx2));
                nr.setText("");
            }
        }
    }

    public  void  limpiar (View view){
        a.setText("");
        b.setText("");
        c.setText("");
        x1.setText("");
        x2.setText("");
        nr.setText("");
    }
}