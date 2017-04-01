package com.praneethgurramgmail.eceinventory;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView getitems;
    EditText getrequest;
    Button scan, search;
    LinearLayout list,linearLayout;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getrequest = (EditText) findViewById(R.id.search_bar);
        search = (Button) findViewById(R.id.search_button);
        scan = (Button) findViewById(R.id.Barcode_button);
        list = (LinearLayout) findViewById(R.id.list);
        getitems = (ListView) findViewById(R.id.list_view2);
        linearLayout=(LinearLayout)findViewById(R.id.activity_main);
    }

    private void InventorySearch() {
        String InQuery = getrequest.getText().toString();
        URL inventoryurl = Internetconnect.bUrl(InQuery);
        new InventoryTask().execute(inventoryurl);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_button) {
            InventorySearch();
        }
        else if (v.getId() == R.id.Barcode_button) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            URL Barcodeurl = Internetconnect.bUrl(scanContent);
            new InventoryTask().execute(Barcodeurl);
        } else {
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

                adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.searchdisplay, R.id.results_view, inventorySresults) {
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

                getitems.setAdapter(adapter);


            }
        }
    }
}
