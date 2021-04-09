package com.example.firebase_login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.example.firebase_login.datos.Farmaco;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardGoogle extends AppCompatActivity {
//gestion de realtimedb
    public  static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refFarmacos = database.getReference("carritos");
    Query consultaordenada = refFarmacos.orderByChild("idcliente").equalTo(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid()));
    List<Farmaco> farmacos;
    ListView listafarmacos;
    //Variable para gestionar FirebaseAuth
    private FirebaseAuth mAuth;

    private TextView txtid, txtnombres, txtemail;
    private ImageView imagenUser;
    private Button btnLogOut, btnEliminarCuenta,verfarmacia;

    //Variables opcionales para desloguear de google tambien
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_google);
        imagenUser = findViewById(R.id.imagenUser);
        verfarmacia = findViewById(R.id.verfarmacia);
        txtnombres = findViewById(R.id.txtNombres);
        txtemail = findViewById(R.id.txtCorreo);

        btnLogOut = findViewById(R.id.btnLogout);
        btnEliminarCuenta = findViewById(R.id.btnEliminarCuenta);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //set datos:

        txtnombres.setText(currentUser.getDisplayName());
        txtemail.setText(currentUser.getEmail());
        //cargar imágen con glide:
        Glide.with(this).load(currentUser.getPhotoUrl()).into(imagenUser);

        //Configurar las gso para google signIn con el fin de luego desloguear de google
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //Cargando datos del carrito
        inicializar();

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cerrar sesión de firebase.
                mAuth.signOut();
                //Cerrar sesión con google tambien: Google sign out
                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //Abrir MainActivity con SigIn button
                        if(task.isSuccessful()){
                            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainActivity);
                            DashboardGoogle.this.finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "No se pudo cerrar sesión con google", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        btnEliminarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtener el usuario actual
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // Get the account
                GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                if (signInAccount != null) {
                    AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
                    //Re-autenticar el usuario para eliminarlo
                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //Eliminar el usuario
                                user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("dashBoard", "onSuccess:Usuario Eliminado");
                                        //llamar al metodo signOut para salir de aqui
                                        signOut();
                                    }
                                });
                            } else {
                                Log.e("dashBoard", "onComplete: Error al eliminar el usuario", task.getException());
                            }
                        }
                    });
                } else {
                    Log.d("dashBoard", "Error: reAuthenticateUser: user account is null");
                }
            }
        });//fin onClick

        verfarmacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardGoogle.this, FarmacosActivity.class);
                startActivity(intent);
            }
        });

    }//fin onCreate
    private void inicializar(){
        listafarmacos = findViewById(R.id.Listarfarmacos);
        //evento programado al hacer click sobre un producto del carrito
        listafarmacos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder ad = new AlertDialog.Builder(DashboardGoogle.this);
                ad.setMessage("Está seguro de eliminar el producto?")
                        .setTitle("Confirmación");

                ad.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DashboardGoogle.refFarmacos
                                .child(farmacos.get(position).getKey()).removeValue();

                        Toast.makeText(DashboardGoogle.this,
                                "Producto Eliminado!",Toast.LENGTH_SHORT).show();
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(DashboardGoogle.this,
                                "Operacion Cancelada!",Toast.LENGTH_SHORT).show();
                    }
                });

                ad.show();

            }
        });
        farmacos = new ArrayList<>();

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaordenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                farmacos.removeAll(farmacos);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Farmaco farmaco = dato.getValue(Farmaco.class);
                    farmaco.setKey(dato.getKey());
                    farmacos.add(farmaco);
                }

                AdaptadorFarmaco adapter = new AdaptadorFarmaco(DashboardGoogle.this,
                        farmacos );
                listafarmacos.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void signOut() {
        //sign out de firebase
        FirebaseAuth.getInstance().signOut();
        //sign out de "google sign in"
        mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //regresar al login screen o MainActivity
                //Abrir mainActivity para que inicie sesión o sign in otra vez.
                Intent IntentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                IntentMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(IntentMainActivity);
                DashboardGoogle.this.finish();
            }
        });
    }


}