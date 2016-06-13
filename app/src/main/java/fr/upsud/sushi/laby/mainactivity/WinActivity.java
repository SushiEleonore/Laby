package fr.upsud.sushi.laby.mainactivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import fr.upsud.sushi.laby.R;

/**
 * Created by proval on 6/8/16.
 */
public class WinActivity extends Activity {
    private MediaPlayer duckSound;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        duckSound= MediaPlayer.create(this,R.raw.duck);
        duckSound.start();
      //  Intent intent = getIntent();
    }

    public void nextLevel(View v){
        //duckSound.stop();
       finish();
    }
}