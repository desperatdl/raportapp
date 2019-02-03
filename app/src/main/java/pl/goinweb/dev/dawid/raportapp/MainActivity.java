package pl.goinweb.dev.dawid.raportapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    BackgroundWorker bw = new BackgroundWorker();
    protected static Button next_button, exit_button, car_button, driver_button, history_button;
    public String[] choosenDriver, choosenCar;
    String car_list_url = "http://dawid.dev.goinweb.pl/dawid/raport/androidapi/car.html";
    String driver_list_url = "http://dawid.dev.goinweb.pl/dawid/raport/androidapi/driver.html";
//                Toast.makeText(MainActivity.this, choosenDriver, Toast.LENGTH_LONG).show();

    public String lista_kierowcow;
    public String lista_aut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClickButtonListener();
        bw.execute();

    }


    public String[] pobierzListeAut()
    {
        lista_aut = bw.resultCar;
        String[] list = null;
        // parse json

        try {
            JSONArray json = new JSONArray(lista_aut);

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

    public String[] pobierzListeKierowcow()
    {
        lista_kierowcow = bw.resultDriver;
        String[] list = null;
        // parse json

        try {
            JSONArray json = new JSONArray(lista_kierowcow);

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

    public void wybierzKierowce(final CharSequence[] data)
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

    public void wybierzAuto(final CharSequence[] data)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wybierz auto");
        builder.setItems(data, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                String string = (String)(data[item]);
                String[] parts = string.split("\\.");
                choosenCar = parts;
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

                wybierzKierowce(pobierzListeKierowcow());
            }
        });

        car_button = findViewById(R.id.car_button);
        car_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wybierzAuto(pobierzListeAut());
            }
        });
    }

}
