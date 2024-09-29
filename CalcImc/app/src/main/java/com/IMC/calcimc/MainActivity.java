package com.IMC.calcimc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nome, altura, peso;
    private TextView imcResultado, categoriaIMC;
    private Button botaoCalcular, botaoLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.NameInput);
        altura = findViewById(R.id.AlturaInput);
        peso = findViewById(R.id.PesoInput);
        botaoCalcular = findViewById(R.id.Calcular);
        botaoLimpar = findViewById(R.id.botaoLimpar);




        botaoCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularImcEExibir();

            }
        });


        botaoLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparCampos();
            }
        });
    }

    private void calcularImcEExibir() {

        if(TextUtils.isEmpty(nome.getText().toString())){
            nome.setError("Campo Obrigatorio");
            return;
        }
        if(TextUtils.isEmpty(altura.getText().toString())){
            altura.setError("Campo Obrigatorio");
            return;
        }
        if(TextUtils.isEmpty(peso.getText().toString())){
            peso.setError("Campo Obrigatorio");
            return;
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("nome",nome.getText().toString());
        intent.putExtra("altura",altura.getText().toString());
        intent.putExtra("peso",peso.getText().toString());
        startActivity(intent);


    }

    private void limparCampos() {
        nome.setText("");
        altura.setText("");
        peso.setText("");
    }


}
