package prankur.tapgames;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TapGamesHome extends AppCompatActivity implements View.OnClickListener{

    ImageView tapme,tapout;
    int pres = 0;
    private Toolbar toolbarMain;
    TextView    activityName;
    SQLiteDatabase sd1, sd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_games_home);
        System.gc();
        tapme = (ImageView)findViewById(R.id.tapmeselect);
        tapout = (ImageView)findViewById(R.id.tapoutselect);
        toolbarMain = (Toolbar) findViewById(R.id.app_barMain);
        activityName = (TextView) findViewById(R.id.activity_name);
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Bold.otf");
        toolbarMain.setNavigationIcon(null);
        activityName.setTypeface(tf2);
        activityName.setText("        Tap Games");
        setSupportActionBar(toolbarMain);
        tapme.setOnClickListener(this);
        tapout.setOnClickListener(this);
        sd1 = openOrCreateDatabase("TAPOUT", MODE_PRIVATE,null);
        sd2 = openOrCreateDatabase("TAPME", MODE_PRIVATE,null);
        sd1.execSQL("create table if not exists hs1(highscore int(10) not null)");
        sd2.execSQL("create table if not exists hs2(highscore int(10) not null)");
        int temp = 0;
        sd1.execSQL("insert into hs1 values(" + temp + ")");
        sd1.execSQL("insert into hs1 values(" + temp + ")");
        sd1.execSQL("insert into hs1 values(" + temp + ")");
        sd1.execSQL("insert into hs1 values(" + temp + ")");
        sd1.execSQL("insert into hs1 values(" + temp + ")");
        sd1.execSQL("insert into hs1 values(" + temp + ")");
        sd1.execSQL("insert into hs1 values(" + temp + ")");
        sd1.execSQL("insert into hs1 values(" + temp + ")");
        sd1.execSQL("insert into hs1 values(" + temp + ")");
        sd1.execSQL("insert into hs1 values(" + temp + ")");

        sd2.execSQL("insert into hs2 values(" + temp + ")");
        sd2.execSQL("insert into hs2 values(" + temp + ")");
        sd2.execSQL("insert into hs2 values(" + temp + ")");
        sd2.execSQL("insert into hs2 values(" + temp + ")");
        sd2.execSQL("insert into hs2 values(" + temp + ")");
        sd2.execSQL("insert into hs2 values(" + temp + ")");
        sd2.execSQL("insert into hs2 values(" + temp + ")");
        sd2.execSQL("insert into hs2 values(" + temp + ")");
        sd2.execSQL("insert into hs2 values(" + temp + ")");
        sd2.execSQL("insert into hs2 values(" + temp + ")");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_developers) {
            Intent i = new Intent(this, Developers.class);
            startActivity(i);
            return true;
        }else if (id == R.id.action_exit) {
            finish();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.tapmeselect:
                Intent inte = new Intent(this, TapMeSplash.class);
                finish();
                startActivity(inte);
                break;
            case R.id.tapoutselect:
                Intent inten = new Intent(this, TapOutSplash.class);
                finish();
                startActivity(inten);
                break;
            default:
                pres = 0;
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (pres == 0) {
            Toast.makeText(TapGamesHome.this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
            pres = 1;
        } else if (pres == 1) {
            super.onBackPressed();
        }
    }
}
