package com.praneethgurramgmail.test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.MediaType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
public class MainActivity extends AppCompatActivity {

    public static final MediaType FORM_DATA_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    //URL derived from form URL
    public static final String URL="https://docs.google.com/a/vt.edu/forms/d/e/1FAIpQLSeIDZvcjGigk0e2Yor5zHH88Vja78c1KOaOQb9C0fdtrFdhzg/formResponse";
    //input element ids found from the live form page
    public static final String EMAIL_KEY="entry.1126076247";



    private EditText emailEditText;

    private final Context context;

    public MainActivity(Context context) {
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //save the activity in a context variable to be used afterwards
        //context =this;

        //Get references to UI elements in the layout
        Button sendButton = (Button)findViewById(R.id.sendButton);
        emailEditText = (EditText)findViewById(R.id.emailEditText);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Make sure all the fields are filled with values
                if(TextUtils.isEmpty(emailEditText.getText().toString()) )
                {
                    Toast.makeText(context,"All fields are mandatory.",Toast.LENGTH_LONG).show();
                    return;
                }
                //Check if a valid email is entered
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches())
                {
                    Toast.makeText(context,"Please enter a valid email.",Toast.LENGTH_LONG).show();
                    return;
                }

                //Create an object for PostDataTask AsyncTask
                PostDataTask postDataTask = new PostDataTask();

                //execute asynctask
                postDataTask.execute(URL,emailEditText.getText().toString()
                        );
            }
        });
    }

    //AsyncTask to send data as a http POST request
    private class PostDataTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... contactData) {
            Boolean result = true;
            String url = contactData[0];
            String email = contactData[1];
            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = EMAIL_KEY+"=" + URLEncoder.encode(email,"UTF-8") ;
            } catch (UnsupportedEncodingException ex) {
                result=false;
            }

            /*
            //If you want to use HttpRequest class from http://stackoverflow.com/a/2253280/1261816
            try {
			HttpRequest httpRequest = new HttpRequest();
			httpRequest.sendPost(url, postBody);
		}catch (Exception exception){
			result = false;
		}
            */

            try{
                //Create OkHttpClient for sending request
                OkHttpClient client = new OkHttpClient();
                //Create the request body with the help of Media Type
                RequestBody body = RequestBody.create(FORM_DATA_TYPE, postBody);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                //Send the request
                okhttp3.Response response = client.newCall(request).execute();
            }catch (IOException exception){
                result=false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result){
            //Print Success or failure message accordingly
            Toast.makeText(context,result?"Message successfully sent!":"There was some error in sending message. Please try again after some time.",Toast.LENGTH_LONG).show();
        }

    }
}
