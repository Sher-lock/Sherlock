package com.srp.sher_lock_remote;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
/*
    TextView chat;
    EditText msg;
    Socket socket;
    BufferedReader in;
    InetAddress servraddr;
    **/
    String resp, resp1;
    Integer flag;
    protected static String url = "http://rishikesh.byethost5.com//error.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //    chat = (TextView) findViewById(R.id.txtChat);
    //    msg= (EditText) findViewById(R.id.txtMsg);
    //    new Thread(new ClientThread()).start();
        final Button shutdown = (Button) findViewById(R.id.shutdown);
        shutdown.setOnClickListener
                (
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                flag=1;
                                new putcall().execute();

                            }
                        }
                );

        final Button screenshot = (Button) findViewById(R.id.screenshot);
        screenshot.setOnClickListener
                (
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                flag=2;
                                new putcall().execute();

                            }
                        }
                );


    }
    private class putcall extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "Starting",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(url);
                System.out.println("hello");
                try {

                    List<NameValuePair> data = new ArrayList<NameValuePair>(1);
                    if (flag == 1)
                        data.add(new BasicNameValuePair("shutdown", "shutdown"));
                    else if (flag == 2)
                        data.add(new BasicNameValuePair("screenshot", "screenshot"));

                    httppost.setEntity(new UrlEncodedFormEntity(data));
                    System.out.println("" + data);

                    HttpResponse rs = httpclient.execute(httppost);

                    System.out.println("hello");


                } catch (Exception e) {
                    System.out.println("kay ahhe he");

                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("kay nahi");
                e.printStackTrace();
            }
            return null;
        }


        protected void onPostExecute(Void void1) {
            System.out.println("coming 3");


            Toast.makeText(getApplicationContext(), "Review given", Toast.LENGTH_SHORT).show();
        }
    }

/*
    class ClientThread implements Runnable
    {

        @Override
        public void run() {
            try {
                servraddr = InetAddress.getByName("192.168.0.9");
                socket=new Socket(servraddr,1234);
                in=new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String str=in.readLine();
                chat.setText(chat.getText().toString()+"\nDesktop : "+str);
            }
            catch(Exception e){}
        }
    }
*/

/*
     public void send(View view)
    {

        String str;
        str=msg.getText().toString();
        chat.setText(chat.getText().toString()+"\nRemote : "+str);
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        msg.setText("");
    }
    */
}
