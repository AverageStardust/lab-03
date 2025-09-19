package com.example.listycitylab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        AddCityFragment.AddCityDialogListener, EditCityFragment.EditCityDialogListener {

    private ArrayList<City> dataList;
    private CityArrayAdapter cityAdapter;

    // callback for adding a new city
    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    // callback for editing an existing city
    @Override
    public  void editCity(City city, int position) {
        dataList.set(position, city);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // default data on start-up
        String[] cities = { "Edmonton", "Vancouver", "Montreal", "Purgatory", "Toronto" };
        String[] provinces = { "AB", "BC", "QC", "SK", "ON" };

        // initialize data
        dataList = new ArrayList<City>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }

        // create adapter with data
        cityAdapter = new CityArrayAdapter(this, dataList);

        // attach adapter to view
        ListView cityList = findViewById(R.id.city_list);
        cityList.setAdapter(cityAdapter);

        // create add button
        FloatingActionButton button = findViewById(R.id.button_add_city);
        button.setOnClickListener(v -> {
            new AddCityFragment()
                    .show(getSupportFragmentManager(), "Add City");
        });

        // create listen for edits
        cityList.setOnItemClickListener((adapter, view, position, id)-> {
            createEditFragment(position)
                    .show(getSupportFragmentManager(), "Edit City");
        });

    }

    EditCityFragment createEditFragment(int position) {
        City city = dataList.get(position);

        // create arguments with city and position
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        args.putInt("position", position);

        EditCityFragment fragment = new EditCityFragment();
        fragment.setArguments(args);

        return fragment;
    }
}