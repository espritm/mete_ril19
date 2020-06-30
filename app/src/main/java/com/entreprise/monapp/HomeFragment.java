package com.entreprise.monapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.entreprise.monapp.Helpers.Constant;
import com.entreprise.monapp.Modele.DonneesMeteo;
import com.entreprise.monapp.Modele.ForcastDay;

import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    DonneesMeteo mDonneesMeteo;

    MeteoAdapter mAdapter;

    TextView welcomeTxv;
    EditText mEdtSearchCity;
    ImageButton mBtnSearch;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        welcomeTxv = v.findViewById(R.id.welcomeTextView);
        mEdtSearchCity = v.findViewById(R.id.searchCityedittext);
        mBtnSearch = v.findViewById(R.id.btnSearch);
        //mBtnShowDetailActivity = v.findViewById(R.id.fragment;Home_Show_DetailActivity_Button);
        mListView = v.findViewById(R.id.fragment_home_listview);
        mRefresher = v.findViewById(R.id.fragment_home_refresher);

        if(sLogin != null)
            welcomeTxv.setText(String.format(getResources().getString(R.string.WelcomeText), sLogin));
        else
            welcomeTxv.setText(String.format(getResources().getString(R.string.WelcomeText), ""));

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefresher.setRefreshing(true);
                callWebService();
                }
        });

        //Gère une ville par défaut dans les SharedPreferences
        SharedPreferences pref = getActivity().getSharedPreferences(Constant.PREF_FILE_NAME, Context.MODE_PRIVATE);
        String sCity = pref.getString(Constant.DEFAULT_CITY, "");
        if (sCity == "") {
            sCity = "Lyon";
            pref.edit().putString(Constant.DEFAULT_CITY, sCity).apply();
        }
        mEdtSearchCity.setText(sCity);

        /*mBtnShowDetailActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Exécuté lorsque l'utilisateur clique le bouton
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });*/

        mAdapter = new MeteoAdapter(getActivity(), 0);

        mListView.setAdapter(mAdapter);

        mRefresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                callWebService();
            }
        });

        mRefresher.post(new Runnable() {
            @Override
            public void run() {

                mRefresher.setRefreshing(true);

                callWebService();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ForcastDay dayClicked = mAdapter.getItem(position);

                String sJson = "";
                try {
                    sJson = dayClicked.serrializeToJson();
                } catch (JSONException e) {
                    showError(getResources().getString(R.string.home_fragment_error));
                    return;
                }

                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(Constant.DAY_CLICKED_EXTRA, sJson);
                //intent.putExtra(Constant.CITY_INFO_EXTRA, dayClicked.getCityInfo().getsInitialJson());
                startActivity(intent);
            }
        });

        return v;
    }

    private void callWebService() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // Build URL
        String urlBase = getResources().getString(R.string.URL_Webservice_Base);
        String url = urlBase + mEdtSearchCity.getText();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            mDonneesMeteo = new DonneesMeteo(response);

                            mAdapter.addAll(mDonneesMeteo.getListFcstDay());

                            mRefresher.setRefreshing(false);
                        } catch (JSONException e) {
                            showError(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showError(error.getMessage());

                        mRefresher.setRefreshing(false);
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
