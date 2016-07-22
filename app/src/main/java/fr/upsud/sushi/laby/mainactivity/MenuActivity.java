package fr.upsud.sushi.laby.mainactivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import fr.upsud.sushi.laby.R;


public class MenuActivity extends Activity {
    private int level;

    private void setMenu (){
        InputStream iS =  this.getResources().openRawResource(R.raw.menu);
        try {
            byte[] buffer = new byte[iS.available()];
            iS.read(buffer);
            ByteArrayOutputStream oS = new ByteArrayOutputStream();
            oS.write(buffer);
            oS.close();
            iS.close();
            createMenu(oS.toString());
        } catch (Exception e) {e.printStackTrace();System.out.println("File problem");}
    }

    private void createMenu(String s) {
        String[] fileNames = s.split("\n");
        int nbfiles = fileNames.length;
        int nblignes = (int)Math.ceil( (double)nbfiles/ (double)3);
        int nbColonnes = 3;

        int cpt = 1;

        for (int i = 1; i<=nblignes; i++) {

            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            //params.gravity = Gravity.TOP;
            ll.setLayoutParams(params);
            ll.setWeightSum((float)0.2);
          //  LinearLayout layout = (LinearLayout) findViewById(R.id.bigLayout);
            //layout.addView(ll);
            for (int j = 1; j<=nbColonnes; j++) {
                Button myButton = new Button(this);
                String str = (cpt) + "";
                myButton.setText(str);
                myButton.setTag(str);
                final int id_ = myButton.getId();

                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
             //   params2.weight = 0.01f;
                //params.gravity = Gravity.TOP;
                myButton.setLayoutParams(params2);
                ll.addView(myButton);
                Button b2 = (Button) findViewById(id_);
                myButton.setOnClickListener(levelListener);
                cpt++;
            }

        }

       /* for (int i = 1; i <= 20; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            Button btn = new Button(this);
            btn.setId(i);
            final int id_ = btn.getId();
            btn.setText("button " + id_);
            btn.setBackgroundColor(Color.rgb(70, 80, 90));
            linear.addView(btn, params);
            btn1 = ((Button) findViewById(id_));
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }*/


        /*
     for (int i = 0; i<nbfiles; i++) {
            Button myButton = new Button(this);
            String str = (i+1) + "";
            myButton.setText(str);
            myButton.setTag(str);
            myButton.setOnClickListener(levelListener);
         LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
         params.weight = 0.1f;

         params.gravity = Gravity.TOP;
         myButton.setLayoutParams(params);
            LinearLayout layout = (LinearLayout) findViewById(R.id.layoutButton1);
            layout.addView(myButton);
        }*/

    }

    private View.OnClickListener levelListener = new View.OnClickListener(){
        public void onClick(View v) {
            levelX(v);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
       // setMenu();
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
