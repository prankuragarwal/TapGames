package prankur.tapgames;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Highscore extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView mList;

    String[] score;
    int[] images;
    int[] temp;
    private TextView activityName;
    SQLiteDatabase sd2;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature((Window.FEATURE_ACTION_BAR_OVERLAY));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        System.gc();
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mList = (ListView)findViewById(R.id.listView);
        //mList.addHeaderView(Highscores.this.getLayoutInflater().inflate(R.layout.listview_header,null));

        score = new String[10];
        temp = new int[100000];
        //score = new String[] {"Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"};
        images = new int[]{R.mipmap.oonee, R.mipmap.twoo, R.mipmap.threee, R.mipmap.fourr, R.mipmap.five,
                R.mipmap.six, R.mipmap.seven, R.mipmap.eight, R.mipmap.nine, R.mipmap.ten};


        Typeface tf1 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Bold.otf");
        activityName = (TextView) findViewById(R.id.activity_name);
        activityName.setTypeface(tf1);
        activityName.setText("Highscores");
        sd2 = openOrCreateDatabase("TAPME", MODE_PRIVATE,null);
        sd2.execSQL("create table if not exists hs2(highscore int(10) not null)");
        c = sd2.rawQuery("select * from hs2", null);
        int t = 0;
        if (c != null) {
            c.moveToFirst();
            temp[t++] = Integer.parseInt(c.getString(0));
            while (c.moveToNext()){
                temp[t++] = Integer.parseInt(c.getString(0));
            }
        }
        int i, j, tt;
        for (i = 0; i < t - 1; i++){
            for (j = i + 1; j < t; j++){
                if (temp[i] < temp[j]){
                    tt = temp[i];
                    temp[i] = temp[j];
                    temp[j] = tt;
                }
            }
        }
        for (i = 0; i < 10; i++){
            if (temp[i] != 0)
                score[i] = String.valueOf(temp[i]);
            else
                score[i] = "Empty";
        }
        initList(score, images);


    }

    public void initList(String[] eventsArray, int[] imagesList) {
        if(eventsArray.length != 0) {

            ArrayList<HashMap<String, String>> eventList = new ArrayList<HashMap<String, String>>();

            for(int i = 0; i < eventsArray.length; i++) {
                HashMap<String, String> candy = new HashMap<String, String>();
                candy.put("event", eventsArray[i]);
                candy.put("image", Integer.toString(imagesList[i]));
                eventList.add(candy);
            }

            ListAdapter adapter = new SimpleAdapter(
                    Highscore.this ,
                    eventList,
                    R.layout.highscore_list_design,
                    new String[] { "event", "image"},
                    new int[] { R.id.list_msg, R.id.lis_icon}) {
                @Override
                public View getView(final int position, View convertView, ViewGroup parent) {
                    final View view = super.getView(position, convertView, parent);
                    Typeface tf = Typeface.createFromAsset(getAssets(), "MyriadPro-Light.ttf");
                    TextView item_name = (TextView)view.findViewById(R.id.list_msg);

                    item_name.setTypeface(tf);

                    return view;
                }
            };

            mList.setAdapter(adapter);
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_developers);
        item.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_highscore, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
