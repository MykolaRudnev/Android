package com.example.mykola.alarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;



public class Alarm_Receiver  extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {


        //fetca extra string from the intent
        String get_your_string = intent.getExtras().getString("extra");

        Log.e( "What is the key " , get_your_string);

            Integer get_your_whale_choice = intent.getExtras().getInt("whale_choice");
        Log.e("The whale choice is ",  get_your_whale_choice.toString());
        //fetch the extras longe from the intent ;
        // tells us which  the user  picked from  the drop the drop dowa menu/spinner

        //create an intents to the service
        Intent service_intent = new Intent(context,RingtonPlayService.class);


        //pass the extra string from Main Activity to the Ringtone Playing Service
        service_intent.putExtra("extra",get_your_string);
        //pass the extra integer from the Receiver to the Ringtone Play Service

        service_intent.putExtra("Whale_choice",get_your_whale_choice);


        //start the rington service
        context.startService(service_intent);
    }
}
