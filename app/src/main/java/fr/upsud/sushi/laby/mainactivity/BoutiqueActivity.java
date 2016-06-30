package fr.upsud.sushi.laby.mainactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.utils.BitmapParser;
import fr.upsud.sushi.laby.utils.Skins;

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

    public void setDuck(View v) {
        BitmapParser.setSkin(Skins.DUCK);
        Toast.makeText(getApplicationContext(), R.string.set_duck, Toast.LENGTH_SHORT).show();
    }

    public void setBebezilla(View v){
        BitmapParser.setSkin(Skins.BEBEZILLA);
        Toast.makeText(getApplicationContext(), R.string.set_bebezilla, Toast.LENGTH_SHORT).show();
    }
}
