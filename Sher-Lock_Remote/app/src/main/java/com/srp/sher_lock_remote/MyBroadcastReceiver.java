package com.srp.sher_lock_remote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by rishi on 1/5/15.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                   //     Intent myIntent = new Intent(context,background_activity.class);
                     //   context.startService(myIntent);

                }

            catch(Exception r) {
                Log.e("What the hell", " " +r.toString());
            }
        }
    }

