package br.com.cast.turmaformacao.teste;


import android.widget.EditText;

/**
 * Created by Administrador on 15/09/2015.
 */
public class FormHelper {

    private FormHelper(){
        super();
    }

    public static boolean validateRequired(String requiredMessage, EditText... editTexts ){
        boolean hasRequired =false;

        for(EditText editText: editTexts){
            String textvalue = editText.getText().toString();
            if(textvalue.trim().isEmpty()){
                editText.setError(requiredMessage);
                hasRequired = true;
            }
        }
        return hasRequired;
    }
}

