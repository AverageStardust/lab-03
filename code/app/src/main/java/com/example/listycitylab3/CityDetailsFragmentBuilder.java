package com.example.listycitylab3;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.ReturnThis;


/**
 * A common builder for "Add City" and "Edit City" dialogs
 */
public class CityDetailsFragmentBuilder {
    interface CityDetailsListener {
        void submitCity(City city);
    }

    private final Context context;
    private String title = "Submit a city";
    private String name = "";
    private String province = "";
    private String positiveButton = "Submit";

    CityDetailsFragmentBuilder(Context context) {
        this.context = context;
    }

    public CityDetailsFragmentBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CityDetailsFragmentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CityDetailsFragmentBuilder setProvince(String province) {
        this.province = province;
        return this;
    }

    public CityDetailsFragmentBuilder setPositiveButton(String positiveButton) {
        this.positiveButton = positiveButton;
        return this;
    }

    public AlertDialog build(CityDetailsListener listener) {
        // get fields
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_city_details, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

        // fill fields
        editCityName.setText(name);
        editProvinceName.setText(province);

        // create dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder
                .setView(view)
                .setTitle(title)
                .setNegativeButton("Cancel", null)
                .setPositiveButton(positiveButton, (dialog, which) -> {
                    // build city from fields
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    City city = new City(cityName, provinceName);
                    listener.submitCity(city);
                })
                .create();
    }
}
