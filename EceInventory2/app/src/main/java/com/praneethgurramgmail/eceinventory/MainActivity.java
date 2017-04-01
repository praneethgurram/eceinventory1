package com.praneethgurramgmail.eceinventory;

import android.content.Intent;
import android.os.AsyncTask;

import android.support.annotation.NonNull;
import android.support.v7.util.AsyncListUtil;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.ArrayAdapter;

import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.util.Scanner;
import java.net.URL;

import android.widget.ListView;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText searchboxEtext, Emailaddress;
    TextView email;
    WebView webView;
    ListView lv;
    ArrayAdapter<String> adapter;
    LinearLayout linearLayout,transferlayout;
    Button button, transfer1, transfer2,barcodebutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        searchboxEtext = (EditText) findViewById(R.id.search_bar);
        lv = (ListView) findViewById(R.id.list_view);

        linearLayout = (LinearLayout) findViewById(R.id.search_layout);
        button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener(this);
        email = (TextView) findViewById(R.id.emailaddress);
        transfer1 = (Button) findViewById(R.id.Transfer1);
        barcodebutton=(Button) findViewById(R.id.Barcode_button);
        barcodebutton.setOnClickListener(this);

        transfer1.setOnClickListener(this);
        //webView = (WebView) findViewById(R.id.Cas_server);

        //webView.loadUrl("http://vteceinventory.azurewebsites.net/authentication/LogOn");

        transferlayout=(LinearLayout)findViewById(R.id.Transferlayout);

        Emailaddress = (EditText) findViewById(R.id.emailaddress);



    }


    private void InventorySearch() {
        String InQuery = searchboxEtext.getText().toString();
        URL inventoryurl = Internetconnect.bUrl(InQuery);
        new InventoryTask().execute(inventoryurl);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_button) {
            InventorySearch();
        }
        else if (v.getId()==R.id.Barcode_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
        else if (v.getId() == R.id.Transfer1) {
            setContentView(R.layout.transfer);

            transfer2 = (Button) findViewById(R.id.Transfer2);
            Emailaddress = (EditText) findViewById(R.id.emailaddress);

            transfer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });
        }
        else if (v.getId()==R.id.Transfer3){
            setContentView(R.layout.transfer);
            transfer2 = (Button) findViewById(R.id.Transfer2);
            Emailaddress = (EditText) findViewById(R.id.emailaddress);

            transfer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            URL Barcodeurl = Internetconnect.bUrl(scanContent);
            new InventoryTask().execute(Barcodeurl);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }



    public class InventoryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String inventorySearchresults = null;
            try {
                inventorySearchresults = Internetconnect.ResponsefromAzure(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return inventorySearchresults;
        }

        @Override
        protected void onPostExecute(String inventorySearchresults) {
            if (inventorySearchresults != null && !inventorySearchresults.equals("")) {
                inventorySearchresults = inventorySearchresults.replace("[", "");
                inventorySearchresults = inventorySearchresults.replace("{", "");
                inventorySearchresults = inventorySearchresults.replace("}", "");
                inventorySearchresults = inventorySearchresults.replace("]", "");
                inventorySearchresults = inventorySearchresults.replace(":", ",");
                inventorySearchresults = inventorySearchresults.replace("\"", " ");

                String[] inventorySresults = inventorySearchresults.split(",");

                linearLayout.setVisibility(View.GONE);
                transferlayout.setVisibility(View.VISIBLE);

                adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.tab, R.id.item_details, inventorySresults) {
                    @NonNull
                    @Override
                    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                        // Get the current item from ListView
                        View view = super.getView(position, convertView, parent);
                        if (position % 2 == 1) {
                            // Set a background color for ListView regular row/item
                            view.setBackgroundColor(Color.GRAY);
                        } else {
                            // Set the background color for alternate row/item
                            view.setBackgroundColor(Color.WHITE);
                        }
                        return view;
                    }
                };

                lv.setAdapter(adapter);


            }
        }
    }




}
