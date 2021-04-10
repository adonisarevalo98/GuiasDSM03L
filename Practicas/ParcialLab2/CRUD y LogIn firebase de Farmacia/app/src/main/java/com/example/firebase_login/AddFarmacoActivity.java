package com.example.firebase_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase_login.datos.Carrito;
import com.example.firebase_login.datos.Farmaco;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddFarmacoActivity extends AppCompatActivity {
    EditText edtPrecio, edtNombre;
    String key="",nombre="",accion="";
    Double precio;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farmaco);
        // Inicializar Firebase Auth

        //set datos:


        inicializar();
    }
    private void inicializar() {
        edtNombre = findViewById(R.id.edtNombre);
        edtPrecio = findViewById(R.id.edtPrecio);

        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        precio = datos.getDouble("precio");
        nombre=datos.getString("nombre");
        accion=datos.getString("accion");
        edtPrecio.setText(precio.toString());
        edtNombre.setText(nombre);
    }
    public void guardar(View v){
        String nombre = edtNombre.getText().toString();
        String precio = edtPrecio.getText().toString();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String idcliente =  currentUser.getUid();
        // Se forma objeto persona
        Carrito carrito = new Carrito(idcliente,nombre,Double.parseDouble(precio));

        //Agregar usando push()
            FarmacosActivity.refCarritos.push().setValue(carrito);
        Toast.makeText(AddFarmacoActivity.this,
                "Producto Agregado al carrito!",Toast.LENGTH_SHORT).show();

        finish();
    }
    public void cancelar(View v){
        finish();
    }
}