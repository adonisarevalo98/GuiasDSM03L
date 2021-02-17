package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText texto;
    Button btnconvertir, btnlimpiar;

    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = findViewById(R.id.ettexto);
        btnconvertir = findViewById(R.id.button1);
        btnlimpiar = findViewById(R.id.button2);

        textToSpeech = new TextToSpeech(getApplicationContext()
                , new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                //Estableciendo idioma
                Locale spanish = new Locale("es", "ES");
                if (status == TextToSpeech.SUCCESS){

                    int lang = textToSpeech.setLanguage(spanish);
                }
            }
        });
        btnconvertir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //capturando texto del EditText
                String tex = texto.getText().toString();
                //Convirtiendo el texto en sonido
                int sonido = textToSpeech.speak(tex,TextToSpeech.QUEUE_FLUSH, null);

            }
                                        });

        btnlimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               texto.setText("");
            }
        });
    }
}