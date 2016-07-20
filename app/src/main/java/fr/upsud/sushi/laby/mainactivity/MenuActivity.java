package fr.upsud.sushi.laby.mainactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.upsud.sushi.laby.R;

/**
 * Created by proval on 6/10/16.
 */
public class MenuActivity extends Activity {
    private int level;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void levelX(View v){
        level = Integer.parseInt(v.getTag().toString());
        setContentView(R.layout.activity_main);
        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
        intent2.putExtra("level", level);
        startActivityForResult(intent2, 0);

        finish();
    }

    public void backToHome(View v){
        setContentView(R.layout.activity_home);
        Intent intent2 = new Intent(getApplicationContext(), Home.class);
        startActivityForResult(intent2, 0);
        finish();
    }

}
