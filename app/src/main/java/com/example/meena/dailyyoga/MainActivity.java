package com.example.meena.dailyyoga;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;


import java.lang.reflect.Field;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TimePicker myTimePicker;
    Button buttonstartSetDialog;
    TextView textAlarmPrompt;
    final static int RQS_1 = 1;
    Uri notification ;
    Ringtone r ;
    boolean alarm = false;
    TimePickerDialog timePickerDialog;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*imageView1 = (ImageView) findViewById(R.id.img1);
        imageView2 = (ImageView) findViewById(R.id.img2);
        imageView3 = (ImageView) findViewById(R.id.img3);
        imageView4 = (ImageView) findViewById(R.id.img4);
        imageView5 = (ImageView) findViewById(R.id.img5);

        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);*/




        textAlarmPrompt = (TextView) findViewById(R.id.alarmprompt);

        buttonstartSetDialog = (Button) findViewById(R.id.startAlaram);
        buttonstartSetDialog.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                textAlarmPrompt.setText("");
                openTimePickerDialog(false);
            }
        });

    }

    private void openTimePickerDialog(boolean is24r) {
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(MainActivity.this,
                onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), is24r);
        timePickerDialog.setTitle("Set Alarm Time");

        timePickerDialog.show();

    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if (calSet.compareTo(calNow) <= 0) {
                // Today Set time passed, count to tomorrow
                calSet.add(Calendar.DATE, 1);
            }
            //TODO investigate :not firing alarm after 24 hours

            setAlarm(calSet);



        }
    };

    private void setAlarm(Calendar targetCal) {

        textAlarmPrompt.setText("\n\n***\n" + "Alarm is set "
                + targetCal.getTime() + "\n" + "***\n");


        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);
        //alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                //  1000 * 60 * 60 * 24, pendingIntent);//TODO investigate :Null Pointerexception at time onset
        //TODO investigate :this is draining on the battery but using setinexactalarm seems to be too inexact proportionally to the interval specified
        //alarm occassionally does not fire
        //issue discussed here: http://stackoverflow.com/questions/8445867/alarmmanager-occasionally-doesnt-fire-alarm?rq=1


    }

    public void display(View v) {
        Intent intent = new Intent(MainActivity.this, DisplayInfo.class);
        String filename = null;
        /*TODO replace swutch statement with a better with generic chunk of code for all image views */
        switch (v.getId()) {
            case R.id.img1:
                Log.i("Image1", "Anulom/vilom");
                filename = "nadi.txt";

                //Start activity one
                break;
            case R.id.img2:
                //Start activiy two
                Log.i("Image1", "Padmasan");
                filename = "padma.txt";
                break;
            case R.id.img3:
                //Start activiy two
                Log.i("Image1", "Padmasan");
                filename = "vipareet.txt";
                break;
            case R.id.img4:
                //Start activiy two
                Log.i("Image1", "Padmasan");
                filename = "dhyan.txt";
                break;
            case R.id.img5:
                //Start activiy two
                Log.i("Image1", "Padmasan");
                filename = "brspine.txt";

                break;
            // Do this for all buttons.
        }
        intent.putExtra("STRING_I_NEED", filename);
        startActivity(intent);
    }
}
//TODO
//Awareness of Breath- Any time All time- reminder with a question
//Vajrasan/Veerasan- Post lunch Post dinner- reminder+timer
//BhrumadhyaDrishti & Nasikagra Drishti- Any time All time- reminder+timer
//Anuloma Viloma- 6am and 6pm- reminder+timer
//Vipareetkarani,pranayam-choice- reminder+timer
//Yoga music optional
//progress tracking
//Personalize
