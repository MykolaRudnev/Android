package com.example.mykola.alarmclock;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;


/**
 * Created by Mykola on 12/9/2016.
 */

public class RingtonPlayService extends Service {

    MediaPlayer media_song;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {return null;}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id ");


        //fetch  the  extra string from the alarm on/alarm off values
        String state=intent.getExtras().getString("extra");
        //fetch the vhale choice integer values

        Integer whale_sound_choice=intent.getExtras().getInt("whale_choice");

        Log.e("Ringtone extra is",state);
        Log.e("Whale choice is",whale_sound_choice.toString());


        //this comrnts the extra wtring from the integer
        //to  start IDs valuer 0 or 1;
        // notification
        //set up the notification service
        NotificationManager notification_Manager=(NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        //set ip an intent that goes in the Main Activiti
        Intent intent_main_activity=new Intent(this.getApplicationContext(),MainActivity.class);

        PendingIntent pending_intent_activity = PendingIntent.getActivity(this,0
                ,intent_main_activity,0);

        Notification notification_popup= new Notification.Builder(this)
                .setContentTitle("An alarm is going off" + "!")
                .setContentText("Click me!")
                .setContentIntent(pending_intent_activity)
                .setAutoCancel(true)
                .build();


        Log.e("Rington state:extra is ",state);

        assert state !=null;
        switch (state) {
            case "Alarm on ":
                startId = 1;
                break;

            case "Alarm_off":
                startId = 0;
                Log.e("Start ID  is",state);
                break;

            default:
                startId = 0;
                break;
        }
        //if else statement

        //if there is no music playing , and the user pressed 'alarm on"
        //music should start playing ;

        if(!this.isRunning &&startId==1)
        {
            Log.e("there is  music, ", "and you wont start ");
            //create an instance of the media player
            this.isRunning = true;
            this.startId=0;

            //set up notification
            notification_Manager.notify(0,notification_popup);



            // play the whale sound depending on the passed whale choice id

            if (whale_sound_choice==0) {
                int minimum_number = 1;
                int maximum_number=7;

                Random random_number = new Random();
                int whale_number=random_number.nextInt(maximum_number+minimum_number);
                Log.e("random number is ",String.valueOf(whale_number));


                if (whale_number==1){
                    media_song = MediaPlayer.create(this, R.raw.beck_dreams);
                    media_song.start();
                }
                else if(whale_number==2){
                    media_song = MediaPlayer.create(this, R.raw.brevis_boss);
                    media_song.start();
                }
                else if(whale_number==3){

                }
                else if(whale_number==4){
                    media_song = MediaPlayer.create(this, R.raw.jaykode_tension);
                    media_song.start();
                }
                else if(whale_number==5){
                    media_song = MediaPlayer.create(this, R.raw.katatonia_onward_into_battle);
                    media_song.start();
                }
                else if(whale_number==6){
                    media_song = MediaPlayer.create(this, R.raw.lets_be_friends_ftw);
                    media_song.start();

                }
                else if(whale_number==7){
                    media_song = MediaPlayer.create(this, R.raw.notixx_superjam);
                    media_song.start();
                }

            }
            else if (whale_sound_choice==1){

                //play a random picked audio file
                media_song = MediaPlayer.create(this, R.raw.beck_dreams);
                //start the  ringtone
                media_song.start();
            }
            else if (whale_sound_choice==2){
                media_song = MediaPlayer.create(this, R.raw.brevis_boss);
                media_song.start();

            }
            else if (whale_sound_choice==3){
                media_song = MediaPlayer.create(this, R.raw.ewan_dobson);
                media_song.start();


            }
            else if (whale_sound_choice==4){
                media_song = MediaPlayer.create(this, R.raw.jaykode_tension);
                media_song.start();


            }
            else if (whale_sound_choice==5){
                media_song = MediaPlayer.create(this, R.raw.katatonia_onward_into_battle);
                media_song.start();

            }
            else if (whale_sound_choice==6){
                media_song = MediaPlayer.create(this, R.raw.lets_be_friends_ftw);
                media_song.start();

            }
            else if (whale_sound_choice==7){
                media_song = MediaPlayer.create(this, R.raw.notixx_superjam);
                media_song.start();

            }
            else
            {

            }


        }
        //if there is music playing , and the   user pressed "alarm off'
        //music should stop playing;
        else
        if(this.isRunning && startId==0)
        {
            Log.e("there is  music, ", "and you wont end ");
            //stop the ringtone
            media_song.stop();
            media_song.reset();
            this.isRunning=false;
            this.startId=0;

        }
        // there are if  the users pressed random buttons
        // just to bag-proof app
        // if there is not music playing , and user pressed "alarm off"
        //do hothing
        else
            if(!this.isRunning && startId==0)
            {
                Log.e("there is not music, ", "and you wont start ");

                this.isRunning=false;
                this.startId=0;
        }
        // if there is  music playing , and user pressed "alarm on"
        //do hothing
        else
        if(this.isRunning && startId==1)
        {
            Log.e("there is  music, ", "and you wont start ");


            this.isRunning=true;
            this.startId=1;
        }
        //can"t think of anything else just to catch the add even
        else
        {
            Log.e("else","somehow you reached this");
        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        // Tell the user we stopped.
        Toast.makeText(this,"on Destroy colled ", Toast.LENGTH_SHORT).show();
    }
}


