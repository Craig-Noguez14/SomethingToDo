package com.replace.pickupfinder.ui.event;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.doctoror.geocoder.Geocoder;
import com.doctoror.geocoder.GeocoderException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.patloew.rxlocation.RxLocation;
import com.replace.pickupfinder.Bootstrapper;
import com.replace.pickupfinder.R;
import com.replace.pickupfinder.ui.base.BaseActivity;
import com.replace.pickupfinder.ui.event.fragments.DatePickerFragment;
import com.replace.pickupfinder.ui.event.fragments.TimePickerFragment;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import Helpers.Utils;
import Models.Address;
import Models.Event;
import Models.EventType;
import Models.SubCategory;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import okhttp3.ResponseBody;

public class NewEventActivity extends BaseActivity implements EventMvpView, DatePickerFragment.OnDatePickedListener {

    private static final String TAG = "EventActivity";

    @Inject
    EventMvpPresenter<EventMvpView> mPresenter;

    @Inject
    RxLocation rxLocation;

    @BindView(R.id.eventDescription)
    EditText mDescription;

    @OnClick(R.id.createEvent)
    void onCreateEventClick(View v) {
        Event event = new Event(mDescription.getText().toString(), startDate, endDate, "Craig", EventType.Public, SubCategory.Soccer, _address);
        mPresenter.onCreateEventClick(event);
    }

    private Date startDate = null;
    private Date endDate = null;
    private PlaceAutocompleteFragment mAutocompleteFragment;
    private Address _address;
    private Place _eventPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(NewEventActivity.this);
        setUp();

    }

    @Override
    protected void setUp() {
        rxLocation.setDefaultTimeout(15, TimeUnit.SECONDS);

        mAutocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        mAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                _eventPlace = place;
                //TODO: Refactor if they ever fix android geocoder BS

                Thread t1 = new Thread(new Runnable() {
                    public void run()
                    {
                        try {
                            Geocoder mGeoCoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                            List<com.doctoror.geocoder.Address> a = null;
                            a = mGeoCoder.getFromLocation(_eventPlace.getLatLng().latitude, _eventPlace.getLatLng().longitude, 1, true);
                            _address = new Address(_eventPlace.getLatLng().latitude,
                                    _eventPlace.getLatLng().longitude,
                                    a.get(0).getStreetAddress(),
                                    a.get(0).getLocality(),
                                    a.get(0).getAdministrativeAreaLevel1(),
                                    a.get(0).getPostalCode());
                        } catch (GeocoderException e) {
                            e.printStackTrace();
                        }
                    }});

                t1.start();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        mAutocompleteFragment.setHint("Enter location of event...");
        PopulateCategoryDropDown();
    }

    //TODO: READ FROM SERVICE
    protected void PopulateCategoryDropDown() {
        Spinner spinner = (Spinner) findViewById(R.id.category);
        String[] subCategories = Utils.getNames(SubCategory.class);

        ArrayAdapter<SubCategory> adapter = new ArrayAdapter<SubCategory>(this, android.R.layout.simple_spinner_item, SubCategory.values());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    @Override
    public void onDatePicked(int layoutId, Date date) {
        if (layoutId == R.id.start_date_picker) {
            startDate = date;
        } else {
            endDate = date;
        }
    }

    @Override
    public void onCreatedEvent(ResponseBody responseBody) {
        Log.d("SUCCESS", "SUCCESS");
    }
}

