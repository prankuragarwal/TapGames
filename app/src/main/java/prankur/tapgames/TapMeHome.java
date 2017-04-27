package prankur.tapgames;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class TapMeHome extends AppCompatActivity implements View.OnClickListener{

    TextView activityName, howto, play;
    private Toolbar toolbarMain;
    int pressed = 0;
    int abtOpened = 0;
    ImageView abtus;
    ImageView mainlogo;
    private ScrollView abtsc;
    LinearLayout playll, howtoplayll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap_me_home);
        System.gc();
        abtsc = (ScrollView) findViewById(R.id.abtscroll);
        abtus = (ImageView) findViewById(R.id.howttoplayll);
        mainlogo = (ImageView) findViewById(R.id.mainlogoo);
        toolbarMain = (Toolbar) findViewById(R.id.app_barMain);
        activityName = (TextView) findViewById(R.id.activity_name);
        Typeface tf1 = Typeface.createFromAsset(getAssets(),
                "OneDirection.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Bold.otf");
        Typeface tf3 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Regular.otf");
        play = (TextView)findViewById(R.id.playtxt);
        activityName.setTypeface(tf2);
        play.setTypeface(tf3);
        toolbarMain.setNavigationIcon(null);
        activityName.setTypeface(tf2);
        activityName.setText(" ");
        setSupportActionBar(toolbarMain);
        playll = (LinearLayout)findViewById(R.id.playll);
        playll.setOnClickListener(this);
        abtus.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainnn, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_highscore) {
            Intent i = new Intent(this, Highscore.class);
            startActivity(i);
            return true;
        } else if (id == R.id.action_choose) {
            Intent i = new Intent(this, TapGamesHome.class);
            finish();
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (pressed == 0) {
            Toast.makeText(TapMeHome.this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
            pressed = 1;
        } else if (pressed == 1) {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.playll:
                Intent inte = new Intent(this, TapMePlay.class);
                startActivity(inte);
                break;
            case R.id.howttoplayll:
                if (abtOpened == 0) {
                    abtOpened = 1;
                    abtsc.setVisibility(View.VISIBLE);
                    playll.setVisibility(View.GONE);
                    mainlogo.setVisibility(View.GONE);
                    abtus.setImageResource(R.mipmap.closecross);
                } else {
                    abtOpened = 0;
                    mainlogo.setVisibility(View.VISIBLE);
                    abtsc.setVisibility(View.GONE);
                    playll.setVisibility(View.VISIBLE);
                    abtus.setImageResource(R.mipmap.howtoplay);
                }
                break;
            default:
                pressed = 0;
                break;
        }
    }
}
