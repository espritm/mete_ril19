package com.entreprise.monapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.entreprise.monapp.Helpers.Constant;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    HomeFragment homeFragment;

    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Request permission
        //En pratique : il faut informer l'utilisateur avec une AlertDialog (pourquoi l'app a besoin de l'autorisation)
        //Une fois validée, l'AlertDialog disparait et on execute suivant :
        //requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        //Afficher le menu burger
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

        }

        return true;
    }

}

