package com.replace.pickupfinder;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import Enums.SubCategory;
import Helpers.TimePickerFragment;
import Helpers.Utils;

public class EventActivity extends FragmentActivity implements TimePickerFragment.OnTimePickedListener {

    private static final String TAG = "EventActivity";
    int hourOfDay;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        PopulateCategoryDropDown();
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        autocompleteFragment.setHint("Enter location of event...");
    }

    @Override
    public void onTimePicked(int layoutId, int hour, int minute) {
        Log.i(TAG, Integer.toString(layoutId));
        Log.i(TAG, Integer.toString(hour));
        Log.i(TAG, Integer.toString(minute));
        // Here you can do whatever needed with value obtained from the fragment
    }

    //TODO: MOVE TO A SERVICE
    protected void PopulateCategoryDropDown() {
        Spinner spinner = (Spinner) findViewById(R.id.category);
        String[] subCategories = Utils.getNames(SubCategory.class);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<SubCategory> adapter = new ArrayAdapter<SubCategory>(this, android.R.layout.simple_spinner_item, SubCategory.values());
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

}
