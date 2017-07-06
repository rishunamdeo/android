package com.example.rishu.mike;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Remove extends AppCompatActivity {
EditText remove;
    Button delete,confirm;
    String get;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        remove=(EditText)findViewById(R.id.remove);

        delete= (Button) findViewById(R.id.delete);


        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                get=remove.getText().toString();

           //     Toast.makeText(Remove.this, get, Toast.LENGTH_SHORT).show();
                SQLiteDatabase db = openOrCreateDatabase("data", MODE_PRIVATE, null);
                String str = "delete from medicine where mname='" + get + "'";


                db.execSQL(str);
                db.close();
             //   Toast.makeText(Remove.this, str, Toast.LENGTH_SHORT).show();

            }
        });
        confirm= (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Remove.this, "Medicine Removed", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });


    }
}
