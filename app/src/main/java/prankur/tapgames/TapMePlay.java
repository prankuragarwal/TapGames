package prankur.tapgames;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import prankur.tapgames.TapMeHome;

public class TapMePlay extends AppCompatActivity implements View.OnClickListener{
    TextView taptxt, activityName;
    private Toolbar toolbar;
    int whee = 1;
    int score = 0;
    SQLiteDatabase sd2;
    TextView scoretxt;
    int prev;
    int curr = whee;
    ImageView wheelimg;
    LinearLayout colbarll1; LinearLayout colbarll2 ; LinearLayout colbarll3 ;
    LinearLayout colbarll4 ; LinearLayout colbarll5 ; LinearLayout colbarll6 ;
    LinearLayout tapll;
    int col = 0;
    int randdcol, randin;
    final Handler mHandler = new Handler();
    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            AnimateandSlideShow();
            mHandler.postDelayed(mUpdateResults, 600);
        }
    };
    final Handler mHandler1 = new Handler();
    final Runnable mUpdateResults1 = new Runnable() {
        public void run() {
            AnimateandSlideShow1();
            mHandler1.postDelayed(mUpdateResults1, 6000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap_me_play);
        System.gc();
        taptxt = (TextView) findViewById(R.id.taptxt);
        Typeface tf1 = Typeface.createFromAsset(getAssets(),
                "OneDirection.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Bold.otf");
        Typeface tf3 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Regular.otf");
        taptxt.setTypeface(tf3);
        tapll = (LinearLayout)findViewById(R.id.tapll);
        tapll.setOnClickListener(this);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        activityName = (TextView) findViewById(R.id.activity_name);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wheelimg = (ImageView)findViewById(R.id.wheelimg);
        colbarll1 = (LinearLayout) findViewById(R.id.colbarll1); colbarll2 = (LinearLayout) findViewById(R.id.colbarll2);
        colbarll3 = (LinearLayout) findViewById(R.id.colbarll3); colbarll4 = (LinearLayout) findViewById(R.id.colbarll4);
        colbarll5 = (LinearLayout) findViewById(R.id.colbarll5); colbarll6 = (LinearLayout) findViewById(R.id.colbarll6);
        scoretxt = (TextView)findViewById(R.id.scoretxt);
        scoretxt.setText("Score: " + score);
        activityName.setTypeface(tf2);
        activityName.setText("         Play");
        sd2 = openOrCreateDatabase("TAPME", MODE_PRIVATE,null);
        sd2.execSQL("create table if not exists hs2(highscore int(10) not null)");
        randin =(int) ((Math.random() * 100) % 6);
        randin++;
        randdcol = randin;
        prev= randin;
        switch(randin) {
            case 6: //yellow
                colbarll6.setVisibility(View.VISIBLE);
                colbarll2.setVisibility(View.GONE);
                colbarll3.setVisibility(View.GONE);
                colbarll4.setVisibility(View.GONE);
                colbarll1.setVisibility(View.GONE);
                colbarll5.setVisibility(View.GONE);
                break;
            case 1: //red
                colbarll1.setVisibility(View.VISIBLE);
                colbarll4.setVisibility(View.GONE);
                colbarll3.setVisibility(View.GONE);
                colbarll2.setVisibility(View.GONE);
                colbarll5.setVisibility(View.GONE);
                colbarll6.setVisibility(View.GONE);
                break;
            case 2: //orange
                colbarll2.setVisibility(View.VISIBLE);
                colbarll3.setVisibility(View.GONE);
                colbarll1.setVisibility(View.GONE);
                colbarll4.setVisibility(View.GONE);
                colbarll5.setVisibility(View.GONE);
                colbarll6.setVisibility(View.GONE);
                break;
            case 3: //lb
                colbarll3.setVisibility(View.VISIBLE);
                colbarll4.setVisibility(View.GONE);
                colbarll2.setVisibility(View.GONE);
                colbarll1.setVisibility(View.GONE);
                colbarll5.setVisibility(View.GONE);
                colbarll6.setVisibility(View.GONE);
                break;
            case 4: //green
                colbarll4.setVisibility(View.VISIBLE);
                colbarll2.setVisibility(View.GONE);
                colbarll3.setVisibility(View.GONE);
                colbarll1.setVisibility(View.GONE);
                colbarll5.setVisibility(View.GONE);
                colbarll6.setVisibility(View.GONE);
                break;
            case 5: //db
                colbarll5.setVisibility(View.VISIBLE);
                colbarll2.setVisibility(View.GONE);
                colbarll3.setVisibility(View.GONE);
                colbarll4.setVisibility(View.GONE);
                colbarll1.setVisibility(View.GONE);
                colbarll6.setVisibility(View.GONE);
                break;
        }

        mHandler.post(mUpdateResults);
        mHandler1.post(mUpdateResults1);
        /*int delay = 10;
        int period = 600;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                mHandler.post(mUpdateResults);
            }
        }, delay, period); //wheel
*/

        /*int delay1 = 100;
        int period1 = 1800;
        timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                mHandler1.post(mUpdateResults1);
            }
        }, delay1, period1); //random color
        */
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_highscore);
        item.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_developers, menu);
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
        } else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void AnimateandSlideShow() {
        if (whee == 1) { //red
            wheelimg.setImageResource(R.mipmap.wone);
            curr = whee;
        }
        else if (whee == 2) { //orange
            wheelimg.setImageResource(R.mipmap.wtwo);
            curr = whee;
        }
        else if (whee == 3) { //lb
            wheelimg.setImageResource(R.mipmap.wthree);
            curr = whee;
        }
        else if (whee == 4) { //green
            wheelimg.setImageResource(R.mipmap.wfour);
            curr = whee;
        }
        else if (whee == 5) { //db
            wheelimg.setImageResource(R.mipmap.wfive);
            curr = whee;
        }
        else if (whee == 6) { //yellow
            wheelimg.setImageResource(R.mipmap.wsix);
            curr = whee;
        }
        whee = whee % 6;
        whee++;
    }

    private void AnimateandSlideShow1() {
        randdcol = (int) ((Math.random() * 100) % 6);
        randdcol++;
        prev = randdcol;
        switch(randdcol) {
            case 6: //yellow
                colbarll6.setVisibility(View.VISIBLE);
                colbarll2.setVisibility(View.GONE);
                colbarll3.setVisibility(View.GONE);
                colbarll4.setVisibility(View.GONE);
                colbarll1.setVisibility(View.GONE);
                colbarll5.setVisibility(View.GONE);
                break;
            case 1: //red
                colbarll1.setVisibility(View.VISIBLE);
                colbarll4.setVisibility(View.GONE);
                colbarll3.setVisibility(View.GONE);
                colbarll2.setVisibility(View.GONE);
                colbarll5.setVisibility(View.GONE);
                colbarll6.setVisibility(View.GONE);
                break;
            case 2: //orange
                colbarll2.setVisibility(View.VISIBLE);
                colbarll3.setVisibility(View.GONE);
                colbarll1.setVisibility(View.GONE);
                colbarll4.setVisibility(View.GONE);
                colbarll5.setVisibility(View.GONE);
                colbarll6.setVisibility(View.GONE);
                break;
            case 3: //lb
                colbarll3.setVisibility(View.VISIBLE);
                colbarll4.setVisibility(View.GONE);
                colbarll2.setVisibility(View.GONE);
                colbarll1.setVisibility(View.GONE);
                colbarll5.setVisibility(View.GONE);
                colbarll6.setVisibility(View.GONE);
                break;
            case 4: //green
                colbarll4.setVisibility(View.VISIBLE);
                colbarll2.setVisibility(View.GONE);
                colbarll3.setVisibility(View.GONE);
                colbarll1.setVisibility(View.GONE);
                colbarll5.setVisibility(View.GONE);
                colbarll6.setVisibility(View.GONE);
                break;
            case 5: //db
                colbarll5.setVisibility(View.VISIBLE);
                colbarll2.setVisibility(View.GONE);
                colbarll3.setVisibility(View.GONE);
                colbarll4.setVisibility(View.GONE);
                colbarll1.setVisibility(View.GONE);
                colbarll6.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void onClick(View view) {
        int qq = view.getId();
        switch(qq) {
            case R.id.tapll:
            case R.id.taptxt:
                if (curr == randdcol) {
                    score++;
                    scoretxt.setText("Score: " + score);
                    Toast.makeText(TapMePlay.this, "+1 Point", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder b = new AlertDialog.Builder(TapMePlay.this);
                    b.setCancelable(false);
                    b.setTitle("GAME OVER");
                    b.setMessage("Your score was " + score);
                    sd2.execSQL("insert into hs2 values(" + score + ")");
                    b.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(TapMePlay.this,TapMePlay.class);
                            finish();
                            startActivity(i);
                        }
                    });
                    b.setNegativeButton("Go to HomePage", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Intent i = new Intent(TapMePlay.this,TapMeHome.class);
                            finish();
                            //startActivity(i);
                            System.exit(0);
                        }
                    });
                    b.show();
                }
                break;
            default:
                break;
        }
    }
}