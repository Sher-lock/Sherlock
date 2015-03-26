package com.srp.sher_lock_remote;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainActivity extends ActionBarActivity {

    TextView chat;
    EditText msg;
    Socket socket;
    BufferedReader in;
    InetAddress servraddr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chat = (TextView) findViewById(R.id.txtChat);
        msg= (EditText) findViewById(R.id.txtMsg);


        new Thread(new ClientThread()).start();

    }

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


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
}
