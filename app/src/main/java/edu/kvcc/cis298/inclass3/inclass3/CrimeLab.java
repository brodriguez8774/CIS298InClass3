package edu.kvcc.cis298.inclass3.inclass3;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by brodriguez8774 on 11/10/2015.
 * Singleton class which holds the list of crimes.
 */
public class CrimeLab {

    //region Singleton Setup

    // Private static variable to hold instance of this class.
    private static CrimeLab sCrimeLab;

    // Public static method to both create the single instance of this class and return created instance.
    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    //endregion



    //region Variables

    private List<Crime> mCrimes;

    // endregion



    //region Constructor

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();

        // Populate with 100 dummy crimes for testing.
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + (i + 1));
            crime.setSolved(i % 2 == 0); // Every other one.
            mCrimes.add(crime);
        }
    }

    //endregion



    //region Properties

    public List<Crime> getCrimes(){
        return  mCrimes;
    }

    //endregion



    //region Methods

    public Crime getCrime(UUID id) {
        for (Crime crime:mCrimes) {
            if (crime.getID().equals(id)) {
                return crime;
            }
        }
        return null;
    }

    //endregion
}
