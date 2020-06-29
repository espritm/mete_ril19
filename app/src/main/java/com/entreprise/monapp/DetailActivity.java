package com.entreprise.monapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    String sCityName = "Lyon";
    String sDayName = "Lundi";
    String sDateNAme = "29.06.2020";
    String sCondition = "Eclairices";
    String sIcon = "https://www.prevision-meteo.ch/style/images/icon/eclaircies.png";

    TextView mTextViewTitle;
    TextView mTextViewCondition;
    ImageView mImageviewCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle("Météo du " + sDateNAme);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextViewTitle = findViewById(R.id.activity_detail_title_textview);
        mTextViewCondition = findViewById(R.id.activity_detail_condition_textview);
        mImageviewCondition = findViewById(R.id.activity_detail_condition_imageview);

        mTextViewTitle.setText("Météo à : " + sCityName + ", le " + sDayName + " " + sDateNAme);
        mTextViewCondition.setText(sCondition);
        Picasso.get().load(sIcon).into(mImageviewCondition);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home)
            onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}