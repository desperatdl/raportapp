package pl.goinweb.dev.dawid.raportapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class dataScreen extends AppCompatActivity {


    protected Button next_button, back_button, check_button, save_button, data_start_button, data_stop_button, time_start_button, time_stop_button;

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


    }


}
