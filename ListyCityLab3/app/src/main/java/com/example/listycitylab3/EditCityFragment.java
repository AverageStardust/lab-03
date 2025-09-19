package com.example.listycitylab3;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {
    interface EditCityDialogListener {
        void editCity(City city, int position);
    }

    private EditCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof EditCityDialogListener) {
            listener = (EditCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement EditCityDialogListener");
        }
    }

    @Override
    public void on() {
        super.onStart();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_edit_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

        City city = (City)getArguments().getSerializable("city");
        editCityName.setText(city.getName());
        editProvinceName.setText(city.getProvince());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle _) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_edit_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        return builder
                .setView(view)
                .setTitle("Edit a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Edit", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();

                    listener.editCity(new City(cityName, provinceName),  getArguments().getInt("position"));
                })
                .create();
    }
}