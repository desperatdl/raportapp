package pl.goinweb.dev.dawid.raportapp;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BackgroundWorker extends AsyncTask<String, Integer, Long> {

    String line = null;
    String result = null;
    String resultCar = null;
    String resultDriver = null;

    String  driver_list_url = "http://dawid.dev.goinweb.pl/dawid/raport/androidapi/driver.html";
    String  car_list_url = "http://dawid.dev.goinweb.pl/dawid/raport/androidapi/car.html";

    @Override
    protected Long doInBackground(String... params) {
//        getUrl();
//        String uri = params[0];
        try {
            URL url = new URL(driver_list_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream is = httpURLConnection.getInputStream();
            resultDriver = readStream(is);
            is.close();
            httpURLConnection.disconnect();
            URL url2 = new URL(car_list_url);
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) url2.openConnection();
            InputStream is2 = httpURLConnection2.getInputStream();
            resultCar = readStream(is2);
            is2.close();
            httpURLConnection2.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    return null;
    }

    private String readStream(InputStream is) {
        // read content to string

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null)
            {
                sb.append(line+"\n");
            }
            br.close();
            result = sb.toString();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);

    }
}
