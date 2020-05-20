package eu.ase.angedasincronizareonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Calendar;

import eu.ase.angedasincronizareonline.utils.Event;

public class AddEvent extends AppCompatActivity {
    public static final String ADD_EVENT_KEY = "addEventKey";
    EditText etHour;
    EditText etName;
    SeekBar seekBar;
    Button btnPasUrmator;
    DatePicker datepicker_date;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        initComponents();
        intent = getIntent();

        if (intent.hasExtra(ADD_EVENT_KEY)) {
            Toast.makeText(this, "a intrat in id intent has extra din add event", Toast.LENGTH_SHORT).show();
            Event event = intent.getParcelableExtra(ADD_EVENT_KEY);
            updateUI(event);
        }

    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    private void initComponents() {
        etHour = findViewById(R.id.AddEvent_ora);
        etName = findViewById(R.id.AddEvent_nume);
        seekBar = findViewById(R.id.sb_importantaEvent);
        datepicker_date=findViewById(R.id.Datepicker);
        btnPasUrmator=findViewById(R.id.AdaugaEvent_btn);
        btnPasUrmator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    Event event = createEventFromView();
                   intent.putExtra(ADD_EVENT_KEY, event);
                    setResult(RESULT_OK, intent);
                   killActivity();
                }
            }
        });
    }
    private void killActivity() {
        finish();
    }
    private boolean validate(){
        if (etName.getText() == null
                || etName.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_meeting_description_error_message,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }



        if (etHour.getText() == null
                || etHour.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_meeting_number_error_message,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }

    private Event createEventFromView(){
        return new Event(etName.getText().toString(), getDateFromDatePicker(datepicker_date), etHour.getText().toString(), getNumber(seekBar));

    }

    private void updateUI(Event event) {
        etName.setText(event.getNameEvent());
        if (event.getHour() != null) {
            etHour.setText(event.getHour());
        }
    }


    public int getNumber(SeekBar seek)
    {
        int seekValue = seek.getProgress();
        return seekValue;
    }


}

