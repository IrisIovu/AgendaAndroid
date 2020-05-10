package eu.ase.angedasincronizareonline.fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.ase.angedasincronizareonline.R;
import eu.ase.angedasincronizareonline.network.HttpManager;
import eu.ase.angedasincronizareonline.network.HttpResponse;
import eu.ase.angedasincronizareonline.network.Item;
import eu.ase.angedasincronizareonline.network.JsonParser;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransferMarketFragment extends Fragment {

    private static final String URL = "https://api.myjson.com/bins/16ro6u";
    private Button btnRoom1;
    private Button btnRoom2;
    private Button btnRoom3;
    private Button btnRoom4;
    private ListView lvResponse;
    private List<Item> selectedResponse = new ArrayList<>();
    private HttpResponse httpResponse;

    public TransferMarketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =
                inflater
                        .inflate(R.layout.fragment_transfer_market, container, false);
        initComponents(view);
        new HttpManager() {
            @Override
            protected void onPostExecute(String s) {
                httpResponse = JsonParser.parseJson(s);
                if (httpResponse != null) {
                    Toast.makeText(getContext(),
                            httpResponse.toString(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }.execute(URL);
        return view;
    }
    private void initComponents(View view) {
        btnRoom1 = view.findViewById(R.id
                .transfer_market_btn_Room1);
        btnRoom2 = view.findViewById(R.id
                .transfer_market_btn_Room2);
        btnRoom3 = view.findViewById(R.id
                .transfer_market_btn_Room3);
        btnRoom4 = view.findViewById(R.id
                .transfer_market_btn_Room4);
        lvResponse = view.findViewById(R.id
                .transfer_market_lv_response);
        //atasare adapter
        ArrayAdapter<Item> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                selectedResponse);
        lvResponse.setAdapter(adapter);
        unSelectedButtons();
        //btn goalkeeper event
        btnRoom1.setOnClickListener(new View
                .OnClickListener() {
            @Override
            public void onClick(View view) {
                if (httpResponse != null && httpResponse
                        .getRoom1() != null) {
                    //adaugare pe listview
                    unSelectedButtons();
                    selectButtonColor(btnRoom1);
                    selectResponse(httpResponse.
                            getRoom1());
                }
            }
        });

        //btn center
        btnRoom2.setOnClickListener(new View
                .OnClickListener() {
            @Override
            public void onClick(View view) {
                if (httpResponse != null &&
                        httpResponse.getRoom2() != null) {
                    unSelectedButtons();
                    selectButtonColor(btnRoom2);
                    selectResponse(httpResponse.getRoom2());
                }
            }
        });

        //btn inter
        btnRoom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (httpResponse != null && httpResponse
                        .getRoom3() != null) {
                    unSelectedButtons();
                    selectButtonColor(btnRoom3);
                    selectResponse(httpResponse.getRoom3());
                }
            }
        });

        //btn winger
        btnRoom4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (httpResponse != null && httpResponse
                        .getRoom4() != null) {
                    unSelectedButtons();
                    selectButtonColor(btnRoom4);
                    selectResponse(httpResponse.getRoom4());
                }
            }
        });
    }

    private void selectResponse(List<Item> list) {
        selectedResponse.clear();
        selectedResponse.addAll(list);
        ArrayAdapter<Item> adapter = (ArrayAdapter<Item>)
                lvResponse.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void unSelectedButtons() {
        btnRoom1.setBackgroundColor(Color.GRAY);
        btnRoom2.setBackgroundColor(Color.GRAY);
        btnRoom3.setBackgroundColor(Color.GRAY);
        btnRoom4.setBackgroundColor(Color.GRAY);
    }

    private void selectButtonColor(Button button) {
        button.setBackgroundColor(Color.GREEN);
    }
}
