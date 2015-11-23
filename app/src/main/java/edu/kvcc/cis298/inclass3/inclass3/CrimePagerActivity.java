package edu.kvcc.cis298.inclass3.inclass3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Activity Class for each individual Crime.
 * Replaces CrimeActivity and adds Page swiping functionality.
 * Created by Waffy on 11/18/2015.
 */
public class CrimePagerActivity extends AppCompatActivity {

    //region Variables

    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    //endregion



    //region Intent to Summon CrimePagerActivity

    private static final String EXTRA_CRIME_ID = "edu.kvcc.cis298.inclass3.inclass3.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        // Makes new intent.
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        // Adds in CrimeId using key above.
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    //endregion



    //region Override Methods

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sets the content to use the crime pager layout.
        setContentView(R.layout.activity_crime_pager);

        // Because UUID is not of a "simple type" such as int or double, it must be of a type
        // which implements SerializableExtra. We do no need to know what SerializableExtra is,
        // just that we can call it to store and retrieve the UUID of the crime.
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        // Gets id of View Pager xml layout.
        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);



        // Gets list of crimes from CrimeLab.
        mCrimes = CrimeLab.get(this).getCrimes();
        // Creates new fragment manager and pager adapter.
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Passes in just created fragment manager to start adapter.
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                // Fetches the specific crime instance in the given position and uses the ID
                // to create a proper display?
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getID());
            }

            @Override
            public int getCount() {
                // Gets size of Crime List.
                return mCrimes.size();
            }
        });

        // Goes through list of Crimes and finds one with matching ID. Sets that one to
        // be the current pager index.
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getID().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }


    //endregion

}
