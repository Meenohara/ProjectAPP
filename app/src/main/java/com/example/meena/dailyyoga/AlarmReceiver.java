package com.example.meena.dailyyoga;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Meena on 23-05-2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    Uri notification;
    Ringtone r;

    @Override
    public void onReceive(Context k1, Intent k2) {
        // TODO Auto-generated method stub
        Toast.makeText(k1, "Alarm received!", Toast.LENGTH_LONG).show();
        AudioManager am = (AudioManager) k1.getSystemService(Context.AUDIO_SERVICE);

        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

//MainActivity.buttonstartSetDialog.getText();

 /*
        switch (am.getRingerMode()) {
            case AudioManager.RINGER_MODE_SILENT:
                Log.i("MyApp", "Silent mode");
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                Log.i("MyApp", "Vibrate mode");
                break;
            case AudioManager.RINGER_MODE_NORMAL:
                Log.i("MyApp", "Normal mode");
                break;
        }*/
///
        if(notification==null||am.getRingerMode()==AudioManager.RINGER_MODE_VIBRATE) {

            final Vibrator v = (Vibrator) k1.getSystemService(Context.VIBRATOR_SERVICE);
            if (v.hasVibrator()) {
                final long[] pattern = {0, 500, 110, 500, 110, 450, 110, 200, 110, 170, 40, 450, 110, 200, 110, 170, 40, 500};
                //start war like theme
                new Thread() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) { //repeat the pattern 5 times
                            v.vibrate(pattern, -1);
                            try {
                                Thread.sleep(4000); //the time, the complete pattern needs
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            }


        }
        else {
            try {

                r = RingtoneManager.getRingtone(k1.getApplicationContext(), notification);
                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
//TODO
//    include file in xml, yoga row
//        putflag in preferences