package com.entreprise.monapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.entreprise.monapp.Helpers.HideKeyboardHelper;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout;
    Button btnLogin;
    EditText edtLogin;
    EditText edtPassword;
    TextView txvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Charge le contenu du fichier XML
        setContentView(R.layout.activity_main);

        //Retrouve les références des vues
        layout = findViewById(R.id.layout);
        btnLogin = findViewById(R.id.loginButton);
        edtLogin = findViewById(R.id.loginEditText);
        edtPassword = findViewById(R.id.pwdEditText);
        txvError = findViewById(R.id.errorTxv);

        //Setup pour cacher le clavier quand on cliquer ailleurs
        HideKeyboardHelper.setupUI(layout, this);

        //Configure les vues
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sLogin = edtLogin.getText().toString();
                String sPwd = edtPassword.getText().toString();

                if (sLogin.equals("maxime"))
                    ;//Le login est bon, on lance la page d'accueil
                else
                {
                    //Configure le texte du TextView
                    txvError.setText(R.string.errorBadLogin);

                    //Rend visible le TextView
                    txvError.setVisibility(View.VISIBLE);
                }

            }
        });

        edtLogin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                txvError.setVisibility(View.GONE);

                return false;
            }
        });
    }
}
