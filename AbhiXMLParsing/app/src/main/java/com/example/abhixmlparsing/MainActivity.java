package com.example.abhixmlparsing;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.TextView;

        import org.w3c.dom.Document;
        import org.w3c.dom.Element;
        import org.w3c.dom.NodeList;
        import org.xmlpull.v1.XmlPullParser;
        import org.xmlpull.v1.XmlPullParserException;
        import org.xmlpull.v1.XmlPullParserFactory;

        import java.io.IOException;
        import java.io.InputStream;
        import java.net.URL;
        import java.net.URLConnection;
        import java.util.ArrayList;

        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {


//    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        textView = (TextView) findViewById(R.id.text);

        XmlPullParserFactory pullParserFactory;

        ////////////////////////////////////////////////////////////////////////////////

        try
        {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            InputStream in_s = getApplicationContext().getAssets().open("pep3.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);

            ArrayList<Item> items = parseItemXML(parser);

            System.out.println("@@@ After parse XML");

            String text = "";

            for (Item item : items) {

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
                System.out.println("@@@" + text);
                System.out.println("@@@ \n\n\n");

            }

//            textView.setText(text);


        }
        catch (XmlPullParserException e) {

            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ////////////////////////////////////////////////////////////////////////////////

//        try
//        {
//            pullParserFactory = XmlPullParserFactory.newInstance();
//            XmlPullParser parser = pullParserFactory.newPullParser();
//
//            InputStream in_s = getApplicationContext().getAssets().open("sample.xml");
//            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
//            parser.setInput(in_s, null);
//
//            ArrayList<Country> countries = parseXML(parser);
//
//            System.out.println("@@@ After parse XML");
//
//            String text = "";
//
//            for (Country country : countries) {
//
//                text += "id : " + country.getId() + " name : " + country.getName() + " capital : " + country.getCapital() + "\n";
//                System.out.println("@@@" + text);
//
//            }
//
////            textView.setText(text);
//
//
//        }
//        catch (XmlPullParserException e) {
//
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

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

    private ArrayList<Country> parseXML(XmlPullParser parser) throws XmlPullParserException, IOException
    {
        System.out.println("@@@ inside parse XML");
        ArrayList<Country> countries = null;
        int eventType = parser.getEventType();
        Country country = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name;
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    countries = new ArrayList();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("country"))
                    {
                        country = new Country();
                        country.id = parser.getAttributeValue(null, "id");
                    }
                    else if (country != null)
                    {
                        if (name.equals("name"))
                        {
                            country.name = parser.nextText();
                        }
                        else if (name.equals("capital"))
                        {
                            country.capital = parser.nextText();
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("country") && country != null)
                    {
                        countries.add(country);
                    }
            }
            eventType = parser.next();
        }

        return countries;

    }
}