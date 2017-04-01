package com.praneethgurramgmail.eceinventory;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import android.app.Activity;
class Internetconnect {
    private final static String InUrl = "http://eceinventory.ece.vt.edu//api/items/";
    private final static String NUrl = "http://eceinventory.ece.vt.edu//api/UsersByPID";
    private final static String PUrl = "http://eceinventory.ece.vt.edu//api/Transfers";
    static URL bUrl(String InQuery) {
        try {
            URL url = new URL(InUrl + InQuery);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    static URL bUrl2(String InQuery) {
        try {
            URL url = new URL(NUrl + InQuery);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static URL bUrl3(String InQuery) {
        try {
            URL url = new URL(PUrl + InQuery);
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
    static String ResponsefromAzure2(URL url) throws IOException {
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
    EditText searchboxEtext, Emailaddress,pidtext;
    TextView email;
    WebView webView;
    ListView lv;
    ArrayAdapter<String> adapter;
    LinearLayout linearLayout, transferlayout,login;
    Button button, transfer, transfer2, scan,pidsearch;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


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

        transferlayout = (LinearLayout) findViewById(R.id.Transferlayout);
        transferlayout.setVisibility(View.GONE);
        Emailaddress = (EditText) findViewById(R.id.emailaddress);
        login = (LinearLayout)findViewById(R.id.login_layout);
        scan = (Button) findViewById(R.id.Barcode_button);
        scan.setOnClickListener(this);
        webView = (WebView) findViewById(R.id.Cas_server);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("http://vteceinventory.azurewebsites.net/authentication/LogOn");
        String url = ("http://vteceinventory.azurewebsites.net/authentication/LogOn");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private class WebViewClient extends android.webkit.WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            login.setVisibility(View.GONE);
        }
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
        } else if (v.getId() == R.id.Transfer) {
            setContentView(R.layout.transfer);

            transfer2 = (Button) findViewById(R.id.Transfer2);
            Emailaddress = (EditText) findViewById(R.id.emailaddress);

            transfer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        } else if (v.getId() == R.id.Barcode_button) {
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
                    "No matched data found in inventory", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
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
            else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "No matched data found in inventory", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }








}
