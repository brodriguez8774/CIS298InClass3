package edu.kvcc.cis298.inclass3.inclass3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.kvcc.cis298.inclass3.inclass3.database.CrimeBaseHelper;
import edu.kvcc.cis298.inclass3.inclass3.database.CrimeCursorWrapper;
import edu.kvcc.cis298.inclass3.inclass3.database.CrimeDBSchema;
import edu.kvcc.cis298.inclass3.inclass3.database.CrimeDBSchema.CrimeTable;

/**
 * Singleton class which holds the list of crimes.
 * Created by brodriguez8774 on 11/10/2015.
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

    /*
    private List<Crime> mCrimes;
    */

    private Context mContext;
    private SQLiteDatabase mDatabase;

    // endregion



    //region Constructor

    private CrimeLab(Context context) {

        // Assign passed in context to class level.
        mContext = context.getApplicationContext();
        // Use the context and CrimeBaseHelper to get a writeable database.
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();

        /*
        // Populate with 100 dummy crimes for testing.
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + (i + 1));
            crime.setSolved(i % 2 == 0); // Every other one.
            mCrimes.add(crime);
        }*/
    }

    //endregion



    //region Properties

    public List<Crime> getCrimes() {

        // Create a list to hold crimes.
        List<Crime> crimes = new ArrayList<>();

        // Create a new CrimeCursorWrapper. Pass in null for WhereClause and WhereArgs because
        // we don't want to limit our results right now.
        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            // Move to first entry in data result.
            cursor.moveToFirst();
            // While there is another entry,
            while (!cursor.isAfterLast()) {
                // Call getCrime method to do work of taking data and turning it into a new crime.
                crimes.add(cursor.getCrime());
                // Move to next row in result set.
                cursor.moveToFirst();
            }
        } finally {
            // Close the cursor.
            cursor.close();
        }

        // Return crimes list.
        return crimes;
    }

    //endregion



    //region Methods

    // Method to add a new crime to Database.
    public void addCrime(Crime c) {
        // Get the values to send to database.
        ContentValues values = getContentValues(c);
        //Insert into database using predefined method in parent class.
        mDatabase.insert(CrimeTable.NAME, null, values);
    }

    public void updateCrime(Crime crime) {
        // Get UUID as string. Used in WHERE clause of SQL to find row to update.
        String uuidString = crime.getID().toString();
        // Create content that will be used to update model.
        ContentValues values = getContentValues(crime);

        // Update a specific crime with the values from ContentValues.
        mDatabase.update(CrimeTable.NAME, values, CrimeTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    public Crime getCrime(UUID id) {

        // Create a new CrimeCursorWrapper. Pass a where clause and string array (WhereArgs). Should
        // narrow query down to a single entry due to use of UUID.
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            // If did not get a result.
            if (cursor.getCount() == 0) {
                return null;
            }

            // Move to first entry.
            cursor.moveToFirst();
            // Return the first result. Theoretically should only be one anyways, if any where found.
            return cursor.getCrime();
        } finally {
            // Close cursor.
            cursor.close();
        }
    }

    // Static method to take in a crime and create a contentValue object that can be inserted into
    // the database. The contentValues class operates as a hash table.
    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getID().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        // Change date object into a timestamp and store.
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        // Ternary operator here. Basically a shortened if/then/else statement.
        // If true, then 1, else, 0.
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);

        return values;
    }

    // Method to query the crimes table. It takes in a WhereClause and WhereArgs for the query.
    // It will return a result set that we can look through to see returned crimes.
    private CrimeCursorWrapper queryCrimes (String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
            CrimeTable.NAME,    // Table name.
                null,           // Selected Column(s). Null means all.
                whereClause,    // Where.
                whereArgs,      // Args for where.
                null,           // GroupBy.
                null,           // Having.
                null            // OrderBy.
        );
        return new CrimeCursorWrapper(cursor);
    }


    //endregion
}
