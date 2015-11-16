package edu.kvcc.cis298.inclass3.inclass3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Fragment class to hold and display the list of crimes.
 * Created by brodriguez8774 on 11/16/2015.
 */
public class CrimeListFragment extends Fragment {

    //region Variables

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    //endregion



    //region Override Methods

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // "Inflates" the view into java-readible code from xml.
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        // Get a reference to the recycler view in xml layout file.
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        // All RecyclerViews need some kind of layout manager to function. Failure to give one will
        // result in crashing.
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Does the work of getting data from CrimeLab and setting it up with adapter.
        updateUI();

        return view;
    }


    //endregion


    //region Methods


    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Variables for CrimeHolder.
        private Crime mCrime;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        // Constructor for CrimeHolder.
        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            // Assigns id to variables.
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        // Binds the individual CrimeHolder views (created above) to the layout.
        public void bindCrime(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

        @Override
        public void onClick(View v) {
            // Temporary onclick toast.
            Toast.makeText(getActivity(), mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    // Adapts the information from RecyclerView to display on screen?
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        // List to hold the "data" of crimes.
        private List<Crime> mCrimes;

        // Assigns crime data to calss level variable.
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        //region Override Methods for CrimeAdapter

        // Called by RecyclerView when it needs a new View to display an item.
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //Creates a new View to put into the ViewHolder.
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            // Inflates view of single list item to xml CrimeList layout.
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        // Binds a ViewHolder's View to the "model object."
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            // Finds the position (index) on the screen in which to bind to.
            Crime crime = mCrimes.get(position);
            // Sends crime info to bindCrime method in CrimeHolder class.
            holder.bindCrime(crime);
        }

        // Gets number of total crimes in mCrimes list.
        @Override
        public int getItemCount() {
            return mCrimes.size();
        }


        //endregion

    }

    // Does the work of getting data from CrimeLab and setting it up with adapter.
    private void updateUI() {
        // Gets collection of data from CrimeLab singleton. The method requires a context so we
        // pass it the hosting activity of this fragment.
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        // Gets the actual list of crimes from CrimeLab class.
        List<Crime> crimes = crimeLab.getCrimes();

        // Creates a new adapter using the list of crimes.
        mAdapter = new CrimeAdapter(crimes);
        // Send the newly created adapter to the recycler to use.
        mCrimeRecyclerView.setAdapter(mAdapter);
    }


    //endregion

}
