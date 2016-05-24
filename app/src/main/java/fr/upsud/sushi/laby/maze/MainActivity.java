package fr.upsud.sushi.laby.maze;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import fr.upsud.sushi.laby.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Level l = new Level(1);

        TextView t=new TextView(this);
        t = (TextView) findViewById(R.id.print);
        String hello = l.printMaze();
        t.setText( hello);
    }
}

