package com.praneethgurramgmail.eceinventory;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.suitebuilder.TestMethod;
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
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getrequest = (EditText) findViewById(R.id.search_bar);
        search = (Button) findViewById(R.id.search_button);
        search.setOnClickListener(this);
        textView =(TextView)findViewById(R.id.results_view) ;
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

                setContentView(R.layout.searchdisplay);

                textView.setText(inventorySearchresults);

            }
        }
    }
}
