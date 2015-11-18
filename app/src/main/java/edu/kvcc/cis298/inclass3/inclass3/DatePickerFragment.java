package edu.kvcc.cis298.inclass3.inclass3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Waffy on 11/18/2015.
 */
public class DatePickerFragment extends DialogFragment {

    //region Variables

    private DatePicker mDatePicker;

    public static final String EXTRA_DATE = "edu.kvcc.cis298.inclass3.inclass3.date";

    //endregion



    //region Intent to Summon DatePickerFragment

    private static final String ARG_DATE = "date";

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //endregion


    //region Override Methods

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Date Picker cannot give the date in the form of ints. Instead, you must pass it to
        // a Calandar object which can do this for us.
        Date date = (Date) getArguments().getSerializable(ARG_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Inflates xml view into java code.
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

        // Find layout id of date picker.
        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_picker);
        // Pass in newly created Date ints (from calendar) to pass to date picker.
        mDatePicker.init(year, month, day, null);

        // Returns a new AlertDialog to create.
        return new AlertDialog.Builder(getActivity())
                // Sets view for alert dialog.
                .setView(v)
                // Sets the alert title to specified string.
                .setTitle(R.string.date_picker_title)
                // Positive button accepts the dialog's primary action, currently with an
                // onClickListener of null.
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int year = mDatePicker.getYear();
                                int month = mDatePicker.getMonth();
                                int day = mDatePicker.getDayOfMonth();
                                Date date = new GregorianCalendar(year, month, day).getTime();
                                sendResult(CrimeActivity.RESULT_OK, date);
                            }
                        })
                // Create dialogue.
                .create();
    }

    //endregion



    //region Private Methods

    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

    //endregion

}
