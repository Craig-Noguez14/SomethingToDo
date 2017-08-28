package com.replace.pickupfinder.ui.event;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.replace.pickupfinder.Bootstrapper;
import com.replace.pickupfinder.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import com.replace.pickupfinder.ui.event.fragments.DatePickerFragment;

import com.replace.pickupfinder.ui.event.fragments.TimePickerFragment;
import Helpers.Utils;
import Models.Address;
import Models.SubCategory;
import Repositories.EventRepository;
import retrofit2.Retrofit;

public class EventActivity extends FragmentActivity {

    private static final String TAG = "EventActivity";
    private int _startMinute = -1;
    private int _startHour = -1;
    private int _endMinute = -1;
    private int _endHour = -1;
    private int _startYear = -1;
    private int _startMonth = -1;
    private int _startDay = -1;
    private int _endYear = -1;
    private int _endMonth = -1;
    private int _endDay = -1;
    private Place _eventPlace;
    private TextView _placeRequired;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        //((Bootstrapper) getApplication()).getComponent().inject(this);

        PopulateCategoryDropDown();
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                _eventPlace = place;
                _placeRequired.setVisibility(View.GONE);
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        autocompleteFragment.setHint("Enter location of event...");
        _placeRequired = (TextView) findViewById(R.id.place_required);
        _placeRequired.setVisibility(View.GONE);
        Button createEventButton = (Button) findViewById(R.id.createEvent);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    attemptEventCreation(_eventPlace);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*@Override
    public void onTimePicked(int layoutId, int hour, int minute) {
        if (layoutId == R.id.start_time_picker) {
            _startHour = hour;
            _startMinute = minute;
        } else {
            _endHour = hour;
            _endMinute = minute;
        }
    }*/

    /*@Override
    public void onDatePicked(int layoutId, int year, int month, int day) {
        if (layoutId == R.id.start_date_picker) {
            _startDay = day;
            _startMonth = month;
            _startYear = year;
        } else {
            _endDay = day;
            _endMonth = month;
            _endYear = year;
        }
    }*/

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

    private void attemptEventCreation(Place place) throws IOException {
        if (_eventPlace == null) {
            _placeRequired.setVisibility(View.VISIBLE);
        }

        EditText _eventDescription = (EditText) findViewById(R.id.eventDescription);
        // Reset errors.
        _eventDescription.setError(null);
        EventRepository _eventRepository = new EventRepository();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //TODO: MOVE THIS STUFF TO RULES ENGINE
        if (TextUtils.isEmpty(_eventDescription.getText())) {
            _eventDescription.setError(getString(R.string.error_field_required));
            _eventDescription.requestFocus();
        } else if (_startYear == -1 || _startMonth == -1 || _startDay == -1) {
            DatePickerFragment t = (DatePickerFragment) getFragmentManager().findFragmentById(R.id.start_date_picker);
            t.setErrorText();
        } else if (_endYear == -1 || _endMonth == -1 || _endDay == -1) {
            DatePickerFragment t = (DatePickerFragment) getFragmentManager().findFragmentById(R.id.end_date_picker);
            t.setErrorText();
        }

        else {
            //Address address = null;
            /*Geocoder mGeoCoder = new Geocoder(this, Locale.getDefault());

                new Thread(() -> {
                    try {
                        List<com.doctoror.geocoder.Address> a = null;
                        a = mGeoCoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1, true);
                        Address address = new Address(place.getLatLng().latitude,
                                place.getLatLng().longitude,
                                a.get(0).getStreetAddress(),
                                a.get(0).getLocality(),
                                a.get(0).getAdministrativeAreaLevel1(),
                                a.get(0).getPostalCode());
                    } catch (GeocoderException e) {
                        e.printStackTrace();
                    }
                }).start();*/


            /*address = new Address(place.getLatLng().latitude,
                        place.getLatLng().longitude,
                        a.get(0).getStreetAddress(),
                        a.get(0).getLocality(),
                        a.get(0).getAdministrativeAreaLevel1(),
                        a.get(0).getPostalCode());*/


            /*LocationRepository locRepo = new LocationRepository();

            locRepo.getService().getCityResults(place.getId(), getString(R.string.google_geocoder_api)).enqueue(new Callback<GeoCodeLocation>() {
                @Override
                public void onResponse(Call<GeoCodeLocation> call, Response<GeoCodeLocation> response) {
                    GeoCodeLocation places = response.body();
                    places.PlaceId = "t";
                }

                @Override
                public void onFailure(Call<GeoCodeLocation> call, Throwable t) {
                    t.printStackTrace();
                }
            });*/

            /*Address address = new Address(1,
                    1,
                    place.getAddress().toString(),
                    "",
                    "",
                    "");*/

            /*Date startDate = new Date(_startYear, _startMonth, _startDay, _startHour, _startMinute);
            Date endDate = new Date(_endYear, _endMonth, _endDay, _endHour, _endMinute);

            Models.Event event = new Models.Event(_eventDescription.getText().toString(), startDate, endDate, prefs.getString("email", ""), EventType.Public, SubCategory.Baseball, address);

            _eventRepository.getService().CreateEvent(event).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d("SUCCESS", "SUCCESS");
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("FAIL", "FAIL");
                }
            });*/
        }
    }


}
