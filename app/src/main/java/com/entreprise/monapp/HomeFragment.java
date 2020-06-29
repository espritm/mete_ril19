package com.entreprise.monapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextView welcomeTxv;
    String sLogin;
    Button mBtnShowDetailActivity;

    public HomeFragment() {
        // Required empty public constructor
    }

    public void setLoginUser(String login)
    {
        sLogin = login;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        welcomeTxv = v.findViewById(R.id.welcomeTextView);
        mBtnShowDetailActivity = v.findViewById(R.id.fragmentHome_Show_DetailActivity_Button);

        if(sLogin != null)
            welcomeTxv.setText(String.format(getResources().getString(R.string.WelcomeText), sLogin));
        else
            welcomeTxv.setText(String.format(getResources().getString(R.string.WelcomeText), ""));

        mBtnShowDetailActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Exécuté lorsque l'utilisateur clique le bouton
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
