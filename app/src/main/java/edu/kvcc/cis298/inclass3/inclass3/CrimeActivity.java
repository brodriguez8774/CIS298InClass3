package edu.kvcc.cis298.inclass3.inclass3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CrimeActivity extends FragmentActivity {

    //region Override Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        // Gets a variable which represents the support version of the fragment manager.
        FragmentManager fm = getSupportFragmentManager();
        // Use the FragmentManager to get the fragment currently in xml UI file. On app start, this will be null.
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        // If null, create a new CrimeFragment and commit it to the "fragment_container" id, defined in the xml file.
        if (fragment == null) {
            fragment = new CrimeFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    //endregion
}
