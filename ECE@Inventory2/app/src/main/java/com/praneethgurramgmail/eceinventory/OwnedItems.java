package com.praneethgurramgmail.eceinventory;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

/**
 * Created by praneeth on 4/10/2017.
 */

public class OwnedItems extends AppCompatActivity {
    EditText pid;
    Button enter;
    LinearLayout pidlayout,rowlayout,pid2;
    ListView listView;
    ArrayAdapter<String> adapter;
    JSONObject jsonObject;
    JSONArray jsonArray;
    TextView textView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    //setContentView(R.layout.tab1pid);
                    Intent intent0 = new Intent(getApplicationContext(), OwnedItems.class);
                    startActivity(intent0);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    // setContentView(R.layout.tab2search);
                    Intent intent = new Intent(getApplicationContext(), ScanSearch.class);
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
        setContentView(R.layout.tab1pid);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        pid = (EditText) findViewById(R.id.enter_pid);
        enter = (Button) findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.enter) ;


                InventorySearch();
            }
        });
        pidlayout = (LinearLayout) findViewById(R.id.pid_layout);
        // listView=(ListView)findViewById (R.id.list_view);
        rowlayout =(LinearLayout)findViewById(R.id.pidlinear1);
        textView =(TextView)findViewById(R.id.pidtextview);
        pid2 =(LinearLayout)findViewById(R.id.pid2);

    }

    private void InventorySearch() {
        String InQuery = pid.getText().toString();
        URL inventoryurl = Internetconnect.bUrl2(InQuery);
        new InventoryTask().execute(inventoryurl);

    }

    public class InventoryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String inventorySearchresults = null;
            try {
                inventorySearchresults = Internetconnect.ResponsefromAzure2(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return inventorySearchresults;
        }

        @Override
        protected void onPostExecute(String inventorySearchresults) {
            if (inventorySearchresults != null && !inventorySearchresults.equals("")) {
              /*  inventorySearchresults = inventorySearchresults.replace("[", "");
                inventorySearchresults = inventorySearchresults.replace("{", "");
                inventorySearchresults = inventorySearchresults.replace("}", "");
                inventorySearchresults = inventorySearchresults.replace("]", "");
                inventorySearchresults = inventorySearchresults.replace(":", ",");
                inventorySearchresults = inventorySearchresults.replace("\"", " ");

                String[] inventorySresults = inventorySearchresults.split(",");


                */
              pid2.setVisibility(View.GONE);
                textView.setText(inventorySearchresults);
               /* pidlayout.setVisibility(View.GONE);


                adapter = new ArrayAdapter<String> (OwnedItems.this, R.layout.rowlayout, R.id.textView, inventorySresults) {
                    @NonNull
                    @Override
                    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                        // Get the current item from ListView
                        View view = super.getView(position, convertView, parent);
                        if (position % 2 == 1) {
                            // Set a background color for ListView regular row/item
                            view.setBackgroundColor(0xFF592B27);
                        } else {
                            // Set the background color for alternate row/item
                            view.setBackgroundColor(Color.WHITE);
                        }
                        return view;
                    }
                };

                listView.setAdapter(adapter);



            }*/

           /* try {
                jsonObject = new JSONObject(inventorySearchresults);
                jsonArray = jsonObject.getJSONArray("ArrayOfItem");
                int count=0;
                String Owner, OrgnCode, OrgnTitle, Room, Bldg, SortRoom, Ptag, Manufacturer, Model, SN, Description, PO, AcqDate, Amt, Ownership, SchevYear,
                        TagType, AssetType, AtypTitle, Condition, LastInvDate, Designation;
                ArrayList<String> items = new ArrayList<> ();
                while (count<jsonArray.length()){
                    JSONObject Jo = jsonArray.getJSONObject(count);
                    Owner = Jo.getString("Owner");
                    OrgnCode=Jo.getString("OrgnCode");
                    OrgnTitle=Jo.getString("OrgnTitle");
                    Room=Jo.getString("Room");
                    Bldg=Jo.getString("Bldg");
                    SortRoom=Jo.getString("SortRoom");
                    Ptag=Jo.getString("Ptag");
                    Manufacturer=Jo.getString("Manufacturer");
                    Model=Jo.getString("Model");
                    SN=Jo.getString("SN");
                    Description=Jo.getString("Description");
                    PO=Jo.getString("PO");
                    AcqDate=Jo.getString("AcqDate");
                    Amt=Jo.getString("Amt");
                    SchevYear=Jo.getString("SchevYear");
                    Ownership=Jo.getString("Ownership");
                    TagType=Jo.getString("TagType");
                    AssetType=Jo.getString("AssetType");
                    AtypTitle=Jo.getString("AtypTitle");
                    Condition=Jo.getString("Condition");
                    LastInvDate=Jo.getString("LastInvDate");
                    Designation=Jo.getString("Designation");

                   items.add(Owner) ;
                    items.add(OrgnCode);
                     items.add( OrgnTitle);

                           (Owner, OrgnCode, OrgnTitle, Room, Bldg, SortRoom, Ptag, Manufacturer, Model, SN, Description, PO, AcqDate, Amt, Ownership, SchevYear,
                            TagType, AssetType, AtypTitle, Condition, LastInvDate, Designation);


                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
            }
        }
    }
}
