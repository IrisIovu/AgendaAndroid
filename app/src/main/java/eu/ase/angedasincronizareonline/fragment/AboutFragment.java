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
public class AboutFragment extends Fragment {


    public AboutFragment() {
        System.out.println("Sa apelat constructorul aboutfragment");
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("Sa apelat oncreateview aboutfragment");
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

}
