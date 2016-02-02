package br.com.cast.turmaformacao.teste.controllers.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.security.SignatureException;

import br.com.cast.turmaformacao.teste.util.FormHelper;
import br.com.cast.turmaformacao.teste.R;


/**
 * Created by Administrador on 14/09/2015.
 */
public class IMCActivity extends AppCompatActivity {
    private EditText editTextHeight;
    private EditText editTextWeight;
    private FloatingActionButton floatingActionButtonCalculate;
    private Toolbar toolbar;
    private TextView result;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        bindToolbar();
        bindEditTextAltura();
        bindEditTextPeso();
        bindTextViewResult();
        bindButtonCalcular();
    }

    private void bindTextViewResult() {
        result = (TextView) findViewById(R.id.textViewResult);
    }

    public void bindToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.calculate_imc));
        setSupportActionBar(toolbar);
    }

    private void bindButtonCalcular() {
        floatingActionButtonCalculate = (FloatingActionButton) findViewById(R.id.floatingActionButtonCalculate);
        floatingActionButtonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double resultadoIMC;
                String validation = "Campo Obrigatorio!";
                if (!FormHelper.validateRequired(validation, editTextHeight, editTextWeight)) {
                    resultadoIMC = calcularIMC();

//                    String message = String.format("Seu IMC é: %.2f"+ resultadoIMC +"\n"+ classificacaoIMC(resultadoIMC));

//                    Toast.makeText(IMCActivity.this, classificacaoIMC(resultadoIMC), Toast.LENGTH_LONG).show();


                    String classificacao = classificacaoIMC(resultadoIMC);

                    result.setText(String.format("Seu IMC é: %.2f \nClassificação: ", resultadoIMC) + classificacao);


//                    Toast.makeText(IMCActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private double calcularIMC() {
        double resultadoIMC;
        double peso = Double.parseDouble(editTextWeight.getText().toString());
        String textAltura = editTextHeight.getText().toString();

        double altura = Double.parseDouble(textAltura.replace(",", "."));
        if (altura > 3){
            altura = altura /100;
            resultadoIMC = peso / (altura * altura);
        }else{
            resultadoIMC = peso / (altura * altura);
        }
        return resultadoIMC;
    }

    private String classificacaoIMC(double resultado) {
        String mensagem = "";

        if (resultado < 18) {
            mensagem = "Magreza";
        }
        if (resultado >= 18 && resultado < 25) {
            mensagem = "Normal";
        }
        if (resultado >= 25 && resultado < 30) {
            mensagem = "Sobrepeso";
        }
        if (resultado >= 30 && resultado < 35) {
            mensagem = "Obesidade Grau I";
        }
        if (resultado >= 35 && resultado < 40) {
            mensagem = "Obesidade Grau II";
        }
        if (resultado >= 40) {
            mensagem = "Obesidade Grau III - Mórbida";
        }
        return mensagem;
    }

    private void bindEditTextPeso() {
        editTextWeight = (EditText) findViewById(R.id.editTextPeso);
        editTextWeight.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {


                    return true;
                }
                return false;
            }
        });
    }

    private void bindEditTextAltura() {
        editTextHeight = (EditText) findViewById(R.id.editTextAltura);
    }
}
