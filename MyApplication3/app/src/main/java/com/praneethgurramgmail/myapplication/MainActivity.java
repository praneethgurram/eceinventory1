package com.praneethgurramgmail.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView)findViewById(R.id.web1);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl( "http://vteceinventory.azurewebsites.net/authentication/LogOn" );
    }
}
