package com.praneethgurramgmail.myapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by praneeth on 4/5/2017.
 */

public class Internetconnect {
    String JSON_String;
    private final static String InUrl = "http://eceinventory.ece.vt.edu//api/items/";
    private final static String NUrl = "http://eceinventory.ece.vt.edu//api/UsersByPID/";
    private final static String PUrl = "http://eceinventory.ece.vt.edu//api/Transfers/";
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
    String ResponsefromAzure2(URL url) throws IOException {
        HttpURLConnection inventoryconnection = (HttpURLConnection) url.openConnection();


        try {
            InputStream input = inventoryconnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
            StringBuilder stringBuilder=new StringBuilder();
            while((JSON_String = bufferedReader.readLine())!=null ){
                stringBuilder.append(JSON_String+"\n");

            }
            bufferedReader.close();
            input.close();
            return stringBuilder.toString().trim();
            /*System.out.println(input);
            Scanner scan = new Scanner(input);
            scan.useDelimiter("\\A");
            boolean Input = scan.hasNext();
            if (Input) {
                return scan.next();
            } else {
                return null;
            }
            */
        } finally {
            inventoryconnection.disconnect();
        }
    }
}
