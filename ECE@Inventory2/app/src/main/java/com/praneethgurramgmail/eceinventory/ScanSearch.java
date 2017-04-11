package com.praneethgurramgmail.eceinventory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
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

/**
 * Created by praneeth on 4/10/2017.
 */

public class ScanSearch extends AppCompatActivity implements View.OnClickListener{
    ArrayAdapter<String> adapter;
    EditText editText_search;
    Button button_search, button_scan;
    ListView listView;
    LinearLayout linearLayout, textview_layout;
    TextView textView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    //setContentView(R.layout.tab1pid);
                    Intent intent0 =  new Intent(getApplicationContext(), OwnedItems.class);
                    startActivity(intent0);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    // setContentView(R.layout.tab2search);
                    Intent intent =  new Intent(getApplicationContext(), ScanSearch.class);
                    startActivity(intent);
                    // setContentView(R.layout.tab2search);
                    return true;

            }
            return false;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2search);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        editText_search = (EditText)findViewById(R.id.search);
        button_search=(Button) findViewById(R.id.search_button);
        button_scan = (Button)findViewById(R.id.scan_button);
        button_search.setOnClickListener(this);
        button_scan.setOnClickListener(this);
       // listView = (ListView)findViewById(R.id.list_view);
        linearLayout=(LinearLayout)findViewById(R.id.tab2_layout);
        //textview_layout=(LinearLayout)findViewById(R.id.textview_layout);
        //textView = (TextView)findViewById(R.id.text_view);
    }
    private void InventorySearch() {
        String InQuery = editText_search.getText().toString();
        URL inventoryurl = Internetconnect.bUrl(InQuery);
        new InventoryTask().execute(inventoryurl);

    }
    @Override public void onClick(View v){
        if(v.getId()==R.id.search_button){
            InventorySearch();
        }
        else if (v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            URL inventoryurl = Internetconnect.bUrl(scanContent);
            new InventoryTask().execute(inventoryurl);
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
            Log.d(inventorySearchresults,"error");
            return inventorySearchresults;
        }

        @Override
        protected void onPostExecute(String inventorySearchresults) {
            if (inventorySearchresults != null && !inventorySearchresults.equals("")) {


            }

        }
    }
}
