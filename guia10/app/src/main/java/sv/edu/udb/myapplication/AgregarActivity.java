package sv.edu.udb.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import sv.edu.udb.myapplication.databinding.ActivityAgregarBinding;
import sv.edu.udb.myapplication.interfaces.Servicio;
import sv.edu.udb.myapplication.models.Producto;
import sv.edu.udb.myapplication.models.Respuesta;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class AgregarActivity extends AppCompatActivity {
    ActivityAgregarBinding binding;
    String accion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgregarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        inicializaInterface();
    }

    public void inicializaInterface(){
        Bundle datos = getIntent().getExtras();
        binding.edtCodigo.setText(datos.getString("codigo",""));
        binding.edtDescripcion.setText(datos.getString("descripcion",""));
        binding.edtPrecio.setText(""+datos.getFloat("precio",0.00f));
        accion = datos.getString("accion","a");
        if (accion.equals("e"))
            binding.edtCodigo.setEnabled(false);
    }


    public void guardar(View view) {
        if (!validar()) return ;
        if (accion.equals("a"))
            agregar();
        else
            actualizar();

    }

    public boolean validar() {
        String codigo = binding.edtCodigo.getText().toString().trim();
        String descripcion = binding.edtDescripcion.getText().toString().trim();
        float precio = Float.valueOf(binding.edtPrecio.getText().toString().trim());
        if (codigo.equals("")) {
            binding.edtCodigo.setError("digite código");
            return false;
        }
        if (descripcion.equals("")) {
            binding.edtDescripcion.setError("digite descripción");
            return false;
        }
        if (precio <= 0.00f) {
            binding.edtPrecio.setError("precio debe ser positivo");
            return false    ;
        }
        return true;
    }

    public void agregar() {
        String codigo = binding.edtCodigo.getText().toString().trim();
        String descripcion =binding.edtDescripcion.getText().toString().trim();
        float precio = Float.valueOf(binding.edtPrecio.getText().toString().trim());
        Producto producto = new Producto(codigo,descripcion,precio);

        Call<Respuesta> call  = Servicio.service.insertProduct(producto);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if (response.code()==200) {
                    Toast.makeText(getBaseContext(),"Registro Agregado satisfactoriamente",
                            Toast.LENGTH_LONG ).show();


                    finish();

                } else
                {
                    Toast.makeText(getBaseContext(),"Error : " + response.code(),
                            Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Error : " + t.getMessage(),
                        Toast.LENGTH_LONG ).show();
            }
        });
    }

    public void actualizar() {
        String codigo =  binding.edtCodigo.getText().toString().trim();
        String descripcion = binding.edtDescripcion.getText().toString().trim();
        float precio = Float.valueOf(binding.edtPrecio.getText().toString().trim());

        Producto producto = new Producto(codigo,descripcion,precio);
        Call<Respuesta> call = Servicio.service.updateProduct(codigo,producto);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                if (respuesta.getResultado()==1) {
                    Toast.makeText(getBaseContext(),"Registro actualizado",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getBaseContext(),"Error:" +response.code(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Error:" + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public void cancelar(View view) {
        finish();
    }

}