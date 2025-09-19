package com.example.listycitylab3;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * A fragment that will prompt a user to edit a city.
 * Calls a listener with the edited city and position to easily update data.
 */
public class EditCityFragment extends DialogFragment {
    interface EditCityDialogListener {
        void editCity(City editedCity, int position);
    }

    private EditCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // attach listener
        if (context instanceof EditCityDialogListener) {
            listener = (EditCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement EditCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle _) {
        City initalCity = (City)getArguments().getSerializable("city");

        return new CityDetailsFragmentBuilder(getContext())
                .setTitle("Edit a City")
                .setName(initalCity.getName())
                .setProvince(initalCity.getProvince())
                .setPositiveButton("Update")
                .build((editedCity) -> {
                    int position = getArguments().getInt("position");
                    listener.editCity(editedCity,  position);
                });
    }
}