package com.example.ejercicio_3;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private TextView nombree1, nombree2, nombree3, isss1, isss2, isss3, afp1, afp2, afp3, renta1, renta2, renta3, sueldo1, sueldo2, sueldo3, msueldo, bono, mesueldo, cants ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nombree1 = (TextView) findViewById(R.id.tvnombre1);
        nombree2 = (TextView) findViewById(R.id.tvnombre2);
        nombree3 = (TextView) findViewById(R.id.tvnombre3);
        isss1 = (TextView) findViewById(R.id.tvisss1);
        isss2 = (TextView) findViewById(R.id.tvisss2);
        isss3 = (TextView) findViewById(R.id.tvisss3);
        afp1 = (TextView) findViewById(R.id.tvafp1);
        afp2 = (TextView) findViewById(R.id.tvafp2);
        afp3 = (TextView) findViewById(R.id.tvafp3);
        renta1 = (TextView) findViewById(R.id.tvrenta1);
        renta2 = (TextView) findViewById(R.id.tvrenta2);
        renta3 = (TextView) findViewById(R.id.tvrenta3);
        sueldo1 = (TextView) findViewById(R.id.tvsueldo1);
        sueldo2 = (TextView) findViewById(R.id.tvsueldo2);
        sueldo3 = (TextView) findViewById(R.id.tvsueldo3);
        msueldo = (TextView) findViewById(R.id.tvmsueldo);
        bono = (TextView) findViewById(R.id.tvbono);
        mesueldo = (TextView) findViewById(R.id.tvmesueldo);
        cants = (TextView) findViewById(R.id.tvcant);

        //Declarando arreglos
        String[] nombres = new String[3];
        String[] cargos = new String[3];
        Double[] horas = new Double[3];
        Double[] isssd = new Double[3];
        Double[] afpd = new Double[3];
        Double[] rentad = new Double[3];
        Double[] sueldos = new Double[3];

        // Llenando arreglos con datos del ActivityMain
        Bundle bundle = getIntent().getExtras();
        nombres[0] = bundle.getString("etnombre1");
        nombres[1] = bundle.getString("etnombre2");
        nombres[2] = bundle.getString("etnombre3");
        cargos[0] = bundle.getString("etcargo1");
        cargos[1] = bundle.getString("etcargo2");
        cargos[2] = bundle.getString("etcargo3");
        horas[0] = bundle.getDouble("ethoras1");
        horas[1] = bundle.getDouble("ethoras2");
        horas[2] = bundle.getDouble("ethoras3");

        //Estableciendo si habrá o no bonos
        String msgbonos;
        if (cargos[0].equals("Gerente") && cargos[1].equals("Asistente") && cargos[2].equals("Secretaria")){
             msgbonos = "No hay bonos";
        }else{
            msgbonos= "Si hay bonos";
        }

        //Calculando descuentos y sueldos y llenando arreglos respectivos
        //recorro el arreglo de horas trabajadas sabiendo que en la misma posicion "i" dentro de los demas arreglos estará la demas informacion del trabajador
        for(int i=0; i<horas.length; i++ ){
            //calculando salario liquido para horas menores o iguales a 160
            if(horas[i] <= 160.00){

                sueldos[i]= (horas[i]*9.75);

             //Calculando salario liquido para horas mayores a 160
            }else if (horas[i] > 160.00){
                sueldos[i]= ((160*9.75)+((horas[i]-160)*11.50));
            }

            //calculando descuentos y guardandolos en los arreglos en su posicion i
            isssd[i] = (sueldos[i]*0.0525);
            afpd[i] = (sueldos[i]*0.0688);
            rentad[i]=(sueldos[i]*0.10);
            //sueldo liquido sin bono
            sueldos[i]=(sueldos[i]-isssd[i]-afpd[i]-rentad[i]);
            //validando si hay bono
            if(msgbonos.equals("Si hay bonos")){
                //Calculando (sueldo liquido + bono) segun el cargo
                if (cargos[i].equals("Gerente")){
                    sueldos[i]= (sueldos[i]*1.10);
                }else if (cargos[i].equals("Asistente")){
                    sueldos[i]= (sueldos[i]*1.05);
                }else if (cargos[i].equals("Secretaria")){
                    sueldos[i]=(sueldos[i]*1.03);
                }else{
                    sueldos[i]=(sueldos[i]*1.02);
                }

            }
        }

        //Encontrando al empleado de mayor y menor sueldo
        Double mayors = sueldos[0]; // variable inicializada con el primer sueldo y que almacenará al mayor sueldo del arreglo
        String mayorn = nombres[0]; // variable inicializada con el primer nombre y que almacenará al empleado con el sueldo mayor
        Double menors = sueldos[0]; // variable inicializada con el primer sueldo y que almacenará al menor sueldo del arreglo
        String menorn = nombres[0]; // variable inicializada con el primer nombre y que almacenará al empleado con el sueldo menor
        int cant = 0; // variable que almacena el numero de salarios mayores a 300
        for (int j=0; j<sueldos.length;j++) {
            if (sueldos[j] > mayors) {
                mayors = sueldos[j];
                mayorn = nombres[j];
            }
            if (sueldos[j] < menors) {
                menors = sueldos[j];
                menorn = nombres[j];

            }

            //contando cuantos salarios son mayores a $300
            if (sueldos[j] > 300.00){
                cant += 1;
            }

        }

        nombree1.setText(String.valueOf(nombres[0]));
        nombree2.setText(String.valueOf(nombres[1]));
        nombree3.setText(String.valueOf(nombres[2]));
        isss1.setText("$"+isssd[0].toString());
        isss2.setText("$"+isssd[1].toString());
        isss3.setText("$"+String.valueOf(isssd[2]));
        afp1.setText("$"+String.valueOf(afpd[0]));
        afp2.setText("$"+String.valueOf(afpd[1]));
        afp3.setText("$"+String.valueOf(afpd[2]));
        renta1.setText("$"+String.valueOf(rentad[0]));
        renta2.setText("$"+String.valueOf(rentad[1]));
        renta3.setText("$"+String.valueOf(rentad[2]));
        sueldo1.setText("$"+String.valueOf(sueldos[0]));
        sueldo2.setText("$"+String.valueOf(sueldos[1]));
        sueldo3.setText("$"+String.valueOf(sueldos[2]));
        msueldo.setText(String.valueOf(mayorn));
        mesueldo.setText(String.valueOf(menorn));
        cants.setText(String.valueOf(cant));
        bono.setText(msgbonos);




    }
    public void regresar(View v){
        finish();
    }
}