package edu.kvcc.cis298.inclass3.inclass3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Fragment class to hold and display each Crime.java.
 * Created by brodriguez8774 on 11/10/2015.
 */
public class CrimeFragment extends Fragment {

    //region Variables

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    //endregion



    //region Override Methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create new instance of a crime.
        mCrime = new Crime();
    }

    // Responsible for inflating view and getting content on screen (for fragments).
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Intentionally left blank.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Intentionally left blank.
            }
        });

        // Associates variable with xml file.
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        // Sets the date button to current date?
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);

        // Associates variable with xml file.
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        // Listens for change in checkbox check.
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // Set the crime's solved property.
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }

    //endregion
}
