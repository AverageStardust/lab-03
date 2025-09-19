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

/**
 * A fragment that will prompt a user to add a city.
 * Calls a listener with the new city.
 */
public class AddCityFragment extends DialogFragment {
    interface AddCityDialogListener {
        void addCity(City newCity);
    }

    private AddCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // attach listener
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new CityDetailsFragmentBuilder(getContext())
                .setTitle("Add a City")
                .setPositiveButton("Add")
                .build((City city) -> {
                    listener.addCity(city);
                });
    }
}