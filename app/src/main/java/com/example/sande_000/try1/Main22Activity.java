package com.example.sande_000.try1;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main22Activity extends AppCompatActivity {

    //TextView textView;
    Intent intent, share;
    public final int LENGTH_LONG = 5;
    File traceFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        Button yourButton = (Button) findViewById(R.id.createEventBtn);
        Button sharingButton = (Button) findViewById(R.id.shareButton);
        //textView = (TextView) findViewById(R.id.introduction);
        intent = new Intent(Main22Activity.this, MainActivity.class);
        traceFile = new File(Main22Activity.this.getExternalFilesDir(null), "TraceFile.txt");
        share = new Intent(Intent.ACTION_SEND);
        yourButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean pass = true;
                String  result="";
                do {
                    String name = "", date = "", time = "", info = "", location = "";
                    EditText editName = (EditText) findViewById(R.id.editText);
                    name = editName.getText().toString().toUpperCase();
                    if(name.length()==0)
                        pass=false;
                    EditText editLocation = (EditText) findViewById(R.id.editText5);
                    location = editLocation.getText().toString();
                    EditText editDate = (EditText) findViewById(R.id.editText2);
                    date = editDate.getText().toString();
                    if (date.indexOf('/') == -1)
                        pass=false;
                    EditText editTime = (EditText) findViewById(R.id.editText3);
                    time = editTime.getText().toString();
                    if (time.indexOf(':') == -1)
                        pass=false;
                    EditText editDesc = (EditText) findViewById(R.id.editText4);
                    info = editDesc.getText().toString();
                    if (pass) {
                        result = "\n\n" + name + " (" + date + " at " + time + ") at " + location + ":\n" + info;
                        share.setType(result);
                        //share.putExtra(Intent.EXTRA_TEXT, result);//might not need
                        try{
                            // Creates a trace file in the primary external storage space of the
                            // current application.
                            // If the file does not exists, it is created.
                            File traceFile = new File(Main22Activity.this.getExternalFilesDir(null), "EventFile.txt");
                            if (!traceFile.exists())
                                traceFile.createNewFile();
                            // Adds a line to the trace file
                            BufferedWriter writer = new BufferedWriter(new FileWriter(traceFile, true /*append*/));
                            writer.write(result);
                            writer.close();
                        }
                        catch (IOException e)
                        {
                            Log.e("com.sande_000.Try1", "Unable to write to the TraceFile.txt file.");
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "The details you entered are invalid.",
                                Toast.LENGTH_LONG).show();
                        pass=true;
                    }
                }while(!pass);
                    //textView.append(result);
                CurrentEvent.getInstance().add(result);
                startActivity(intent);
            }
        });
        sharingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //boolean pass = true;
                String  result="";
                //do {
                    String name = "", date = "", time = "", info = "", location = "";
                    EditText editName = (EditText) findViewById(R.id.editText);
                    name = editName.getText().toString().toUpperCase();
                    //if(name.length()==0)
                        //pass=false;
                    EditText editLocation = (EditText) findViewById(R.id.editText5);
                    location = editLocation.getText().toString();
                    EditText editDate = (EditText) findViewById(R.id.editText2);
                    date = editDate.getText().toString();
                    //if (date.indexOf('/') == -1)
                        //pass=false;
                    EditText editTime = (EditText) findViewById(R.id.editText3);
                    time = editTime.getText().toString();
                    //if (time.indexOf(':') == -1)
                        //pass=false;
                    EditText editDesc = (EditText) findViewById(R.id.editText4);
                    info = editDesc.getText().toString();
                    //if (pass) {
                        result = "\n\n" + name + " (" + date + " at " + time + ") at " + location + ":\n" + info;
                        share.setType("text/plain");
                        //share.putExtra(Intent.EXTRA_TEXT, result);//might not need
                    //}
                    //else{
                        //Toast.makeText(getApplicationContext(), "The details you entered are invalid.",
                                //Toast.LENGTH_LONG).show();
                        //pass=true;
                    //}
                //}while(!pass);
                shareIt(result);
            }
        });
    }
    private void shareIt(String message){
        share.putExtra(android.content.Intent.EXTRA_SUBJECT, "You've been invited to my event!");
        share.putExtra(android.content.Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "Share via"));
    }
}
