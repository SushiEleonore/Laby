package fr.upsud.sushi.laby.mainactivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.calculus.TermBuilder;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Constants;
import fr.upsud.sushi.laby.utils.Observer;

//import android.support.v7.app.AlertDialog;

class CustomWebChromeClient extends WebChromeClient {

    private Context mContext;

    CustomWebChromeClient(Context ctx) {
        super();
        mContext = ctx;
    }

    @Override
    public boolean onJsPrompt (WebView view, String url, String message, final String defaultValue, final JsPromptResult result)
    {
        View v = LayoutInflater.from(mContext).inflate(R.layout.variable_dialog,null);
        final EditText input = (EditText) v.findViewById(R.id.variable_name);
        input.setText(defaultValue);
        input.setSelection(defaultValue.length());
        new AlertDialog.Builder(mContext)
                .setView(v)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {

                                result.confirm(input.getText().toString());
                            }
                        })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                result.confirm(defaultValue);
                            }
                        })
                .create()
                .show();
        return true;

    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
        new AlertDialog.Builder(mContext)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                result.confirm();
                            }
                        })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                result.cancel();
                            }
                        })
                .create()
                .show();

        return true;
    }

}

public class MainActivity extends AppCompatActivity implements Observer<String> {

    private WebView mWebView;
    private View mCodeView;
    private Level l;
    //private GameView gameView;
    private GameRenderer gameR;
    private Handler mHandler;
    private TermBuilder tbuilder;
    private boolean firsTime;

    //TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        final Intent intent = getIntent();
        int niveau = intent.getIntExtra("level", 0);
        firsTime= true;
        setContentView(R.layout.activity_main);
        l = new Level(niveau, this);
        //Intent intent2 = new Intent(getApplicationContext(), MenuActivity.class);
        //startActivity(intent2);
        //SurfaceView v =  (SurfaceView) findViewById(R.id.surfaceView);
        //gameView = new GameView(this, l);

        SurfaceView sMaze= (SurfaceView) findViewById(R.id.mazeview);
        SurfaceView sPlayer = (SurfaceView) findViewById(R.id.playerview);
        sPlayer.setZOrderOnTop(true);    // necessary

        SurfaceViewDrawer drawer =new SurfaceViewDrawer(sMaze, sPlayer);
        gameR= new GameRenderer(drawer, l, this.getResources());

        //linlay.addView(gameView);
        this.mHandler = new Handler(Looper.getMainLooper());
        //this.addContentView(gameView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT) );
        //LinearLayout lay =(LinearLayout)findViewById(R.id.layout1);

        //gameView.draw();
        this.tbuilder = new TermBuilder(this, l);
        //lay.addView(gameView);
        gameR.drawBG();
        //gameR.drawPlayer();

        //gameView.draw();
        setmWebView();

       /* mWebView = (WebView) findViewById(R.id.webView);

        mWebView.setWebChromeClient(new CustomWebChromeClient(this));
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/blockly/index.html");
        mWebView.addJavascriptInterface(new TermBuilder(this, l), "JavaTermBuilder");*/


        //l.getPlayer().move(l);

        /*
        TextView t = (TextView) findViewById(R.id.print);
        String hello = "";//l.printMaze();
        t.setText( hello);
        */
    }

    public void setmWebView () {
        mWebView = (WebView) findViewById(R.id.webView);

        mWebView.setWebChromeClient(new CustomWebChromeClient(this));
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/blockly/index.html");
        mWebView.addJavascriptInterface(this.tbuilder , "JavaTermBuilder");
    }

    public void setLevel (Level lv) {
        this.l = lv; firsTime=true;

    }




    @Override
    protected void onResume() {
        super.onResume();
        // Tell the gameView resume method to execute
       // gameView.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();
        // Tell the gameView pause method to execute
        //gameView.pause();
    }

    public void winWindow(){
        firsTime=true;
        Intent intent = new Intent(getApplicationContext(), WinActivity.class);
        startActivity(intent);
    }

    public void notify(boolean fin, String id) {
        final String id2 = id;
        final boolean fin2 =fin;

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (fin2){winWindow();nextLevel();}
                    else if (id2==null) {Toast.makeText(getApplicationContext(), "Tu n'es pas allé jusqu'au bout, réessaie !", Toast.LENGTH_SHORT).show();}
                    else {

                        gameR.update(l);
                        gameR.drawPlayer();

                        mWebView.loadUrl("javascript:highlightBlockById('" + id2 +
                                "')");

                    }
                }
            });
    }

    public void evalBlock(View v) {
        if (firsTime){
            System.out.println("First Time");
            mWebView.loadUrl("javascript:evalBlock()");
            firsTime=false;
        }
        else{
            System.out.println("Rest");
            mWebView.loadUrl("javascript:evalRestOfBlock()");
        }
    }

    public void prevStep(View v) {
        this.tbuilder.prevStep();
        firsTime=false;
    }

    public void stop(View v) {
        this.tbuilder.stop();
    }

    public void nextStep(View v) {
            firsTime=false;
            mWebView.loadUrl("javascript:nextStep()");
    }

    public void restartLevel (View  v) {
        l.restart();
        gameR.drawBG();
        gameR.drawPlayer();
        tbuilder.reset();
        firsTime =true;
    }

    public void nextLevel() {
        int lvl = l.getLevel();
        setLevel(new Level(lvl+1, this));
        setmWebView ();
        firsTime=true;
        this.tbuilder= new TermBuilder(this, l);
        mWebView.addJavascriptInterface(tbuilder, "JavaTermBuilder");
        gameR.update(this.l);
        gameR.drawPlayer();
        gameR.drawBG();
    }

    public void actionBlocks(MenuItem m) {
        mCodeView.setVisibility(View.GONE);
        mWebView.setVisibility(View.VISIBLE);
    }

    public void actionCode(MenuItem m) {
        mWebView.setVisibility(View.GONE);
        mCodeView.setVisibility(View.VISIBLE);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

}