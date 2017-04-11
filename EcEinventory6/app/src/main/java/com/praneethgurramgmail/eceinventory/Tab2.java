package com.praneethgurramgmail.eceinventory;

/**
 * Created by praneeth on 4/4/2017.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class Tab2 extends Fragment {

    EditText tab2_search;
    Button tab2_button;
    ListView tab2_listview,listview2;
    TextView tab2_textview;
    LinearLayout linearLayout,scan;
    ArrayAdapter<String> adapter;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ItemAdapter itemAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tan2search, container, false);

        itemAdapter = new ItemAdapter(getContext (),R.layout.rowlayout);
        tab2_search = (EditText)rootView.findViewById(R.id.search_bar);
        tab2_button = (Button)rootView.findViewById(R.id.search_button);
        tab2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.search_button) {
                    InventorySearch();
                     new actionbar ();
                }
            }
        });
        tab2_listview = (ListView)rootView.findViewById(R.id.list_view);
        tab2_textview =(TextView)rootView.findViewById(R.id.tab2);
        linearLayout =(LinearLayout)rootView.findViewById(R.id.search);
        scan =(LinearLayout)rootView.findViewById (R.id.Scan);

        return rootView;
    }






    private void InventorySearch() {
        String InQuery = tab2_search.getText().toString();
        URL inventoryurl = Internetconnect.bUrl(InQuery);
        new InventoryTask().execute(inventoryurl);

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
                scan.setVisibility (View.GONE);
                /*tab2_listview.setAdapter(itemAdapter);
                try {
                    jsonObject = new JSONObject(inventorySearchresults);
                    jsonArray = jsonObject.getJSONArray("Item");
                    int count=0;
                    String Owner, OrgnCode, OrgnTitle, Room, Bldg, SortRoom, Ptag, Manufacturer, Model, SN, Description, PO, AcqDate, Amt, Ownership, SchevYear,
                            TagType, AssetType, AtypTitle, Condition, LastInvDate, Designation;
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

                        Items items = new Items(Owner, OrgnCode, OrgnTitle, Room, Bldg, SortRoom, Ptag, Manufacturer, Model, SN, Description, PO, AcqDate, Amt, Ownership, SchevYear,
                                TagType, AssetType, AtypTitle, Condition, LastInvDate, Designation);

                        itemAdapter.add(items);
                        count++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                */
               //linearLayout.setVisibility(View.GONE);
                //scan.setVisibility (View.GONE);

                adapter = new ArrayAdapter<String>(getActivity(), R.layout.listlayout, R.id.tab2, inventorySresults) {
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

                tab2_listview.setAdapter(adapter);



            }

        }
    }
    public class actionbar extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate (savedInstanceState);
            setContentView (R.layout.tan2search);
            getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
            Intent intent = new Intent (getApplicationContext (),actionbar.class);
            startActivity (intent);

        }
    }

}

