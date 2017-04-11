package com.praneethgurramgmail.myapplication;

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
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button button;
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText =(EditText)findViewById(R.id.editText);
        textView=(TextView)findViewById(R.id.textview);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId ()==R.id.button);
                InventorySearch();
            }
        });

    }

    private void InventorySearch() {
        String InQuery = editText.getText().toString();
        URL inventoryurl = Internetconnect.bUrl2(InQuery);
        new InventoryTask ().execute(inventoryurl);

    }
    public class InventoryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String inventorySearchresults = null;
            try {
                inventorySearchresults = Internetconnect.ResponsefromAzure2 (searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return inventorySearchresults;
        }

        @Override
        protected void onPostExecute(String inventorySearchresults) {
            if (inventorySearchresults != null && !inventorySearchresults.equals("")) {
               /* inventorySearchresults = inventorySearchresults.replace("[", "");
                inventorySearchresults = inventorySearchresults.replace("{", "");
                inventorySearchresults = inventorySearchresults.replace("}", "");
                inventorySearchresults = inventorySearchresults.replace("]", "");
                inventorySearchresults = inventorySearchresults.replace(":", ",");
                inventorySearchresults = inventorySearchresults.replace("\"", " ");

                String[] inventorySresults = inventorySearchresults.split(",");
                */
                textView.setText(inventorySearchresults);


                /*pidlayout.setVisibility(View.GONE);


                adapter = new ArrayAdapter<String>(getActivity(), R.layout.rowlayout, R.id.textView, inventorySresults) {
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

                */

            }

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
public void parseJSON(View view)
{

}

}
