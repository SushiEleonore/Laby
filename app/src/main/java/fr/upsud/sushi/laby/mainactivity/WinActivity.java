package fr.upsud.sushi.laby.mainactivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import fr.upsud.sushi.laby.R;

public class WinActivity extends Activity {
    private MediaPlayer duckSound;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        duckSound= MediaPlayer.create(this,R.raw.duck);
        duckSound.start();
    }

    public void nextLevel(View v){
        duckSound.stop();
        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent2);
       finish();
    }
}