package com.example.firebase_login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase_login.datos.Farmaco;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FarmacosActivity extends AppCompatActivity {
    public  static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refFarmacos = database.getReference("farmacos");
    public static DatabaseReference refCarritos = database.getReference("carritos");
    Query consultaordenada = refFarmacos.orderByChild("nombre");
    List<Farmaco> farmacos;
    ListView listafarmacos;
    //Variable para gestionar FirebaseAuth
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmacos);
        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        inicializar();
    }
    private void inicializar(){
        listafarmacos = findViewById(R.id.Listarfarmacos);
        //evento programado al hacer click sobre un producto del carrito
        listafarmacos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), AddFarmacoActivity.class);

                intent.putExtra("accion","a"); // Editar
                intent.putExtra("key", farmacos.get(i).getKey());
                intent.putExtra("nombre",farmacos.get(i).getNombre());
                intent.putExtra("precio",farmacos.get(i).getPrecio());
                startActivity(intent);

            }
        });
        farmacos = new ArrayList<>();

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaordenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de farmacos
                farmacos.removeAll(farmacos);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Farmaco farmaco = dato.getValue(Farmaco.class);
                    farmaco.setKey(dato.getKey());
                    farmacos.add(farmaco);
                }

                AdaptadorFarmaco adapter = new AdaptadorFarmaco(FarmacosActivity.this,
                        farmacos );
                listafarmacos.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}