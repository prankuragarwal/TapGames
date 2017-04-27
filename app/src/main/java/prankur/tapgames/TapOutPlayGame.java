package prankur.tapgames;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
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

import java.util.*;
import java.lang.*;

public class TapOutPlayGame extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    LinearLayout sco, yesll, noll;
    ImageView playimageone, playimagezero, playimagetwo, playimagethree, playimagefour;
    TextView  yes, no;
    Thread t;
    private TextView activityName;
    int i = 0;
    int scor = 0;
    int pre = 0;
    TextView sec, score;
    SQLiteDatabase sd1;
    final CounterClass timer = new CounterClass(60000, 1000);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap_out_play_game);
        System.gc();
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        activityName = (TextView) findViewById(R.id.activity_name);
        //scor = (TextView) findViewById(R.id.scor);
        //sco = (LinearLayout) findViewById(R.id.sco);
        no = (TextView) findViewById(R.id.no);
        sec = (TextView) findViewById(R.id.sectext);
        score = (TextView) findViewById(R.id.scoretex);
        noll = (LinearLayout) findViewById(R.id.noll);
        yes = (TextView) findViewById(R.id.yes);
        yesll = (LinearLayout) findViewById(R.id.yesll);
        setSupportActionBar(toolbar);
        yesll.setOnClickListener(this);
        noll.setOnClickListener(this);
        playimagezero = (ImageView) findViewById(R.id.playimagezero);
        playimageone = (ImageView) findViewById(R.id.playimageone);
        playimagetwo = (ImageView) findViewById(R.id.playimagetwo);
        playimagethree = (ImageView) findViewById(R.id.playimagethree);
        playimagefour = (ImageView) findViewById(R.id.playimagefour);
        toolbar.setNavigationIcon(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Typeface tf1 = Typeface.createFromAsset(getAssets(),
                "OneDirection.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Bold.otf");
        Typeface tf3 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Regular.otf");
        //score.setTypeface(tf3);
        no.setTypeface(tf1);
        yes.setTypeface(tf1);
        i = 0;
        scor = 0;
        pre = i;
        activityName.setTypeface(tf2);
        activityName.setText("         Play");
        score.setText("Score: " + scor);
        funran();
        timer.start();
        pre = i;
        playimagezero.setOnClickListener(this);
        playimageone.setOnClickListener(this);
        playimagetwo.setOnClickListener(this);
        playimagethree.setOnClickListener(this);
        playimagefour.setOnClickListener(this);
        sd1 = openOrCreateDatabase("TAPOUT", MODE_PRIVATE,null);
        sd1.execSQL("create table if not exists hs2(highscore int(10) not null)");
    }

    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            sec.setText("Time: "+ "0" + " sec");
            yes.setEnabled(false);
            no.setEnabled(false);
            SharedPreferences sp = getSharedPreferences("TapoutApp", 0);
            SharedPreferences.Editor ed = sp.edit();
            if (scor > sp.getInt("HighScore", 0)) {
                ed.putInt("HighScore", scor);
                ed.commit();
            }
            sd1.execSQL("insert into hs1 values(" + scor + ")");
            AlertDialog.Builder b = new AlertDialog.Builder(TapOutPlayGame.this);
            b.setTitle("GAME OVER");
            b.setMessage("Your score was " + scor);
            b.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(TapOutPlayGame.this,TapOutPlayGame.class);

                    finish();

                    startActivity(i);
                }
            });
            b.setNegativeButton("Go to HomePage", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(TapOutPlayGame.this,TapOutHome.class);

                    finish();

                    startActivity(i);

                }
            });
            b.show();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if ((millisUntilFinished/1000) < 10) {
                sec.setTextColor(Color.RED);
            } else {
                sec.setTextColor(Color.BLACK);
            }
            sec.setText("Time: "+ (millisUntilFinished/1000) + " Sec");
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        scor = 0;
        i = 0;
        pre = 0;
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


    void funran() {
        Random randomGenerator = new Random();
        i = randomGenerator.nextInt(5);
        if (i == 0) {
            playimagezero.setVisibility(View.VISIBLE);
            playimageone.setVisibility(View.GONE);
            playimagetwo.setVisibility(View.GONE);
            playimagethree.setVisibility(View.GONE);
            playimagefour.setVisibility(View.GONE);
        } else if (i == 1) {
            playimageone.setVisibility(View.VISIBLE);
            playimagezero.setVisibility(View.GONE);
            playimagetwo.setVisibility(View.GONE);
            playimagethree.setVisibility(View.GONE);
            playimagefour.setVisibility(View.GONE);
        } else if (i == 2) {
            playimagetwo.setVisibility(View.VISIBLE);
            playimagezero.setVisibility(View.GONE);
            playimageone.setVisibility(View.GONE);
            playimagethree.setVisibility(View.GONE);
            playimagefour.setVisibility(View.GONE);
        } else if (i == 3) {
            playimagethree.setVisibility(View.VISIBLE);
            playimagezero.setVisibility(View.GONE);
            playimageone.setVisibility(View.GONE);
            playimagetwo.setVisibility(View.GONE);
            playimagefour.setVisibility(View.GONE);
        } else if (i == 4) {
            playimagefour.setVisibility(View.VISIBLE);
            playimagezero.setVisibility(View.GONE);
            playimageone.setVisibility(View.GONE);
            playimagetwo.setVisibility(View.GONE);
            playimagethree.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yesll:
                if (i == pre) {
                    scor++;
                    score.setText("Score: " + scor);
                    pre = i;
                    Toast.makeText(TapOutPlayGame.this, "+1 Point", Toast.LENGTH_SHORT).show();
                    playimagefour.setVisibility(View.GONE);
                    playimagezero.setVisibility(View.GONE);
                    playimageone.setVisibility(View.GONE);
                    playimagetwo.setVisibility(View.GONE);
                    playimagethree.setVisibility(View.GONE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            funran();
                        }
                    }, 50);
                } else {
                    timer.cancel();
                    SharedPreferences sp = getSharedPreferences("TapoutApp", 0);
                    SharedPreferences.Editor ed = sp.edit();
                    if (scor > sp.getInt("HighScore", 0)) {
                        ed.putInt("HighScore", scor);
                        ed.commit();
                    }
                    sd1.execSQL("insert into hs1 values(" + scor + ")");
                    AlertDialog.Builder b = new AlertDialog.Builder(TapOutPlayGame.this);
                    b.setTitle("GAME OVER");
                    b.setMessage("Your score was " + scor);
                    b.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(TapOutPlayGame.this, TapOutPlayGame.class);

                            finish();

                            startActivity(i);
                        }
                    });
                    scor = 0;
                    i = 0;
                    pre = 0;
                    score.setText("Score: " + scor);

                    b.setNegativeButton("Go to HomePage", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Intent i = new Intent(PlayGame.this, Home.class);
                            finish();
                            System.exit(0);
                            //startActivity(i);

                        }
                    });
                    b.show();
                }
                break;
            case R.id.noll:
                if (i != pre) {
                    scor++;
                    score.setText("Score: " + scor);
                    pre = i;
                    Toast.makeText(TapOutPlayGame.this, "+1 Point", Toast.LENGTH_SHORT).show();
                    playimagefour.setVisibility(View.GONE);
                    playimagezero.setVisibility(View.GONE);
                    playimageone.setVisibility(View.GONE);
                    playimagetwo.setVisibility(View.GONE);
                    playimagethree.setVisibility(View.GONE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            funran();
                        }
                    }, 50);
                } else {
                    timer.cancel();
                    SharedPreferences sp = getSharedPreferences("TapoutApp", 0);
                    SharedPreferences.Editor ed = sp.edit();
                    if (scor > sp.getInt("HighScore", 0)) {
                        ed.putInt("HighScore", scor);
                        ed.commit();
                    }
                    sd1.execSQL("insert into hs1 values(" + scor + ")");
                    AlertDialog.Builder b = new AlertDialog.Builder(TapOutPlayGame.this);
                    b.setTitle("GAME OVER");
                    b.setMessage("Your score was " + scor);
                    b.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(TapOutPlayGame.this, TapOutPlayGame.class);

                            finish();

                            startActivity(i);
                        }
                    });
                    scor = 0;
                    i = 0;
                    pre = 0;
                    score.setText("Score: " + scor);
                    b.setNegativeButton("Go to HomePage", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Intent i = new Intent(PlayGame.this, Home.class);

                            finish();
                            System.exit(0);
                            //startActivity(i);

                        }
                    });
                    b.show();
                }
        }
    }
}