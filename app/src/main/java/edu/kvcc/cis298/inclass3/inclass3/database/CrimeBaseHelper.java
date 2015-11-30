package edu.kvcc.cis298.inclass3.inclass3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.kvcc.cis298.inclass3.inclass3.database.CrimeDBSchema.CrimeTable;

/**
 * Created by brodriguez8774 on 11/23/2015.
 */
public class CrimeBaseHelper extends SQLiteOpenHelper {

    //region Variables

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    //endregion



    //region Constructor

    public CrimeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    //endregion



    //region Override Methods

    // Method called on database creation.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute a RAW sqlite statement to create the table that will store data for our
        // application. Note: This method isn't very safe, from a security standpoint.
        db.execSQL("create table" + CrimeTable.NAME + "(" +
            " _id integer primary key autoincrement, " +
            CrimeTable.Cols.UUID + "," +
            CrimeTable.Cols.TITLE + "," +
            CrimeTable.Cols.DATE + "," +
            CrimeTable.Cols.SOLVED +
            ")"
        );
    }

    // Method called if database already exists but not on newest version. Determined by above
    // Variables. Will migrate database to most current and correct version for app.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // We aren't doing anything here right now.
    }

    //endregion

}
