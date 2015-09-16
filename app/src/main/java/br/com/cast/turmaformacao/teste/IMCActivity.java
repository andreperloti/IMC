package br.com.cast.turmaformacao.teste;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Administrador on 14/09/2015.
 */
public class IMCActivity extends AppCompatActivity {
    private EditText editTextAltura;
    private EditText editTextPeso;
    private Button buttonCalcular;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        bindEditTextAltura();
        bindEditTextPeso();
        bindButtonCalcular();


    }

    private void bindButtonCalcular() {
        buttonCalcular = (Button) findViewById(R.id.buttonCalcular);
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double resultadoIMC;
                double peso = Double.parseDouble(editTextPeso.getText().toString());
                double altura = Double.parseDouble(editTextAltura.getText().toString());
                resultadoIMC = peso / (altura * altura);

                String message = getString(R.string.msg_resultIMC, resultadoIMC);

                TextView classificacao = (TextView) findViewById(R.id.editTextClassification);
                classificacao.setText(classificacaoIMC(resultadoIMC));
                Toast.makeText(IMCActivity.this, classificacaoIMC(resultadoIMC), Toast.LENGTH_SHORT).show();


                EditText result = (EditText) findViewById(R.id.editTextResult);
                result.setText(message);
                Toast.makeText(IMCActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String classificacaoIMC(double resultado){
        String mensagem = "";

        if(resultado<18){
            mensagem = "Magreza";
        }
        if(resultado>=18&&resultado<25){
            mensagem = "Normal";
        }
        if(resultado>=25&&resultado<30){
            mensagem = "Sobrepeso";
        }
        if(resultado>=30&&resultado<35){
            mensagem = "Obesidade Grau I";
        }
        if(resultado>=35&&resultado<40){
            mensagem = "Obesidade Grau II";
        }
        if(resultado>=40){
            mensagem = "Obesidade Grau III - Mórbida";
        }
        return mensagem;
    }

    private void bindEditTextPeso() {
        editTextPeso = (EditText) findViewById(R.id.editTextPeso);
    }

    private void bindEditTextAltura() {
        editTextAltura = (EditText) findViewById(R.id.editTextAltura);
    }
}
