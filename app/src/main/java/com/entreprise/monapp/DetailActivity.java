package com.entreprise.monapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.entreprise.monapp.Helpers.Constant;
import com.entreprise.monapp.Modele.CityInfo;
import com.entreprise.monapp.Modele.ForcastDay;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ForcastDay mDayToShow;

    TextView mTextViewTitle;
    TextView mTextViewCondition;
    ImageView mImageviewCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Récupère les Extra
        try {
            String sJsonDayToShow = "";
            if (getIntent().hasExtra(Constant.DAY_CLICKED_EXTRA))
                sJsonDayToShow = getIntent().getStringExtra(Constant.DAY_CLICKED_EXTRA);
            //String sJsonCityInfo = getIntent().getStringExtra(Constant.CITY_INFO_EXTRA);

            mDayToShow = new ForcastDay(sJsonDayToShow);
            //mDayToShow.setCityInfo(new CityInfo(sJsonCityInfo));
        }
        catch (Exception e){
            //Affiche une alert dialog pour informer l'utilisateur qu'il y a eu une erreur
            new AlertDialog.Builder(this)
                    .setIcon(R.mipmap.ic_launcher)
                    .setTitle(R.string.app_name)
                    .setMessage(R.string.detail_activity_error_no_day_to_show)
                    .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            finish();
                        }
                    })
                    .show();

            return;
        }

        setTitle("Météo du " + mDayToShow.getDate());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextViewTitle = findViewById(R.id.activity_detail_title_textview);
        mTextViewCondition = findViewById(R.id.activity_detail_condition_textview);
        mImageviewCondition = findViewById(R.id.activity_detail_condition_imageview);

        mTextViewTitle.setText("Météo à : " + mDayToShow.getCityInfo().getName() + ", le " + mDayToShow.getDayLong() + " " + mDayToShow.getDate());
        mTextViewCondition.setText(mDayToShow.getCondition());
        Picasso.get().load(mDayToShow.getIcon()).into(mImageviewCondition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home)
            onBackPressed();
        else if (id == R.id.share)
            shareOnSocialNetwork();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void shareOnSocialNetwork(){
        //Intent pour partager
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Salut il va " + mDayToShow.getCondition() + " le " + mDayToShow.getDate());

        //Intent pour choisir comment partager
        Intent chooser = Intent.createChooser(intent, getResources().getString(R.string.shareTitle));

        startActivity(chooser);
    }
}