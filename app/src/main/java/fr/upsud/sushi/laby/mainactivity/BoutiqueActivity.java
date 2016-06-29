package fr.upsud.sushi.laby.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.upsud.sushi.laby.R;

public class BoutiqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutique);
    }

    public void toHomePage(View v){
        setContentView(R.layout.activity_home);
        Intent intent2 = new Intent(getApplicationContext(), Home.class);
        startActivityForResult(intent2, 0);
        finish();
    }
}
