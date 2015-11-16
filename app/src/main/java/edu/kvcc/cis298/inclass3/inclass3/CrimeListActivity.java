package edu.kvcc.cis298.inclass3.inclass3;

import android.support.v4.app.Fragment;

/**
 * Created by brodriguez8774 on 11/16/2015.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    //region Override Methods

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    //endregion



}
