package pl.goinweb.dev.dawid.raportapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.lang.UScript;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private ListView car_list, driver_list ;
    protected static Button next_button, exit_button, car_button, driver_button, history_button;
    public String choosenDriver, choosenCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClickButtonListener();

    }

    public void wybierzKierowce()
    {
        final CharSequence[] kierowcy = {"Darek", "Dawid", "Piotr"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wybierz kierowce");
        builder.setItems(kierowcy, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                driver_button.setText(kierowcy[item]);
                choosenDriver = (String)(kierowcy[item]);
//                Toast.makeText(MainActivity.this, choosenDriver, Toast.LENGTH_LONG).show();
            }
        });
        builder.create();
        builder.show();
    }

    public void wybierzAuto()
    {
        final CharSequence[] auta = {"Merc", "Opel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wybierz auto");
        builder.setItems(auta, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                car_button.setText(auta[which]);
                choosenCar = (String)(auta[which]);
            }
        });
        builder.create();
        builder.show();
    }
    public void onClickButtonListener()
    {
        next_button = findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, dataScreen.class);
                startActivity(intent);
            }
        });

        exit_button = findViewById(R.id.exit_button);
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        history_button = findViewById(R.id.history_button);
        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, history.class);
                startActivity(intent);
            }
        });

        driver_button = findViewById(R.id.driver_button);
        driver_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wybierzKierowce();
            }
        });

        car_button = findViewById(R.id.car_button);
        car_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wybierzAuto();
            }
        });
    }

}
