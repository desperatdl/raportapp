package pl.goinweb.dev.dawid.raportapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;


public class dataScreen extends AppCompatActivity {


    static public Button next_button, back_button, check_button, save_button, data_start_button, data_stop_button, time_start_button, time_stop_button;
    static public Time choosen_time_start, choosen_time_stop;
    static public Date choosen_date_start, choosen_date_stop
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_screen);
        OnClickButtonListener();
    }

    public void OnClickButtonListener()
    {
        next_button = findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dataScreen.this, summary.class);
                startActivity(intent);
            }
        });

        back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        time_start_button = findViewById(R.id.time_start_button);
        time_start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimeStartPicker();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

    }

    public static class TimeStartPicker extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            time_start_button.setText(hourOfDay + " : " + minute);
            choosen_time_start = Time;
        }
    }

}
