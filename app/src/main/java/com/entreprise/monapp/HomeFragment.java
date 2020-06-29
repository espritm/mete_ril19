package com.entreprise.monapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.entreprise.monapp.Modele.DonneesMeteo;

import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    DonneesMeteo mDonneesMeteo;

    TextView welcomeTxv;
    String sLogin;
    //Button mBtnShowDetailActivity;
    ListView mListView;
    SwipeRefreshLayout mRefresher;

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
        //mBtnShowDetailActivity = v.findViewById(R.id.fragmentHome_Show_DetailActivity_Button);
        mListView = v.findViewById(R.id.fragment_home_listview);
        mRefresher = v.findViewById(R.id.fragment_home_refresher);

        if(sLogin != null)
            welcomeTxv.setText(String.format(getResources().getString(R.string.WelcomeText), sLogin));
        else
            welcomeTxv.setText(String.format(getResources().getString(R.string.WelcomeText), ""));

        /*mBtnShowDetailActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Exécuté lorsque l'utilisateur clique le bouton
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });*/

        callWebService();

        return v;
    }

    private void callWebService() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // Build URL
        String urlBase = getResources().getString(R.string.URL_Webservice_Base);
        String url = urlBase + "lyon";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            mDonneesMeteo = new DonneesMeteo(response);
                        } catch (JSONException e)
                        {
                            showError(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        showError(error.getMessage());
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void showError(final String sMessage){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.ErrorVolleyTitle);
        builder.setMessage(sMessage);
        builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Executé du code à la fermeture du dialog
                //Affiche un texte temporaire à l'écran, qui disparait après quelques secondes.
                Toast.makeText(getActivity(), "ERROR : " + sMessage, Toast.LENGTH_LONG).show();
            }
        });
        builder.setCancelable(false);

        builder.show();
    }
}
