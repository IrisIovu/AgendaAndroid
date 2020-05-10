package eu.ase.angedasincronizareonline.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import eu.ase.angedasincronizareonline.AddMeetingActivity;
import eu.ase.angedasincronizareonline.R;

public class MeetingAdapter extends ArrayAdapter<Meeting> {
    private Context context;
    private int resource;
    private List<Meeting> meetings;
    private LayoutInflater layoutInflater;

    public MeetingAdapter(@NonNull Context context,
                         int resource,
                         List<Meeting> meetings,
                           LayoutInflater layoutInflater) {
        super(context, resource,meetings);
        this.context = context;
        this.resource = resource;
        this.meetings = meetings;
        this.layoutInflater=layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);
        Meeting meeting = meetings.get(position);
        if (meeting != null) {
            addDescrription(view, meeting.getDescription());
            addDate(view, meeting.getDate());
            addNumberpart(view, meeting.getNumberOfPart());
            addLocation(view, meeting.getLocation());
            addHour(view, meeting.getHour());
        }
        return view;
    }

    private void addDescrription(View view, String name) {
        TextView textView = view.findViewById(R.id.lv_meeting_description);
        if (name != null && !name.trim().isEmpty()) {
            textView.setText(name);
        } else {
            textView.setText(R.string.meeting_adapter_no_content);
        }
    }
    private void addDate(View view, Date date) {
        TextView textView = view.findViewById(R.id.lv_meeting_date);
        if (date != null) {
            textView.setText(new SimpleDateFormat(AddMeetingActivity.DATE_FORMAT, Locale.US)
                    .format(date));
        } else {
            textView.setText(R.string.meeting_adapter_no_content);
        }
    }
    private void addNumberpart(View view, Integer number) {
        TextView textView = view.findViewById(R.id.lv_meeting_numberpart);
        if (number != null) {
            textView.setText(String.valueOf(number));
        } else {
            textView.setText(R.string.meeting_adapter_no_content);
        }
    }
    private void addLocation(View view, String location) {
        TextView textView = view.findViewById(R.id.lv_meeting_location);
        if (location != null && !location.trim().isEmpty()) {
            textView.setText(location);
        } else {
            textView.setText(R.string.meeting_adapter_no_content);
        }
    }
    private void addHour(View view, String hour) {
        TextView textView = view.findViewById(R.id.lv_meeting_hour);
        if (hour != null && !hour.trim().isEmpty()) {
            textView.setText(hour);
        } else {
            textView.setText(R.string.meeting_adapter_no_content);
        }
    }

}
