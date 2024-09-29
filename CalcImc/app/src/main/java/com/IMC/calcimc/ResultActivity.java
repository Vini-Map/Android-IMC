package com.IMC.calcimc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    TextView resultado;
    String strNome;
    Float fltAltura, fltPeso, fltResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resultado = (TextView) findViewById(R.id.ResultTitle);

        Intent intent = getIntent();
        strNome = intent.getStringExtra("nome");
        fltAltura = Float.parseFloat(intent.getStringExtra("altura"));
        fltPeso = Float.parseFloat(intent.getStringExtra("peso"));
        fltResult = fltPeso / (fltAltura * fltAltura);

        String strResult = "Olá " + strNome + " !";
        strResult = strResult + "\n" + "IMC = " + fltResult.toString();

        if(fltResult < 17){
            strResult = strResult + "\n" + "Muito abaixo do peso";

        } else if (fltResult < 18.49) {
            strResult = strResult + "\n" + "Abaixo do peso";

        }else if (fltResult < 24.99) {
            strResult = strResult + "\n" + "Peso normal";

        }else if (fltResult < 29.99) {
            strResult = strResult + "\n" + "Acima do normal";

        }else if (fltResult < 34.99) {
            strResult = strResult + "\n" + "Obesidade I";

        }else if (fltResult < 39.99) {
            strResult = strResult + "\n" + "Obesidade II (severa)";
        }else
            strResult = strResult + "\n" + "Obesidade III (mórbida)";

        resultado.setText(strResult);

        Button botaoVoltar = findViewById(R.id.voltar);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Voltar para a activity anterior
                finish();
            }
        });
    }
}