package eu.ase.angedasincronizareonline.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eu.ase.angedasincronizareonline.AddEvent;
import eu.ase.angedasincronizareonline.R;
import eu.ase.angedasincronizareonline.utils.Event;
import eu.ase.angedasincronizareonline.utils.EventAdapter;

import static android.app.Activity.RESULT_OK;
import static eu.ase.angedasincronizareonline.AddEvent.ADD_EVENT_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListEvents extends Fragment {
    public static final String EVENT_KEY = "eventKey";
    private int selectedEventIndex;
    private static final int REQUEST_CODE_UPDATE_EVENT= 201;
    ListView lvEvents;
    List<Event> events = new ArrayList<>();

    public ListEvents() {
    }

    public void notifyInternal(){
        EventAdapter eventAdapter =
                (EventAdapter) lvEvents.getAdapter();
        eventAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_list_events, container,
                        false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        lvEvents = view.findViewById(R.id.lv_Events);
        if (getArguments() != null) {
            events = getArguments().
                    getParcelableArrayList(EVENT_KEY);

        }
        if (getContext() != null) {
            EventAdapter adapter = new EventAdapter(getContext(),
                    R.layout.lv_events_row_view, events, getLayoutInflater());
            lvEvents.setAdapter(adapter);
            lvEvents.setOnItemClickListener(lvEventsItemSelected());
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_UPDATE_EVENT && resultCode == RESULT_OK
                && data != null) {
            Event event = data.getParcelableExtra(ADD_EVENT_KEY);
            if (event != null) {
                updateEvent(event);
                EventAdapter adapter = (EventAdapter) lvEvents.getAdapter();
                adapter.notifyDataSetChanged();
            }
        }
    }

    private AdapterView.OnItemClickListener lvEventsItemSelected() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getContext(), AddEvent.class);
                selectedEventIndex = position;
                intent.putExtra(ADD_EVENT_KEY, events.get(position));
                startActivityForResult(intent, REQUEST_CODE_UPDATE_EVENT);
            }
        };
    }
    private void updateEvent(Event event) {
        events.get(selectedEventIndex).setHour(event.getHour());
        events.get(selectedEventIndex).setData(event.getData());
       }
}
