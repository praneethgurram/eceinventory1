package com.praneethgurramgmail.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Scanner;
import java.net.URL;
import java.util.HashMap;

import android.net.Uri.Builder;
class Internetconnect {
    final static String InUrl = "http://vteceinventory.azurewebsites.net/api/items/";
    //final static String P_query = "Owner";

    public static URL bUrl(String InQuery) {
        try {
            URL url = new URL(InUrl + InQuery);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String ResponsefromAzure(URL url) throws IOException {
        HttpURLConnection inventoryconnection = (HttpURLConnection) url.openConnection();

        /*inventoryconnection.setRequestMethod("GET");
        int responseCode = inventoryconnection.getResponseCode();
        BufferedReader mobile = new BufferedReader(new InputStreamReader(inventoryconnection.getInputStream()));
        String incoming;
        StringBuffer response = new StringBuffer();
        while ((incoming = mobile.readLine()) != null) {
            response.append(incoming);
        }
        mobile.close();


        //inventoryconnection.getRequestMethod();
        */
        try {
            InputStream input = inventoryconnection.getInputStream();

            System.out.println(input);
            Scanner scan = new Scanner(input);
            scan.useDelimiter("\\A");
            boolean Input = scan.hasNext();
            if (Input) {
                return scan.next();
            } else {
                return null;
            }

        } finally {
            inventoryconnection.disconnect();
        }
    }
}

public class MainActivity extends AppCompatActivity {
    EditText searchboxEtext;
    TextView inventorySzresults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
