package com.example.sande_000.try1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    //String addition;
    TextView textView;
    CurrentEvent currentEvent;
    File traceFile;
    Date date = new Date();
    String newText, dateString=date.toString(), currentMonth;
    int month=13, day=32, currentDay;
    long currentMonthInt;
    NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.icon)
            .setContentTitle("Event coming up!")
            .setContentText("Open app for details.");;
    int mNotificationId = 001;

    public void showNotification() {
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

    @Override
    protected void onResume() {
        super.onResume();
        intent = getIntent();
        /*if(intent.hasExtra("addition")){
            addition = intent.getExtras().getString("addition");
            currentEvent.add(addition);
            intent.removeExtra("addition");
        }*/
        String output="";
        for(int i = 0; i < currentEvent.getEvents().size(); i++){
            output += currentEvent.getEvents().get(i);
        }
        String originalOutput = textView.getText().toString();
        if(originalOutput.indexOf(output) == -1)
            textView.setText(originalOutput + output);
        /*try{
            File traceFile = new File(((Context)this).)
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        traceFile = new File(MainActivity.this.getExternalFilesDir(null), "EventFile.txt");
        currentEvent = CurrentEvent.getInstance();
        textView = (TextView) findViewById(R.id.introduction);
        for(int x=0; x<convertToString().length()-2; x++){
            if(convertToString().charAt(x)=='/') {
                month = Integer.parseInt(convertToString().substring(x - 2, x));
                day = Integer.parseInt(convertToString().substring(x + 1, x + 3));
                currentMonth = dateString.substring(4, 7);
                switch(currentMonth){
                    case "Jan":
                        currentMonthInt=1;
                        break;
                    case "Feb":
                        currentMonthInt=2;
                        break;
                    case "Mar":
                        currentMonthInt=3;
                        break;
                    case "Apr":
                        currentMonthInt=4;
                        break;
                    case "May":
                        currentMonthInt=5;
                        break;
                    case "Jun":
                        currentMonthInt=6;
                        break;
                    case "Jul":
                        currentMonthInt=7;
                        break;
                    case "Aug":
                        currentMonthInt=8;
                        break;
                    case "Sep":
                        currentMonthInt=9;
                        break;
                    case "Oct":
                        currentMonthInt=10;
                        break;
                    case "Nov":
                        currentMonthInt=11;
                        break;
                    case "Dec":
                        currentMonthInt=12;
                        break;
                }
                currentDay = Integer.parseInt(dateString.substring(8, 10));
                if(currentMonthInt==(long)month && currentDay==day){
                    showNotification();
                }
                /*if (Math.abs(Integer.parseInt(convertToString().substring(x - 2, x))-Integer.parseInt(date.substring(5, 7))) < month) {
                    if (Math.abs(Integer.parseInt(convertToString().substring(x + 1, x + 3))-Integer.parseInt(date.substring(8, 10))) < day) {
                        month = Integer.parseInt(convertToString().substring(x - 2, x));
                        day = Integer.parseInt(convertToString().substring(x + 1, x + 3));
                    }
                }*/
            }
        }
        /*if(Integer.parseInt(date.substring(5, 7)) == month && Integer.parseInt(date.substring(8, 10)) == day)
            showNotification();*/
        newText = "Hello there! Create an event by tapping the plus icon at on the bottom of the screen. Once you have created an event you can invite your friends to it!" + convertToString();
        textView.setText(newText);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main22Activity.class));
                finish();
            }
        });
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

    private String convertToString(){
        Scanner input = null;
        String output="";
        try {
            input = new Scanner(traceFile);
            while(input.hasNextLine()){
                output+=(input.nextLine()+"\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return output;
    }
}
