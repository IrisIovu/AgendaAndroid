package eu.ase.angedasincronizareonline.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eu.ase.angedasincronizareonline.AddMeetingActivity;
import eu.ase.angedasincronizareonline.R;
import eu.ase.angedasincronizareonline.utils.Meeting;
import eu.ase.angedasincronizareonline.utils.MeetingAdapter;

import static android.app.Activity.RESULT_OK;
import static eu.ase.angedasincronizareonline.AddMeetingActivity.ADD_MEETING_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListviewMeetingsFragment extends Fragment {
    public static final String MEETING_KEY = "meetingsKey";
    private int selectedMeetingIndex;
    private static final int REQUEST_CODE_UPDATE_MEETING = 201;
    ListView lvMeetings;
    List<Meeting> meetings = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater
                .inflate(R.layout.fragment_listview_meetings, container,
                        false);
        initComponents(view);
        return view;
    }
    public void notifyInternal(){
        MeetingAdapter meetingAdapter =
                (MeetingAdapter) lvMeetings.getAdapter();
        meetingAdapter.notifyDataSetChanged();
    }
    private void initComponents(View view) {
        lvMeetings = view.findViewById(R.id.main_lv_meetings);
        //preiau lista de jucatori
        if (getArguments() != null) {
            meetings = getArguments().getParcelableArrayList(MEETING_KEY);
        }
        //adaugare adapter pe listview
        if (getContext() != null) {
            MeetingAdapter adapter = new MeetingAdapter(getContext(),
                    R.layout.lv_row_view, meetings, getLayoutInflater());
            lvMeetings.setAdapter(adapter);
            lvMeetings.setOnItemClickListener(lvMeetingsItemSelected());
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_UPDATE_MEETING && resultCode == RESULT_OK
                && data != null) {
           Meeting meeting = data.getParcelableExtra(ADD_MEETING_KEY);
            if (meeting != null) {
                updateMeeting(meeting);
                MeetingAdapter adapter = (MeetingAdapter) lvMeetings.getAdapter();
                adapter.notifyDataSetChanged();
            }
        }
    }
    private AdapterView.OnItemClickListener lvMeetingsItemSelected() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getContext(), AddMeetingActivity.class);
                // salvam indexul elementului bifat din ListView
                selectedMeetingIndex = position;
                intent.putExtra(ADD_MEETING_KEY, meetings.get(position));
                startActivityForResult(intent, REQUEST_CODE_UPDATE_MEETING);
            }
        };
    }
    private void updateMeeting(Meeting meeting) {
        meetings.get(selectedMeetingIndex).setDescription(meeting.getDescription());
        meetings.get(selectedMeetingIndex).setDate(meeting.getDate());
        meetings.get(selectedMeetingIndex).setNumberOfPart(meeting.getNumberOfPart());
        meetings.get(selectedMeetingIndex).setLocation(meeting.getLocation());
        meetings.get(selectedMeetingIndex).setHour(meeting.getHour());
    }
}
