package edu.kvcc.cis298.inclass3.inclass3.database;

/**
 * Class to hold database structure information.
 * Created by brodriguez8774 on 11/23/2015.
 */
public class CrimeDBSchema {

    /**
     * Inner class to represent information about the CrimeTable in database.
     * Currently the only table we have, but if we had more, we'd mak additional classes beneath
     * this.
     */
    public static final class CrimeTable {

        // Table name.
        public static final String NAME = "crimes";

        // Static column names for the table.
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }

    }




}
