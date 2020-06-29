package com.entreprise.monapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.entreprise.monapp.Modele.ForcastDay;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

public class MeteoAdapter extends ArrayAdapter<ForcastDay> {

    private ArrayList<ForcastDay> mListItems;

    public MeteoAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);

        mListItems = new ArrayList<ForcastDay>();
    }

    @Override
    public int getPosition(@Nullable ForcastDay item) {
        return mListItems.indexOf(item);
    }

    @Override
    public int getCount() {
        return mListItems.size();
    }

    @Nullable
    @Override
    public ForcastDay getItem(int position) {
        return mListItems.get(position);
    }

    @Override
    public void addAll(@NonNull Collection<? extends ForcastDay> collection) {
        mListItems.clear();

        mListItems.addAll(collection);

        notifyDataSetChanged();
    }

    @Override
    public void add(@Nullable ForcastDay object) {
        mListItems.add(object);

        notifyDataSetChanged();
    }

    public void add(@NonNull Collection<? extends ForcastDay> collection) {
        mListItems.addAll(collection);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = View.inflate(getContext(), R.layout.item_meteo_adapter, parent);

        ForcastDay dayToShow = getItem(position);

        //TODO : configurer la vue avec l'item à afficher.
        TextView city = v.findViewById(R.id.item_meteo_city_textview);
        TextView Country = v.findViewById(R.id.item_meteo_country_textview);
        TextView tmin = v.findViewById(R.id.item_meteo_tmin_textview);
        TextView tmax = v.findViewById(R.id.item_meteo_tmax_textview);
        TextView hour = v.findViewById(R.id.item_meteo_hour_textview);
        ImageView icon = v.findViewById(R.id.item_meteo_imageview);

        //city.setText(dayToShow.);
        tmin.setText(dayToShow.getTmin().toString());
        tmax.setText(dayToShow.getTmax().toString());
        Picasso.get().load(dayToShow.getIcon()).into(icon);
        hour.setText(dayToShow.getDate());

        return v;
    }
}
