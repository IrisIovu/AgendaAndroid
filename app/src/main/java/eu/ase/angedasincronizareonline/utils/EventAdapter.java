package eu.ase.angedasincronizareonline.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Date;
import java.util.List;
import eu.ase.angedasincronizareonline.R;

public class EventAdapter extends ArrayAdapter<Event> {
    private Context context;
    private int resource;
    private List<Event> events;
    private LayoutInflater layoutInflater;

    public EventAdapter(@NonNull Context context, int resource,
                        @NonNull List<Event> events,
                        LayoutInflater layoutInflater) {
        super(context, resource, events);
        this.context = context;
        this.resource = resource;
        this.events = events;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);
        Event event = events.get(position);

        if (event != null) {
           addNameEvent(view, event.getNameEvent());
            addDate(view, event.getData());
            addHour(view,event.getHour());
        }
        return view;
    }

    private void addNameEvent(View view, String name) {
        TextView textView = view.findViewById(R.id.tv_Row_View_Name_Event);
        if (name != null && !name.trim().isEmpty()) {
            textView.setText(name);
        } else {
            textView.setText(R.string.meeting_adapter_no_content);
        }
    }

    private void addDate(View view, Date data){
        TextView textView = view.findViewById(R.id.tv_Row_View_Date_Event);
        if(data.toString()!=null){
            textView.setText(data.toString());
        }
        else{
            Toast.makeText(context, "a intrat pe hour null", Toast.LENGTH_SHORT).show();
            textView.setText(R.string.meeting_adapter_no_content);
        }
    }

    private void addHour(View view, String hour){
        TextView textView = view.findViewById(R.id.tv_Row_View_Hour_Event);
        if(hour!=null){
            textView.setText("Ora Evenimentului: "+hour);
        }
        else{
            Toast.makeText(context, "a intrat pe hour null", Toast.LENGTH_SHORT).show();
            textView.setText(R.string.meeting_adapter_no_content);
        }
    }




}
