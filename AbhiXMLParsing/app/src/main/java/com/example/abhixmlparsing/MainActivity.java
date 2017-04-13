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

        import java.io.ByteArrayInputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.net.URL;
        import java.net.URLConnection;
        import java.nio.charset.StandardCharsets;
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
            String returnedStr = "<ArrayOfItem xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://schemas.datacontract.org/2004/07/ECEInventory.Models\"><Item><AcqDate>06-MAR-2007</AcqDate><Amt>2,119.00</Amt><AssetType i:nil=\"true\"/><AtypTitle i:nil=\"true\"/><Bldg>Whittemore Hall</Bldg><Condition i:nil=\"true\"/><Custodian>Plassmann, Paul E.</Custodian><Description>Computer, Fujitsu Lifebook T4210</Description><Designation i:nil=\"true\"/><LastInvDate i:nil=\"true\"/><Manufacturer>FUJITSU</Manufacturer><Model>LIFEBOOK T4210</Model><OrgnCode i:nil=\"true\"/><OrgnTitle i:nil=\"true\"/><Owner i:nil=\"true\"/><Ownership>University owned</Ownership><PO>MAR0607</PO><Ptag>000294241</Ptag><Room>302C</Room><SN>R6600789</SN><SchevYear>01-DEC-2016</SchevYear><SortRoom>00302C Whittemore Hall</SortRoom><TagType i:nil=\"true\"/></Item><Item><AcqDate>23-APR-2007</AcqDate><Amt>2,130.04</Amt><AssetType i:nil=\"true\"/><AtypTitle i:nil=\"true\"/><Bldg>Home Use</Bldg><Condition i:nil=\"true\"/><Custodian>Plassmann, Paul</Custodian><Description>Computer, Apple iMac 24\"</Description><Designation i:nil=\"true\"/><LastInvDate i:nil=\"true\"/><Manufacturer>APPLE</Manufacturer><Model>IMAC</Model><OrgnCode i:nil=\"true\"/><OrgnTitle i:nil=\"true\"/><Owner i:nil=\"true\"/><Ownership>University owned</Ownership><PO>P0733043</PO><Ptag>000300819</Ptag><Room>HOME</Room><SN>SQP712088VGP</SN><SchevYear>17-APR-2015</SchevYear><SortRoom>99999HOME Home Use</SortRoom><TagType i:nil=\"true\"/></Item><Item><AcqDate>14-APR-2010</AcqDate><Amt>2,755.21</Amt><AssetType i:nil=\"true\"/><AtypTitle i:nil=\"true\"/><Bldg>Home Use</Bldg><Condition i:nil=\"true\"/><Custodian>Plassmann, Paul</Custodian><Description>Laptop, Apple Macbook Pro</Description><Designation i:nil=\"true\"/><LastInvDate i:nil=\"true\"/><Manufacturer>APPLE</Manufacturer><Model>MACBOOK PRO</Model><OrgnCode i:nil=\"true\"/><OrgnTitle i:nil=\"true\"/><Owner i:nil=\"true\"/><Ownership>University owned</Ownership><PO>P1097228</PO><Ptag>000321032</Ptag><Room>HOME</Room><SN>SW80090RQ64C</SN><SchevYear>11-MAY-2015</SchevYear><SortRoom>99999HOME Home Use</SortRoom><TagType i:nil=\"true\"/></Item><Item><AcqDate>28-MAR-2013</AcqDate><Amt>1,896.19</Amt><AssetType i:nil=\"true\"/><AtypTitle i:nil=\"true\"/><Bldg>Home Use</Bldg><Condition i:nil=\"true\"/><Custodian>Plassmann, Paul</Custodian><Description>Laptop, Apple Macbook Pro</Description><Designation i:nil=\"true\"/><LastInvDate i:nil=\"true\"/><Manufacturer>APPLE</Manufacturer><Model>MACBOOK PRO</Model><OrgnCode i:nil=\"true\"/><OrgnTitle i:nil=\"true\"/><Owner i:nil=\"true\"/><Ownership>SCHEV ownership</Ownership><PO>P2451321</PO><Ptag>000354629</Ptag><Room>HOME</Room><SN>C02KC5D5FFT0</SN><SchevYear>11-MAY-2015</SchevYear><SortRoom>99999HOME Home Use</SortRoom><TagType i:nil=\"true\"/></Item></ArrayOfItem>";
            InputStream in_s = new ByteArrayInputStream(returnedStr.getBytes("UTF-8"));

            //InputStream in_s = getApplicationContext().getAssets().open("pep3.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);

            ArrayList<Item> items = parseItemXML(parser);

            System.out.println("@@@ After parse XML");

            String text = "";

            for (Item item : items) {

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