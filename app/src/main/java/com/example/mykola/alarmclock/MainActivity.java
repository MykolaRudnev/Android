package com.example.mykola.alarmclock;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.R.attr.id;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AlarmManager alarm_manager;
    TimePicker alarm_timePiker;
    TextView update_text;
    Context context;
    PendingIntent pending_intest;
    int choose_vhale_sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context=this;

        //initialize our alarm maneger
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //initialize our timePiker
        alarm_timePiker = (TimePicker) findViewById(R.id.timePicker);

        //initialize our update box
        update_text = (TextView)findViewById(R.id.update_text);

        //create on instance of a calendar
        final Calendar calendar = Calendar.getInstance();

        // create an onClick listener to start the alarm
        final Intent my_intent = new Intent(this.context,Alarm_Receiver.class);




        //create the spinner in the main Ui
        Spinner spinner = (Spinner) findViewById(R.id.My_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.whale_array, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //Set an onclick listener to the onItemSelected method
        spinner.setOnItemSelectedListener(this);






        //initialize start button
        Button alarm_on = (Button) findViewById(R.id.alarm_on);


        alarm_on.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                //setting calendar
                calendar.set(Calendar.HOUR_OF_DAY,alarm_timePiker.getHour());
                calendar.set(Calendar.MINUTE,alarm_timePiker.getMinute());

                //get this string values of the hour and minuts
                int hour=alarm_timePiker.getHour();
                int minute=alarm_timePiker.getMinute();

                //cinwert the int values to string
                String hour_string = String.valueOf(hour);
                String minut_string=String.valueOf(minute);
                if(hour>12){
                    hour_string=String.valueOf(hour-12);
                }
                if(minute<10){
                    minut_string="0"+String.valueOf(minute);
                }
                //method that changes the update text  TextBox
                set_alarm_text("Alarm set to "+hour_string+":"+minut_string);


                //put is extra string into my_intent
                //telle the clock taht you pressed yhe "alarm_on" button
                my_intent.putExtra("extra","alarm on");

                //put in an extra long into my_intemt;
                //talls the clock that you wont a curtain value from the drop-down menu
              //  my_intent.putExtra("Whale_choice",choose_vhale_sound,)
                my_intent.putExtra("whale_choice",choose_vhale_sound);
                Log.e("The whale id is " ,String.valueOf(choose_vhale_sound));

                //create a pending intent that delays the intent
                //until the specifided calendar time
                pending_intest= PendingIntent.getBroadcast(MainActivity.this,0,
                       my_intent,PendingIntent.FLAG_UPDATE_CURRENT );


                // set this alarm maneger
                alarm_manager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                        pending_intest);
            }





        });
        //initialize stop button
        Button alarm_off = (Button) findViewById(R.id.alarm_off);

        //create an onClick listener to stop the alarm or undo alarm set

       alarm_off.setOnClickListener(new View.OnClickListener() {//++++
           @Override
           public void onClick(View view) {
               //method that changes the update text  TextBox
               set_alarm_text("Alarm off");
               //cancelthe alarms
               alarm_manager.cancel(pending_intest);

                //put extra string my_intent
               //telle the clock thatt you preesed the "alarm_of' button
               my_intent.putExtra("extra","alarm off");
               //also put an extra long into the alarm off sectoin
               //to prevent crashes in a Null Poniter Excepcion
               my_intent.putExtra("whale_choice",choose_vhale_sound);

               //stop rington
               sendBroadcast(my_intent);

           }
       });


    }

    private void set_alarm_text(String output) {
        update_text.setText(output);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        //outputing vhatever id the user bas selected
        // Toast.makeText(adapterView.getContext(),"the spinne is "
        // +id, Toast.LENGTH_SHORT).show();
        // choose_vhale_sound= (int)id;





    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
                    // Another interface callback
    }
}
