package edu.kvcc.cis298.inclass3.inclass3;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;

/**
 * Activity class for each individual crime.
 * Outdated due to use of CrimePagerActivity.
 */
public class CrimeActivity extends SingleFragmentActivity {

    // Intent Identifier String.
    public static final String EXTRA_CRIME_ID = "edu.kvcc.cis298.inclass3.inclass3.crime_id";

    // Passes in current activity as context and tells it to create a new CrimeActivity.
    // Also passes the specific CrimeID as an extra.
    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return  intent;
    }

    // It's okay for the host (activity) to know a lot about the fragment.
    // However, the fragment should know as little as possible about the activity to be flexible.
    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }

    //endregion
}
