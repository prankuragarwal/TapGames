package prankur.tapgames;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TapOutHome extends AppCompatActivity implements View.OnClickListener{
    TextView activityName;
    TextView pl;
    ImageView mainlogo;
    int pressed = 0;
    private Toolbar toolbarMain;
    LinearLayout play;
    ImageView abtus;
    private ScrollView abtsc;
    int abtOpened = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap_out_home);
        System.gc();
        abtsc = (ScrollView) findViewById(R.id.abtscroll);
        abtus = (ImageView) findViewById(R.id.abtclick);
        mainlogo = (ImageView) findViewById(R.id.mainlogo);
        toolbarMain = (Toolbar) findViewById(R.id.app_barMain);
        TextView textview1 = (TextView) findViewById(R.id.effeText);
        activityName = (TextView) findViewById(R.id.activity_name);
        play =(LinearLayout) findViewById(R.id.pp);
        pl = (TextView) findViewById(R.id.play);
        Typeface tf1 = Typeface.createFromAsset(getAssets(),
                "OneDirection.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Bold.otf");
        Typeface tf3 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Regular.otf");
        textview1.setTypeface(tf3);
        pl.setTypeface(tf3);
        toolbarMain.setNavigationIcon(null);
        activityName.setTypeface(tf2);
        activityName.setText("            Tapout");
        setSupportActionBar(toolbarMain);
        play.setOnClickListener(this);
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
            Intent i = new Intent(this, Highscores.class);
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
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.abtclick:
                if (abtOpened == 0) {
                    abtOpened = 1;
                    abtsc.setVisibility(View.VISIBLE);
                    play.setVisibility(View.GONE);
                    mainlogo.setVisibility(View.GONE);
                    abtus.setImageResource(R.mipmap.closecross);
                } else {
                    abtOpened = 0;
                    mainlogo.setVisibility(View.VISIBLE);
                    abtsc.setVisibility(View.GONE);
                    play.setVisibility(View.VISIBLE);
                    abtus.setImageResource(R.mipmap.howtoplay);
                }
                break;
            case R.id.pp:
                Intent inte = new Intent(this, TapOutPlayGame.class);
                startActivity(inte);
                break;
            default:
                pressed = 0;
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if (pressed == 0) {
            Toast.makeText(TapOutHome.this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
            pressed = 1;
        } else if (pressed == 1) {
            super.onBackPressed();
        }
    }
}