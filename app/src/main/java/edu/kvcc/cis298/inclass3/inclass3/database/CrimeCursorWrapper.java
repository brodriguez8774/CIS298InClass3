package edu.kvcc.cis298.inclass3.inclass3.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import edu.kvcc.cis298.inclass3.inclass3.Crime;
import edu.kvcc.cis298.inclass3.inclass3.database.CrimeDBSchema.CrimeTable;

/**
 * Created by brodriguez8774 on 11/30/2015.
 */
public class CrimeCursorWrapper extends CursorWrapper {

    //region Constructor.

    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    //endregion

    // Will get the data for a crime in raw form. Will need converting to standard form.
    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);

        return crime;
    }

}
