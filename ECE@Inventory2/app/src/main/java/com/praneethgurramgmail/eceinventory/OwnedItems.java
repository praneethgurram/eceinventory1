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
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

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
             ArrayList<Item> myItems = parseXMLString(inventorySearchresults);   // Added by Abhi

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

    public ArrayList<Item> parseXMLString(String xmlStr)
    {
        XmlPullParserFactory pullParserFactory;
        ArrayList<Item> items = new ArrayList<>();

        try
        {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            String returnedStr = "<ArrayOfItem xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://schemas.datacontract.org/2004/07/ECEInventory.Models\"><Item><AcqDate>06-MAR-2007</AcqDate><Amt>2,119.00</Amt><AssetType i:nil=\"true\"/><AtypTitle i:nil=\"true\"/><Bldg>Whittemore Hall</Bldg><Condition i:nil=\"true\"/><Custodian>Plassmann, Paul E.</Custodian><Description>Computer, Fujitsu Lifebook T4210</Description><Designation i:nil=\"true\"/><LastInvDate i:nil=\"true\"/><Manufacturer>FUJITSU</Manufacturer><Model>LIFEBOOK T4210</Model><OrgnCode i:nil=\"true\"/><OrgnTitle i:nil=\"true\"/><Owner i:nil=\"true\"/><Ownership>University owned</Ownership><PO>MAR0607</PO><Ptag>000294241</Ptag><Room>302C</Room><SN>R6600789</SN><SchevYear>01-DEC-2016</SchevYear><SortRoom>00302C Whittemore Hall</SortRoom><TagType i:nil=\"true\"/></Item><Item><AcqDate>23-APR-2007</AcqDate><Amt>2,130.04</Amt><AssetType i:nil=\"true\"/><AtypTitle i:nil=\"true\"/><Bldg>Home Use</Bldg><Condition i:nil=\"true\"/><Custodian>Plassmann, Paul</Custodian><Description>Computer, Apple iMac 24\"</Description><Designation i:nil=\"true\"/><LastInvDate i:nil=\"true\"/><Manufacturer>APPLE</Manufacturer><Model>IMAC</Model><OrgnCode i:nil=\"true\"/><OrgnTitle i:nil=\"true\"/><Owner i:nil=\"true\"/><Ownership>University owned</Ownership><PO>P0733043</PO><Ptag>000300819</Ptag><Room>HOME</Room><SN>SQP712088VGP</SN><SchevYear>17-APR-2015</SchevYear><SortRoom>99999HOME Home Use</SortRoom><TagType i:nil=\"true\"/></Item><Item><AcqDate>14-APR-2010</AcqDate><Amt>2,755.21</Amt><AssetType i:nil=\"true\"/><AtypTitle i:nil=\"true\"/><Bldg>Home Use</Bldg><Condition i:nil=\"true\"/><Custodian>Plassmann, Paul</Custodian><Description>Laptop, Apple Macbook Pro</Description><Designation i:nil=\"true\"/><LastInvDate i:nil=\"true\"/><Manufacturer>APPLE</Manufacturer><Model>MACBOOK PRO</Model><OrgnCode i:nil=\"true\"/><OrgnTitle i:nil=\"true\"/><Owner i:nil=\"true\"/><Ownership>University owned</Ownership><PO>P1097228</PO><Ptag>000321032</Ptag><Room>HOME</Room><SN>SW80090RQ64C</SN><SchevYear>11-MAY-2015</SchevYear><SortRoom>99999HOME Home Use</SortRoom><TagType i:nil=\"true\"/></Item><Item><AcqDate>28-MAR-2013</AcqDate><Amt>1,896.19</Amt><AssetType i:nil=\"true\"/><AtypTitle i:nil=\"true\"/><Bldg>Home Use</Bldg><Condition i:nil=\"true\"/><Custodian>Plassmann, Paul</Custodian><Description>Laptop, Apple Macbook Pro</Description><Designation i:nil=\"true\"/><LastInvDate i:nil=\"true\"/><Manufacturer>APPLE</Manufacturer><Model>MACBOOK PRO</Model><OrgnCode i:nil=\"true\"/><OrgnTitle i:nil=\"true\"/><Owner i:nil=\"true\"/><Ownership>SCHEV ownership</Ownership><PO>P2451321</PO><Ptag>000354629</Ptag><Room>HOME</Room><SN>C02KC5D5FFT0</SN><SchevYear>11-MAY-2015</SchevYear><SortRoom>99999HOME Home Use</SortRoom><TagType i:nil=\"true\"/></Item></ArrayOfItem>";
            InputStream in_s = new ByteArrayInputStream(returnedStr.getBytes("UTF-8"));

            //InputStream in_s = getApplicationContext().getAssets().open("pep3.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);

            items = parseItemXML(parser);

            System.out.println("@@@ After parse XML");

            String text = "";

            for (Item item : items)
            {

                System.out.println("@@@ item name :" + item.getDescription());

                text += "AcqDate = " + item.getAcqDate() +
                        "\n Amt = " + item.getAmt() +
                        "\n AssetType = " + item.getAssetType() +
                        "\n AtypTitle = " + item.getAtypTitle() +
                        "\n Bldg = " + item.getBldg() +
                        "\n Condition = " + item.getCondition() +
                        "\n Custodian = " + item.getCustodian() +
                        "\n Description = " + item.getDescription() +
                        "\n Designation = " + item.getDesignation() +
                        "\n LastInvDate = " + item.getLastInvDate() +
                        "\n Manufacturer = " + item.getManufacturer() +
                        "\n Model = " + item.getModel() +
                        "\n OrgnCode = " + item.getOrgnCode() +
                        "\n OrgnTitle = " + item.getOrgnTitle() +
                        "\n Owner = " + item.getOwner() +
                        "\n Ownership = " + item.getOwnership() +
                        "\n PO = " + item.getPO() +
                        "\n Ptag = " + item.getPtag() +
                        "\n Room = " + item.getRoom() +
                        "\n SN = " + item.getSN() +
                        "\n SchevYear = " + item.getSchevYear() +
                        "\n SortRoom = " + item.getSortRoom() +
                        "\n TagType = "  + item.getTagType() +
                        "\n";
            }

            System.out.println("@@@" + text);

//            textView.setText(text);
        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return items;
    }

    private ArrayList<Item> parseItemXML(XmlPullParser parser) throws XmlPullParserException, IOException
    {
        System.out.println("@@@ inside parse XML");
        ArrayList<Item> items = null;
        int eventType = parser.getEventType();
        Item item = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name;
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    items = new ArrayList();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("Item"))
                    {
                        item = new Item();
//                        item.AcqDate= parser.getAttributeValue(null, "AcqDate");
                    }
                    else if (item != null)
                    {
                        if (name.equalsIgnoreCase("AcqDate"))
                            item.AcqDate = parser.nextText();
                        else if (name.equalsIgnoreCase("Amt"))
                            item.Amt = parser.nextText();
                        else if (name.equalsIgnoreCase("AssetType"))
                            item.AssetType = parser.nextText();
                        else if (name.equalsIgnoreCase("AtypTitle"))
                            item.AtypTitle = parser.nextText();
                        else if (name.equalsIgnoreCase("Bldg"))
                            item.Bldg = parser.nextText();
                        else if (name.equalsIgnoreCase("Condition"))
                            item.Condition = parser.nextText();
                        else if (name.equalsIgnoreCase("Custodian"))
                            item.Custodian = parser.nextText();
                        else if (name.equalsIgnoreCase("Description"))
                            item.Description = parser.nextText();
                        else if (name.equalsIgnoreCase("Designation"))
                            item.Designation = parser.nextText();
                        else if (name.equalsIgnoreCase("LastInvDate"))
                            item.LastInvDate = parser.nextText();
                        else if (name.equalsIgnoreCase("Manufacturer"))
                            item.Manufacturer = parser.nextText();
                        else if (name.equalsIgnoreCase("Model"))
                            item.Model = parser.nextText();
                        else if (name.equalsIgnoreCase("OrgnCode"))
                            item.OrgnCode = parser.nextText();
                        else if (name.equalsIgnoreCase("OrgnTitle"))
                            item.OrgnTitle = parser.nextText();
                        else if (name.equalsIgnoreCase("Owner"))
                            item.Owner = parser.nextText();
                        else if (name.equalsIgnoreCase("Ownership"))
                            item.Ownership = parser.nextText();
                        else if (name.equalsIgnoreCase("PO"))
                            item.PO = parser.nextText();
                        else if (name.equalsIgnoreCase("Ptag"))
                            item.Ptag = parser.nextText();
                        else if (name.equalsIgnoreCase("Room"))
                            item.Room = parser.nextText();
                        else if (name.equalsIgnoreCase("SN"))
                            item.SN = parser.nextText();
                        else if (name.equalsIgnoreCase("SchevYear"))
                            item.SchevYear = parser.nextText();
                        else if (name.equalsIgnoreCase("SortRoom"))
                            item.SortRoom = parser.nextText();
                        else if (name.equalsIgnoreCase("TagType"))
                            item.TagType = parser.nextText();
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("Item") && item != null)
                    {
                        items.add(item);
                    }
            }
            eventType = parser.next();
        }

        return items;

    }
}
