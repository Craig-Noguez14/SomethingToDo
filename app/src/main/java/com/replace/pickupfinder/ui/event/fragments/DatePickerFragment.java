package com.replace.pickupfinder.ui.event.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import com.replace.pickupfinder.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private DatePickerFragment.OnDatePickedListener mCallback;
    Integer mLayoutId = null;
    Button dateButton;
    int _year;
    int _month;
    int _day;
    Date _date;

    public interface OnDatePickedListener {
        public void onDatePicked(int textId, Date date);
    }

    public DatePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (DatePickerFragment.OnDatePickedListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnDatePickedListener.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.datepicker_layout, container, false);
        mCallback = (DatePickerFragment.OnDatePickedListener)getActivity();
        mLayoutId = DatePickerFragment.this.getId();

        dateButton = (Button) view.findViewById(R.id.date_button);

        // Show a datepicker when the dateButton is clicked
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        DatePickerFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );


                dpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d("DatePicker", "Dialog was cancelled");
                    }
                });

                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");
        if(dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int month, int day) {
            Calendar now = Calendar.getInstance();
            _year = year;
            _month = month;
            _day = day;

            TimePickerDialog tpd = TimePickerDialog.newInstance(
                    new TimePickerFragment(),
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    false
            );

            tpd.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                    _date = new Date(_year, _month, _day, hourOfDay, minute, second);
                    mCallback.onDatePicked(mLayoutId, _date);
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    dateButton.setText(df.format(_date));
                    dateButton.setError(null);
                }

            });

            tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    Log.d("TimePicker", "Dialog was cancelled");
                }
            });
            tpd.show(getFragmentManager(), "Timepickerdialog");
        }

    public void setErrorText() {
        dateButton.setError(getString(R.string.error_field_required));
    }
}
