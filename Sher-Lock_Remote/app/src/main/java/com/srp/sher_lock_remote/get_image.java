package com.srp.sher_lock_remote;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class get_image extends ActionBarActivity {
    String result;
    InputStream is;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getimage);
        imageView=(ImageView)findViewById(R.id.imageView);
        new putcall().execute();
    }


    private class putcall extends AsyncTask<Void, Void, Void> {
        String image;

        @Override
        protected Void doInBackground(Void... params) {

            try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("//Ur URL");

                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection " + e.toString());
                }

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();

                    result = sb.toString();
                } catch (Exception e) {
                    Log.e("log_tag", "Error converting result " + e.toString());
                }
                return null;
                // TODO Auto-generated method stub

            }

            protected void onPostExecute(Void v) {
         /*       try {


                    JSONArray jArray = new JSONArray(result);
                    for (int i = 0; i <= jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);


                        String image = json_data.getString("image");


                    }

                    byte[] rawImage = Base64.decode(image, Base64.DEFAULT);
                    Bitmap bmp = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length);


                    imageview.setImageBitmap(bmp);


                } catch (JSONException e) {
                    Log.e("log_tag", "Error parsing data " + e.toString());
                }
*/
            }
        }
    }

