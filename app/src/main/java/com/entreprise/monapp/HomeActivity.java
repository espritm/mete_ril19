package com.entreprise.monapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.entreprise.monapp.Helpers.Constant;

public class HomeActivity extends AppCompatActivity {

    HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Récupère les Extra
        String sLogin = getIntent().getStringExtra(Constant.LOGIN_EXTRA);

        //Vérifie si le fragment avait déjà été instancié
        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("homeFragmntTag");

        //Instancie le fragment
        if (homeFragment == null) {
            homeFragment = new HomeFragment();

            //On configure notre fragment
            homeFragment.setLoginUser(sLogin);
        }

        //Affiche le fragment à l'écran
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment, "homeFragmentTag").commit();
    }
}
