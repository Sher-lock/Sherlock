package com.srp.sher_lock_remote;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rishi on 1/5/15.
 */
public class background_activity extends IntentService{


    String resp;
    String flag;
    private static final int UNIQUE_ID = 882;
    protected static String url = "http://rishikesh.byethost5.com//notificationget.php";
    Context mContext = null;

    int fflag=0;

    public background_activity() {
        super("background_activity");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
    try {
       // mContext = getBaseContext();
      //  new getnotification(mContext).execute();
    }
    catch(Exception e)
    {
        Log.e("=============","nooooooooooooooooooooooo"+e);
    }

    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("i am in service","wowowow");

    }
    /*
    private class getnotification extends AsyncTask<Void,String, Integer> {
        getnotification(Context context)
        {
            mContext=context;
        }
        protected void onPreExecute() {
          //  Toast.makeText(getApplicationContext(), "Starting",
            //        Toast.LENGTH_LONG).show();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {

                flag="\"1\"";
             HttpClient httpclient=new DefaultHttpClient();
             HttpGet get = new HttpGet(url);
            System.out.println(get);
            HttpResponse response=httpclient.execute(get);
            System.out.println(response.toString());
            HttpEntity entity=response.getEntity();
            resp= EntityUtils.toString(entity);
            System.out.println(resp);
            //	isr=entity.getContent();
            System.out.println("\n\n\nhello\n\n\n");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            try
            {
                String s="";
               // JSONObject json=new JSONObject();
                //JSONArray jarray=new JSONArray(resp);
                Log.d("json value=========",""+resp.toString());
                Log.d("flag value=========",""+flag);
                //for(int i=0;i<jarray.length();i++)
                //{
                    //System.out.println("i am here 1"+email.getText().toString()+" "+password.getText().toString());

                    //json=jarray.getJSONObject(i);
                    if(flag.equals(resp.toString()))//json.getString("flag").toString()))
                    {
                        Log.d("====================","What the hell");
                         return 1;
                    }

                //}

            }
            catch(Exception e)
            {
                Log.e("log_tag", "error parsing data" + e.toString());
            }
            return 0;
        }


        protected void onPostExecute(Integer ffla) {

            if(ffla==1)
            {
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification = new Notification(android.R.mipmap.sym_def_app_icon,"Hello from service", System.currentTimeMillis());
                notification.defaults |= Notification.DEFAULT_SOUND;
                Intent intent = new Intent(background_activity.this, MainActivity.class);
                notification.setLatestEventInfo(background_activity.this, "PC at risk", "Somebody just logged into your PC",PendingIntent.getActivity(background_activity.this, 1, intent, 0));
                manager.notify(111, notification);
                callService();
            }
             else
            {
                    Log.d("========","notification not called");
            }
        }
*/
        public void callService()
        {
         //   new getnotification(mContext).execute();
        }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                try {
                        mContext = getBaseContext();
                        flag = "\"1\"";
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpGet get = new HttpGet(url);
                        System.out.println(get);
                        HttpResponse response = httpclient.execute(get);
                        System.out.println(response.toString());
                        HttpEntity entity = response.getEntity();
                        resp = EntityUtils.toString(entity);
                        System.out.println(resp);
                        //	isr=entity.getContent();
                        System.out.println("\n\n\nhello\n\n\n");

                        String s = "";
                        // JSONObject json=new JSONObject();
                        //JSONArray jarray=new JSONArray(resp);
                        Log.d("json value=========", "" + resp.toString());
                        Log.d("flag value=========", "" + flag);
                        //for(int i=0;i<jarray.length();i++)
                        //{
                        //System.out.println("i am here 1"+email.getText().toString()+" "+password.getText().toString());

                        //json=jarray.getJSONObject(i);
                        if (flag.equals(resp.toString()))//json.getString("flag").toString()))
                        {
                            Log.d("====================", "What the hell");
                            fflag = 1;
                        }

                    if(fflag==1)
                    {
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notification = new Notification(android.R.mipmap.sym_def_app_icon,"Hello from service", System.currentTimeMillis());
                        notification.defaults |= Notification.DEFAULT_SOUND;
                        Intent intent = new Intent(background_activity.this, MainActivity.class);
                        notification.setLatestEventInfo(background_activity.this, "PC at risk", "Somebody just logged into your PC",PendingIntent.getActivity(background_activity.this, 1, intent, 0));
                        manager.notify(111, notification);
                        //callService();
                        fflag=0;
                    }
                    else
                    {
                        Log.d("========","notification not called");
                    }

                    Thread.sleep(5000);
                }
                catch (Exception r)
                    {
                        r.printStackTrace();
                    }
                }

            }

        }).start();
        //Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        //return START_STICKY;
        return 0;
    }
}

