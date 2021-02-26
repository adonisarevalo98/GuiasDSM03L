package com.example.ejercicio2_splitjava;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
private TextView  c1, c2, c3, c4, pc1, pc2, pc3, pc4, nulos;
private EditText votos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c1 = (TextView) findViewById(R.id.tvc1);
        c2 = (TextView) findViewById(R.id.tvc2);
        c3 = (TextView) findViewById(R.id.tvc3);
        c4 = (TextView) findViewById(R.id.tvc4);
        pc1 = (TextView) findViewById(R.id.tvpc1);
        pc2 = (TextView) findViewById(R.id.tvpc2);
        pc3 = (TextView) findViewById(R.id.tvpc3);
        pc4 = (TextView) findViewById(R.id.tvpc4);
        votos = (EditText) findViewById(R.id.etvotos);
        nulos = (TextView) findViewById(R.id.tvnulos);

    }
    public void calcular (View view){
        Toast alert1 = Toast.makeText(this, "Lista de votos vacia", Toast.LENGTH_LONG);
        //Variables contadoras que almacenaran el numero de votos;
        int vc1=0, vc2=0, vc3=0, vc4=0;
        //Variables que almacenaran el porcentaje de votos por candidato
        double pvc1=0.0, pvc2=0.0, pvc3=0.0, pvc4=0.0;
        //variable que almacena el numero de votos
        int cantvotos=0;
        //variable que aclmacena el numero de votos nulos
        int vnulos=0;
        //variable que almacena porcentaje de votos nulos
        double pvnulos=0.0;
        if (votos.getText().toString().equals("")){
            alert1.show();
        }else{
            //almacenando lista de votos en arreglos con split
            String[] lvotos1 = votos.getText().toString().split(",");
           //recorriendo arreglo para contar votos
            for (int i=0; i<lvotos1.length;i++) {
               if(Integer.parseInt(lvotos1[i]) == 1){
                   vc1 += 1;
               }else  if(Integer.parseInt(lvotos1[i]) == 2){
                   vc2+=1;
               }else if(Integer.parseInt(lvotos1[i]) == 3){
                   vc3+=1;
                }else if(Integer.parseInt(lvotos1[i]) == 4){
                   vc4+=1;
                }else{
                   vnulos+=1;
                }
            }
            //Determinando porcentaje de votos de cada candidato
            cantvotos = lvotos1.length;
            pvc1= (vc1*100)/cantvotos;
            pvc2= (vc2*100)/cantvotos;
            pvc3= (vc3*100)/cantvotos;
            pvc4= (vc4*100)/cantvotos;
            pvnulos= (vnulos*100)/cantvotos;

            c1.setText(String.valueOf(vc1));
            c2.setText(String.valueOf(vc2));
            c3.setText(String.valueOf(vc3));
            c4.setText(String.valueOf(vc4));
            pc1.setText(String.valueOf(pvc1)+"%");
            pc2.setText(String.valueOf(pvc2)+"%");
            pc3.setText(String.valueOf(pvc3)+"%");
            pc4.setText(String.valueOf(pvc4)+"%");
            nulos.setText("Hay "+String.valueOf(vnulos)+" votos nulos, siendo el "+
                    String.valueOf(pvnulos)+ "% de los votos");

        }
    }
    public void limpiar (View view){
        c1.setText("");
        c2.setText("");
        c3.setText("");
        c4.setText("");
        pc1.setText("");
        pc2.setText("");
        pc3.setText("");
        pc4.setText("");
        nulos.setText("");
        votos.setText("");
    }
}