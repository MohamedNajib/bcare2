package com.emedia.bcare.services;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.emedia.bcare.Config.BCareApp;
import com.emedia.bcare.R;

public class NotificationReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        playNotificationSound(context);
    }

    public void playNotificationSound(Context context) {
        try {
            //Ringtone r = RingtoneManager.
            //        getRingtone(context, Uri.parse("android.resource://" + BCareApp.getInstance().getContext().getPackageName()
            //                + "/"
            //                + R.raw.smack));
            //r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}