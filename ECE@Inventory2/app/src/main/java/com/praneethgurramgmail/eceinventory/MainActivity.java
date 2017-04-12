package com.praneethgurramgmail.eceinventory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

   /* private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                   //setContentView(R.layout.tab1pid);
                    Intent intent0 =  new Intent(getApplicationContext(), OwnedItems.class);
                    startActivity(intent0);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                   // setContentView(R.layout.tab2search);
                    Intent intent =  new Intent(getApplicationContext(), ScanSearch.class);
                   startActivity(intent);
                   // setContentView(R.layout.tab2search);
                    return true;

            }
            return false;
        }

    };
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //new Mywebview();
        Intent intent = new Intent(getApplicationContext(),OwnedItems.class);
        startActivity(intent);
        //setContentView(R.layout.tab1pid);
        //mTextMessage = (TextView) findViewById(R.id.message);
      //  BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
       // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
