package com.example.miaad.examen1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView texto;
    private Button toast;
    private Button counter;
    private Button zero;
    private int contador;

    public static final String GAME_PREFERENCES = "GamePrefs";
    public static final String GAME_PREFERENCES_CONTADOR = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         texto = (TextView) findViewById(R.id.texto);
         toast = (Button) findViewById(R.id.toast);
         counter = (Button) findViewById(R.id.count);
         zero = (Button) findViewById(R.id.zero);

         contador = Integer.parseInt(texto.getText().toString());

         guardarPreferences();
         cargarPreferences();

        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeToast();
            }
        });

        counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTexto();
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartCounter();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        this.guardarPreferences();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.cargarPreferences();
    }

    private void restartCounter() {
        contador = 0;
        texto.setText(String.valueOf(contador));
    }

    private void setTexto() {
        contador++;
        texto.setText(String.valueOf(contador));
        guardarPreferences();
    }

    private void makeToast() {
        Context context = getApplicationContext();
        CharSequence text = "Contador = " + texto.getText();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void guardarPreferences() {
        SharedPreferences settings = getSharedPreferences(GAME_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putInt(GAME_PREFERENCES_CONTADOR, contador);
        prefEditor.commit();
    }

    private void cargarPreferences() {
        SharedPreferences settings = getSharedPreferences(GAME_PREFERENCES, MODE_PRIVATE);
        if (settings.contains(GAME_PREFERENCES_CONTADOR) == true) {
            texto.setText(String.valueOf(contador)); }
    }


}
