package eu.ase.angedasincronizareonline.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.ase.angedasincronizareonline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewByDateFragment extends Fragment {


    public ViewByDateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_by_date, container, false);
    }

}
