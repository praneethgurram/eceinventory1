package com.praneethgurramgmail.eceinventory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by praneeth on 4/10/2017.
 */

public class Mywebview extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mywebview);

        webView = (WebView) findViewById(R.id.Cas_server);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("https://vteceinventory.azurewebsites.net/authentication/LogOn");
        String url = ("https://vteceinventory.azurewebsites.net/authentication/LogOn");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }
    private class WebViewClient extends android.webkit.WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            //login.setVisibility(View.GONE);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            Log.d("tt",url.substring(0,11));
            String value = null;
            if(url.substring(0,12).equals("https://ecei")) {
                value = cookieManager.getCookie("https://vteceinventory.azurewebsites.net/authentication/ProduceCookie");
            }


            if(value != null && value.contains("pep3=pep3=pep3")) {

                Intent intent = new Intent(getApplicationContext(),OwnedItems.class);
                startActivity(intent);
            }

        }
    }
}
