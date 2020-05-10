package eu.ase.angedasincronizareonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import eu.ase.angedasincronizareonline.utils.Meeting;

public class AddMeetingActivity extends AppCompatActivity {
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String ADD_MEETING_KEY = "addMeetingKey";
    EditText etDate;
    EditText etNumberOfPart;
    EditText etDescription;
    EditText etTime;
    Spinner spnLocations;
    Button btnSave;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        initComponents();
        intent = getIntent();
        if (intent.hasExtra(ADD_MEETING_KEY)) {
            Meeting meeting = intent.getParcelableExtra(ADD_MEETING_KEY);
            updateUI(meeting);
        }
    }
    private void initComponents() {

        etDate = findViewById(R.id.add_meeting_date);
        etNumberOfPart = findViewById(R.id.add_meeting_numberPers);
        etDescription = findViewById(R.id.add_meeting_description);
        etTime = findViewById(R.id.add_meeting_hour);
        spnLocations=findViewById(R.id.add_meeting_spn_meeting);
        btnSave=findViewById(R.id.add_meeting_btn_save);
        ArrayAdapter<CharSequence> positionsAdapter =
                ArrayAdapter.createFromResource(getApplicationContext(), R.array.add_meeting_positions,
                        R.layout.support_simple_spinner_dropdown_item);
        spnLocations.setAdapter(positionsAdapter);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validare
                if (validate()) {
                    //construire obiect
                    Meeting meeting = createMeetinfFromView();
                    Toast.makeText(getApplicationContext(),
                            meeting.toString(),
                            Toast.LENGTH_LONG)
                            .show();
                    //transfer parametru
                    intent.putExtra(ADD_MEETING_KEY, meeting);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
    private void updateUI(Meeting meeting) {
        etDescription.setText(meeting.getDescription());
        if (meeting.getDate() != null) { // daca valoarea este null atunci nu ar trebui sa convertim de la date la string.
            etDate.setText(new SimpleDateFormat(DATE_FORMAT, Locale.US).format(meeting.getDate()));
        }
        if (meeting.getNumberOfPart() != null) {

            etNumberOfPart.setText(String.valueOf(meeting.getNumberOfPart()));
        }
        if (meeting.getLocation() != null) {
            addLocation(meeting);
        }
        if (meeting.getHour() != null) {
            etTime.setText(meeting.getHour());
        }
    }
    private void addLocation(Meeting meeting) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) spnLocations.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).equals(meeting.getLocation())) {
                spnLocations.setSelection(i);
                break;
            }
        }
    }

    private Meeting createMeetinfFromView() {
        String description = etDescription.getText().toString();
        Date date = null;
        try {
            date = new SimpleDateFormat(DATE_FORMAT, Locale.US)
                    .parse(etDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer number = Integer.parseInt(etNumberOfPart.getText()
                .toString());
        String location = spnLocations.getSelectedItem().toString();

        String hour = etTime.getText().toString();

        return new Meeting(description, hour, number, date, location);
    }

    private boolean validate() {
        if (etDescription.getText() == null
                || etDescription.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_meeting_description_error_message,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (etNumberOfPart.getText() == null
                || etNumberOfPart.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_meeting_number_error_message,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (etDate.getText() == null
                || etDate.getText().toString()
                .trim().isEmpty()
                || !validateDate(etDate.
                getText().toString())) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_meeting_date_error_message,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        return true;
    }

    private boolean validateDate(String strDate) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(DATE_FORMAT, Locale.US);
        try {
            simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
