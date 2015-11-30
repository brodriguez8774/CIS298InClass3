package edu.kvcc.cis298.inclass3.inclass3;

import java.util.Date;
import java.util.UUID;

/**
 * Class created for each single instance of a crime.
 * Created by brodriguez8774 on 11/10/2015.
 */
public class Crime {

    //region Variables

    private UUID mID;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    //endregion



    // region Constructor

    /**
     * Default constructor.
     */
    public Crime() {
        // Generate unique identifier.
        this(UUID.randomUUID());
    }

    /**
     * Single parameter constructor.
     * @param id Crime's UUID.
     */
    public Crime(UUID id) {
        mID = id;
        mDate = new Date();

    }

    //endregion



    // region Properties

    public UUID getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    //endregion
}
