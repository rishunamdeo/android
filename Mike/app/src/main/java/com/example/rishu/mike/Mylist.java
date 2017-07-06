package com.example.rishu.mike;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;

public class Mylist extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylist);

        lv= (ListView) findViewById(R.id.listView);
        try {
            SQLiteDatabase db = openOrCreateDatabase("data", MODE_PRIVATE, null);
            String sql = "select * from medicine";
            Cursor c = db.rawQuery(sql, null);
            int in1 = c.getColumnIndex("mname");
            int in2 = c.getColumnIndex("s_date");
            int in3 = c.getColumnIndex("e_date");
            int in4 = c.getColumnIndex("dosage");
            int in5 = c.getColumnIndex("desc");
            LinkedList<String> res = new LinkedList<>();
            int i = 1;
            while (c.moveToNext()) {
                String name = c.getString(in1);
                String sdate = c.getString(in2);
                String edate = c.getString(in3);
                String desc = c.getString(in5);
                String time = c.getString(in4);
                res.add(i + " - " + name + " \n" + sdate + " \n" + edate + "\n" + time + "\n" + desc);
                i++;
            }
            ArrayAdapter<String> aa = new ArrayAdapter<String>(Mylist.this, android.R.layout.simple_list_item_1, res);
            lv.setAdapter(aa);
            db.close();
            Toast.makeText(Mylist.this, "Mylist", Toast.LENGTH_SHORT).show();

        //    Mylist.this.finish();
        }
        catch (Exception e)
        {
            Toast.makeText(Mylist.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
