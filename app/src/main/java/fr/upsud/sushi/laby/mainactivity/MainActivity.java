package fr.upsud.sushi.laby.mainactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.maze.Level;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Level l = new Level(1);
        l.getPlayer().move(l);

        TextView t=new TextView(this);
        t = (TextView) findViewById(R.id.print);
        String hello = l.printMaze();
        t.setText( hello);
    }
}

