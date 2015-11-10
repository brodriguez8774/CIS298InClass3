package edu.kvcc.cis298.inclass3.inclass3;

import java.util.UUID;

/**
 * Created by brodriguez8774 on 11/10/2015.
 */
public class Crime {

    //region Variables

    private UUID mID;
    private String mTitle;

    //endregion



    // region Constructor

    public Crime() {
        // Generate unique identifier.
        mID = UUID.randomUUID();
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

    //endregion
}
