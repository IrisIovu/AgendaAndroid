package eu.ase.angedasincronizareonline.fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
public class MeetingInfoJsonFragment extends Fragment {

    private static final String URL = "https://my-json-server.typicode.com/IrisIovu/MyJson/db";
    private Button btn_AlegeCamera;
    private ListView lvResponse;
    private List<Item> selectedResponse = new ArrayList<>();
    private HttpResponse httpResponse;
    private EditText ed_Alegecamera;

    public MeetingInfoJsonFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater
                        .inflate(R.layout.fragment_transfer_market, container, false);
        initComponents(view);
        new HttpManager() {
            @Override
            protected void onPostExecute(String s) {
                httpResponse = JsonParser.parseJson(s);
                if (httpResponse != null) {

                }
            }
        }.execute(URL);
        return view;
    }
    private void initComponents(View view) {
        ed_Alegecamera = view.findViewById(R.id.Alegecamera);
        btn_AlegeCamera = view.findViewById(R.id.btn_AlegeCamera);
        lvResponse = view.findViewById(R.id
                .transfer_market_lv_response);
        ArrayAdapter<Item> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                selectedResponse);
        lvResponse.setAdapter(adapter);
        btn_AlegeCamera.setOnClickListener(new View
                .OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(Integer.parseInt(ed_Alegecamera.getText().toString())) {
                    case 1:
                        if (httpResponse != null && httpResponse
                                .getRoom1() != null) {
                        selectResponse(httpResponse.
                                getRoom1());
                    }
                        break;
                    case 2:
                        if (httpResponse != null && httpResponse
                                .getRoom2() != null) {
                            selectResponse(httpResponse.
                                    getRoom2());
                        }
                        break;
                    case 3:
                        if (httpResponse != null && httpResponse
                                .getRoom3() != null) {
                            selectResponse(httpResponse.
                                    getRoom3());
                        }
                        break;
                    case 4:
                        if (httpResponse != null && httpResponse
                                .getRoom4() != null) {
                        selectResponse(httpResponse.
                                getRoom4());
                    }
                        break;
                    default:
                        Toast.makeText(getContext(), "Ati introdus o camera incorecta", Toast.LENGTH_SHORT).show();
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


}
