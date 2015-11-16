package edu.kvcc.cis298.inclass3.inclass3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Generic Abstract class to create a New Fragment.
 * Created by brodriguez8774 on 11/16/2015.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {

    //region Variables



    //endregion



    //region Override Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflates view using Activity Fragment.
        setContentView(R.layout.activity_fragment);

        // Initialize fragment manager.
        // Gets a variable which represents the support version of the fragment manager.
        FragmentManager fm = getSupportFragmentManager();
        // Use the FragmentManager to get the fragment currently in xml UI file. On app start, this will be null.
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        // If null, create a new CrimeFragment and commit it to the "fragment_container" id, defined in the xml file.
        // AKA, if fragment manager does not yet have a fragment, initialize one.
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    //endregion



    //region Methods

    // Abstract method to create fragment.
    protected abstract Fragment createFragment();

    //endregion

}
