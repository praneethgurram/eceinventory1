package com.praneethgurramgmail.eceinventory;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

/**
 * Created by praneeth on 4/12/2017.
 */

public class listdisplay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.listview);

    }
    public class InventoryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String inventorySearchresults = null;
            String inventorySearchresults1 = "NO Items for that barcode";
            try {
                inventorySearchresults = Internetconnect.ResponsefromAzure(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(inventorySearchresults==null){
                inventorySearchresults = inventorySearchresults1;
            }

           // Log.d(inventorySearchresults,"error");
            return inventorySearchresults;
        }

        @Override
        protected void onPostExecute(String inventorySearchresults) {
            if (inventorySearchresults != null && !inventorySearchresults.equals("")) {


            }

        }
    }
}
