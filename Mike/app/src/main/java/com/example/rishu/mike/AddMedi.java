package com.example.rishu.mike;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddMedi extends AppCompatActivity {
EditText et;
Button b1,prev,next;
    TextView txt;
int i=0;
    String medicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medi);
        setdb();
        et=(EditText)findViewById(R.id.editText);
        b1=(Button)findViewById(R.id.button);
        prev=(Button)findViewById(R.id.prev);
        next=(Button)findViewById(R.id.next);
        txt= (TextView) findViewById(R.id.txt);
        final ImageView iv=(ImageView)findViewById(R.id.imageView);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicine = et.getText().toString();
                    //Toast.makeText(AddMedi.this, medicine, Toast.LENGTH_SHORT).show();

                    Intent i1 = new Intent(AddMedi.this, SetDetails.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mname", medicine);
                    i1.putExtras(bundle);
                    startActivity(i1);
                    AddMedi.this.finish();
                }
        });


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                --i;
                if(i<1)
                {
                    i=10;
                }
                String name = "img" + i;
                int rid = getResources().getIdentifier(name, "drawable", getPackageName());
                iv.setImageResource(rid);
                settxt(name);

            }

            private void settxt(String name) {


                switch (name)
                {
                    case "img1":
                          txt.setText("Capsule");
                          break;
                    case "img2":
                        txt.setText("Drop");
                        break;
                    case "img3":
                        txt.setText("First Aid");
                        break;
                    case "img4":
                        txt.setText("Lotion");
                        break;
                    case "img5":
                        txt.setText("Injection");
                        break;
                    case "img6":
                        txt.setText("Mouth Freshner");
                        break;
                    case "img7":
                        txt.setText("Tablet");
                        break;
                    case "img8":
                        txt.setText("Spray");
                        break;
                    case "img9":
                        txt.setText("Tube");
                        break;
                    case "img10":
                        txt.setText("Inhaler");
                        break;
                }
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ++i;
                if(i>10)
                {
                    i=1;
                }
                String name = "img" + i;
                int rid = getResources().getIdentifier(name, "drawable", getPackageName());
                iv.setImageResource(rid);
                settxt(name);


            }

            private void settxt(String name) {


                switch (name)
                {
                    case "img1":
                        txt.setText("Capsule");
                        break;
                    case "img2":
                        txt.setText("Drop");
                        break;
                    case "img3":
                        txt.setText("First Aid");
                        break;
                    case "img4":
                        txt.setText("Lotion");
                        break;
                    case "img5":
                        txt.setText("Injection");
                        break;
                    case "img6":
                        txt.setText("Mouth Freshner");
                        break;
                    case "img7":
                        txt.setText("Tablet");
                        break;
                    case "img8":
                        txt.setText("Spray");
                        break;
                    case "img9":
                        txt.setText("Tube");
                        break;
                    case "img10":
                        txt.setText("Inhaler");
                        break;
                }
            }
        });



    }

    private void setdb() {
        SQLiteDatabase db=openOrCreateDatabase("data",MODE_PRIVATE,null);
        String sql= "create table if not exists medicine(id INTEGER PRIMARY KEY AUTOINCREMENT, mname varchar(50), s_date DATE , e_date DATE, dosage varchar(10), desc varchar(50))";
        db.execSQL(sql);
        db.close();

    }



}
