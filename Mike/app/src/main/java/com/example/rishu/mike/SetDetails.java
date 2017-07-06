package com.example.rishu.mike;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;

public class SetDetails extends AppCompatActivity {
    EditText et1,sd,ed,noti;
    Button b1;
    Spinner sp;
    String mediName;
    Calendar myCalendar,myCalendar1;
    String s1;
    String []ar={"Take before meal","Take after meal","Take on an empty stomach","Take with water","Never take with milk","Avoid Sugars","Avoid salty food","avoid fatty food","Eat more vegetables","Eat more iron-rich foods"};


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_details);
        et1 = (EditText) findViewById(R.id.editText1);
        sd = (EditText) findViewById(R.id.sd);
        ed = (EditText) findViewById(R.id.ed);
        noti = (EditText) findViewById(R.id.noti);
        b1 = (Button) findViewById(R.id.btn);
        sp = (Spinner) findViewById(R.id.sp1);
        Bundle bundle = getIntent().getExtras();
        mediName = bundle.getString("mname");
        ArrayAdapter<String> obj2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ar);
        obj2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(obj2);

        myCalendar = Calendar.getInstance();
        myCalendar1 = Calendar.getInstance();


        String notify=noti.getText().toString();
        NotificationCompat.Builder builder=new  NotificationCompat.Builder(SetDetails.this);
            builder.setSmallIcon(R.drawable.c);
          builder.setContentTitle(notify);
        builder.setContentText(notify);
           Intent i=new Intent(SetDetails.this,MainActivity.class);
        TaskStackBuilder stackBuilder=TaskStackBuilder.create(SetDetails.this);
       stackBuilder.addParentStack(MainActivity.class);
       stackBuilder.addNextIntent(i);
        PendingIntent pi=stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pi);

        NotificationManager nm= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(5,builder.build());



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {

                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                sd.setText(sdf.format(myCalendar.getTime()));
            }

        };
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, monthOfYear);
                myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel1();
            }

            private void updateLabel1() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                ed.setText(sdf.format(myCalendar.getTime()));
            }

        };

        sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SetDetails.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SetDetails.this, date1, myCalendar1.get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                        myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDB();
                Intent i2 = new Intent(SetDetails.this, Mylist.class);
                startActivity(i2);
            }

            private void insertDB() {
                s1 = sp.getSelectedItem().toString();
                //Toast.makeText(SetDetails.this, s1, Toast.LENGTH_SHORT).show();
                SQLiteDatabase db=openOrCreateDatabase("data", MODE_PRIVATE, null);
                String insert = "insert into medicine(mname , s_date , e_date , dosage , desc) values (? , ? , ? , ? , ?) ";
                Object oa[] = new Object[5];
                oa[0] = mediName;
                oa[1] = sd.getText().toString();
                oa[2] = ed.getText().toString();
                oa[3] = et1.getText().toString();
                oa[4] = s1;
                db.execSQL(insert, oa);
                db.close();
                SetDetails.this.finish();


            }
        });
    }
}
