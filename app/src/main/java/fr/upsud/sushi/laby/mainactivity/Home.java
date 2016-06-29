package fr.upsud.sushi.laby.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.upsud.sushi.laby.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void toMenu(View v){
        setContentView(R.layout.activity_menu);
        Intent intent2 = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(intent2, 0);
        finish();
    }

    public void toLevel1(View v){
        setContentView(R.layout.activity_main);
        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
        intent2.putExtra("level", 1);
        startActivityForResult(intent2, 0);
        finish();
    }

    public void toBoutique(View v){
        setContentView(R.layout.activity_boutique);
        Intent intent2 = new Intent(getApplicationContext(), BoutiqueActivity.class);
        startActivityForResult(intent2, 0);
        finish();
    }

}
