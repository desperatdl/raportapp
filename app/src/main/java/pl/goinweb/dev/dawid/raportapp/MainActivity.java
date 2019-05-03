package pl.goinweb.dev.dawid.raportapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    BackgroundWorker bw = new BackgroundWorker();

    protected static Button next_button, exit_button, car_button, driver_button, history_button;
    protected static String[] choosenDriver;
    protected static String[] choosenCar;
    protected static String version;
//    String car_list_url = "http://dawid.dev.goinweb.pl/dawid/raport/androidapi/car.html";
//    String driver_list_url = "http://dawid.dev.goinweb.pl/dawid/raport/androidapi/driver.html";
//    String version_url = "http://dawid.dev.goinweb.pl/dawid/raport/androidapi/version.html";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClickButtonListener();
        bw.execute();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        getVersion();
        Toast.makeText(this, "onStart()", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart()", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume()", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "onPause()", Toast.LENGTH_LONG).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "onStop()", Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
    public void getVersion() {
        String car_list = bw.resultVersion;

        try {
            // reprezentacja obiektu JSON w Javie
            JSONObject json = new JSONObject(car_list);

            // pobranie pól obiektu JSON i wyświetlenie ich na ekranie
            ((TextView) findViewById(R.id.text_new_version)).setText(json.optString("version"));

        } catch (Exception e) {
            // obsłuż wyjątek
            Log.d(MainActivity.class.getSimpleName(), e.toString());
        }    }

    public String[] downloadCarList()
    {
        String car_list = bw.resultCar;
        String[] list = null;
        // parse json

        try {
            JSONArray json = new JSONArray(car_list);

            list = new String[json.length()];

            //looping through all the elements in json array
            for (int i = 0; i < json.length(); i++) {

                //getting json object from the json array
                JSONObject obj = json.getJSONObject(i);
                list[i] = obj.getString("id") + "." + obj.getString("name");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public String[] downloadDriversList()
    {
        String driver_list = bw.resultDriver;
        String[] list = null;
        // parse json

        try {
            JSONArray json = new JSONArray(driver_list);

            list = new String[json.length()];

            //looping through all the elements in json array
            for (int i = 0; i < json.length(); i++) {

                //getting json object from the json array
                JSONObject obj = json.getJSONObject(i);
                list[i] = obj.getString("id") + "." + obj.getString("name");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public void showDriverPicker(final CharSequence[] data)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wybierz kierowce");
        builder.setItems(data, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                String string = (String)(data[item]);
                String[] parts = string.split("\\.");
                choosenDriver = parts;
                driver_button.setText(parts[1]);
            }
        });
        builder.create();
        builder.show();
    }

    public void showCarPicker(final CharSequence[] data)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wybierz auto");
        builder.setItems(data, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                String string = (String)(data[item]);
                String[] parts = string.split("\\.");
                setChoosenCar(parts);
                car_button.setText(parts[1]);
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
                Intent intent = new Intent(MainActivity.this, DataScreenActivity.class);
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
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        driver_button = findViewById(R.id.driver_button);
        driver_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDriverPicker(downloadDriversList());
            }
        });

        car_button = findViewById(R.id.car_button);
        car_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showCarPicker(downloadCarList());
            }
        });
    }

    public String[] getChoosenCar() {
        return choosenCar;
    }

    public void setChoosenCar(String[] choosenCar) {
        this.choosenCar = choosenCar;
    }
}
