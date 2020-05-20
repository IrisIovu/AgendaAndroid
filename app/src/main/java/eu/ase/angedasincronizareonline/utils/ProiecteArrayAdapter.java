package eu.ase.angedasincronizareonline.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import eu.ase.angedasincronizareonline.R;

public class ProiecteArrayAdapter extends ArrayAdapter<Proiect> {
private Activity context;
private List<Proiect> proiecte;

    public ProiecteArrayAdapter(@NonNull Activity context, List<Proiect> proiecte) {

        super(context, R.layout.proiect_lv_layout, proiecte);
        this.context=context;
        this.proiecte=proiecte;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.proiect_lv_layout,null,true);
        TextView tv_name = listViewItem.findViewById(R.id.tv_numeProiect);
        TextView tv_procent= listViewItem.findViewById(R.id.tv_procentSucces);

        Proiect proiect = proiecte.get(position);
        tv_name.setText(proiect.getNumeProiect());
       tv_procent.setText(String.valueOf(proiect.getProcentSucces()));

        return listViewItem;


    }


}
