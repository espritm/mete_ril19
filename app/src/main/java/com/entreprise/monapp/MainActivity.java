package com.entreprise.monapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.entreprise.monapp.Helpers.Constant;
import com.entreprise.monapp.Helpers.EnumDegres;
import com.entreprise.monapp.Helpers.HideKeyboardHelper;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout;
    Button btnLogin;
    EditText edtLogin;
    EditText edtPassword;
    //TextView txvError;
    String sLogin;
    String sPwd;

    TextInputLayout inputLayoutLogin;
    TextInputLayout inputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPreferences();

        //Si l'activité est recréée, on récupère dans le Bundle les valeurs saisies précédement par l'utilisateur
        if (savedInstanceState != null && savedInstanceState.containsKey("login"))
            sLogin = savedInstanceState.getString("login");
        if (savedInstanceState != null && savedInstanceState.containsKey("password"))
            sPwd = savedInstanceState.getString("password");

        //Charge le contenu du fichier XML
        setContentView(R.layout.activity_main);

        //Retrouve les références des vues
        layout = findViewById(R.id.layout);
        btnLogin = findViewById(R.id.loginButton);
        edtLogin = findViewById(R.id.loginEditText);
        edtPassword = findViewById(R.id.pwdEditText);
        //txvError = findViewById(R.id.errorTxv);

        inputLayoutLogin = findViewById(R.id.inputLayoutLogin);
        inputLayoutPassword = findViewById(R.id.inputLayoutPassword);

        //Pré-rempli l'editText avec le dernier login utilisé
        SharedPreferences pref = getSharedPreferences(Constant.PREF_FILE_NAME, Context.MODE_PRIVATE);
        String sPreviousLogin = pref.getString(Constant.LOGIN, "");
        if (sPreviousLogin != ""){
            edtLogin.setText(sPreviousLogin);
            edtLogin.requestFocus();
        }

        //Setup pour cacher le clavier quand on cliquer ailleurs
        HideKeyboardHelper.setupUI(layout, this);

        //Configure les vues
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sLogin = edtLogin.getText().toString();
                sPwd = edtPassword.getText().toString();

                if (sLogin.equals("maxime")) {
                    //Save login into SharedPreference
                    SharedPreferences pref = getSharedPreferences(Constant.PREF_FILE_NAME, Context.MODE_PRIVATE);
                    pref.edit().putString(Constant.LOGIN, sLogin).apply();

                    //Lance la page d'accueil, passe en paramètre le login saisi par l'utilisateur
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra(Constant.LOGIN_EXTRA, sLogin);
                    startActivity(intent);
                }
                else {
                    //Configure le texte du TextView
                    /*txvError.setText(R.string.errorBadLogin);

                    //Rend visible le TextView
                    txvError.setVisibility(View.VISIBLE);*/

                    //Affiche un message d'erreur grave au TextInputLayout
                    inputLayoutLogin.setError(getResources().getString(R.string.errorBadLogin));
                    inputLayoutLogin.setErrorEnabled(true);
                }
            }
        });

        edtLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Cache l'erreur
                inputLayoutLogin.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        /*edtLogin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                //txvError.setVisibility(View.GONE);

                //Cache l'erreur
                inputLayoutLogin.setErrorEnabled(false);

                return false;
            }
        });*/

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //Après le onPause, on enregistre dans le Bundle les valeurs saisies par l'utilisateur
        outState.putString("login", sLogin);
        outState.putString("password", sPwd);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initPreferences(){

        SharedPreferences pref = getSharedPreferences(Constant.PREF_FILE_NAME, Context.MODE_PRIVATE);

        //Par défaut, appli en degrés Celsius
        int value = pref.getInt(Constant.DEGRE, -1);
        if (value == -1)
            pref.edit().putInt(Constant.DEGRE, EnumDegres.Celsius.ordinal()).apply();
    }
}
