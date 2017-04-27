package prankur.tapgames;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class TapGamesSplash extends AppCompatActivity {

    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_games_splash);
        System.gc();
        iv = (ImageView) findViewById(R.id.gifvie);
        Thread t = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(Exception e){

                }finally{
                    Intent inte = new Intent(TapGamesSplash.this, TapGamesHome.class);
                    startActivity(inte);
                }
            }
        };
        t.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}

