package com.praneethgurramgmail.EceInventory;


import android.os.AsyncTask;

import android.support.annotation.NonNull;
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

import com.praneethgurramgmail.myapplication2.R;


class Internetconnect {
    private final static String InUrl = "http://vteceinventory.azurewebsites.net/api/items/";

    static URL bUrl(String InQuery) {
        try {
            URL url = new URL(InUrl + InQuery);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

     static String ResponsefromAzure(URL url) throws IOException {
        HttpURLConnection inventoryconnection = (HttpURLConnection) url.openConnection();


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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText searchboxEtext, Emailaddress;
    TextView email;

    ListView lv;
    ArrayAdapter<String> adapter;
    LinearLayout linearLayout,transferlayout;
    Button button, transfer, transfer2;


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
        transfer = (Button) findViewById(R.id.Transfer);
        transfer.setOnClickListener(this);

        transferlayout=(LinearLayout)findViewById(R.id.Transferlayout);
        transferlayout.setVisibility(View.GONE);
        Emailaddress = (EditText) findViewById(R.id.emailaddress);



    }

    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int mSelectedItem = item.getItemId();
            if (mSelectedItem == R.id.action_search) {
                //Context context = MainActivity.this;
                //String toast = "Inventory item id clicked";
                //Toast.makeText(context,toast,Toast.LENGTH_LONG).show();
                InventorySearch();
            }
            return super.onOptionsItemSelected(item);
        }
    */
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
        else if (v.getId() == R.id.Transfer) {
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




