package com.IMC.calcimc;

import android.os.Bundle;
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
        imcResultado = findViewById(R.id.ImcResultado);
        categoriaIMC = findViewById(R.id.CategoriaIMC);

        botaoCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularImcEExibir();
                String nomeValor = nome.getText().toString();
                atualizarNomeTextView(nomeValor);
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
        String alturaTexto = altura.getText().toString();
        String pesoTexto = peso.getText().toString();

        if (alturaTexto.isEmpty() || pesoTexto.isEmpty()) {
            imcResultado.setText("Preencha todos os campos.");
            categoriaIMC.setText(""); // Limpa o texto da categoria
            return;
        }

        try {
            // Obtenha os valores dos campos EditText e converta para double
            double alturaValor = Double.parseDouble(alturaTexto);
            double pesoValor = Double.parseDouble(pesoTexto);

            // Verifique se a altura e o peso são válidos
            if (alturaValor <= 0 || pesoValor <= 0) {
                imcResultado.setText("Altura e peso devem ser maiores que zero.");
                categoriaIMC.setText(""); // Limpa o texto da categoria
                return;
            }

            double imc = pesoValor / (alturaValor * alturaValor);
            String categoriaIMCTexto = obterCategoriaIMC(imc);
            String resultadoIMC = String.format("IMC calculado: %.2f", imc);

            imcResultado.setText(resultadoIMC);
            categoriaIMC.setText(categoriaIMCTexto);
        } catch (NumberFormatException e) {
            imcResultado.setText("Entrada inválida. Verifique os valores.");
            categoriaIMC.setText(""); // Limpa o texto da categoria
        }
    }

    private String obterCategoriaIMC(double imc) {
        if (imc < 18.5) {
            return "Categoria: Abaixo do peso";
        } else if (imc < 24.9) {
            return "Categoria: Peso normal";
        } else if (imc < 29.9) {
            return "Categoria: Sobrepeso";
        } else {
            return "Categoria: Obesidade";
        }
    }

    private void atualizarNomeTextView(String nome) {
        if (nome != null && !nome.isEmpty()) {
            TextView textViewNome = findViewById(R.id.nome);
            textViewNome.setText(nome);
        }
    }

    private void limparCampos() {
        nome.setText("");
        altura.setText("");
        peso.setText("");
        imcResultado.setText("");
        categoriaIMC.setText("");
    }
}
