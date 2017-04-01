package com.praneethgurramgmail.eceinventory;

/**
 * Created by praneeth on 3/29/2017.
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
public class Internetconnect {
    private final static String InUrl = "http://http://eceinventory.ece.vt.edu/api/items/";

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
