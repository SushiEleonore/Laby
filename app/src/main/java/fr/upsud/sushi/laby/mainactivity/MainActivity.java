package fr.upsud.sushi.laby.mainactivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.calculus.TermBuilder;
import fr.upsud.sushi.laby.graphics.GameRenderer;
import fr.upsud.sushi.laby.graphics.SurfaceViewDrawer;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Observer;
import fr.upsud.sushi.laby.utils.Values;


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
    private GameRenderer gameR;
    private Handler mHandler;
    private TermBuilder tbuilder;
    private boolean firsTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        int niveau = intent.getIntExtra("level", 0);
        firsTime= true;
        setContentView(R.layout.activity_main);
        l = new Level(niveau, this);

        SurfaceView sMaze= (SurfaceView) findViewById(R.id.mazeview);
        SurfaceView sPlayer =(SurfaceView) findViewById(R.id.playerview);
        SurfaceView sChili = (SurfaceView) findViewById(R.id.chiliview);
        SurfaceView sBWall= (SurfaceView)findViewById(R.id.bwallview);
        //ItemDrawer sBwall = new ItemDrawer(findViewById(R.id.bwallview), l.getbWall(), l, drawze)
        sChili.setZOrderOnTop(true);
        sBWall.setZOrderOnTop(true);
        sPlayer.setZOrderOnTop(true);    // necessary
        SurfaceViewDrawer drawer =new SurfaceViewDrawer(sMaze, sPlayer, sChili,sBWall, (LinearLayout) findViewById(R.id.layout1), l);

        gameR= new GameRenderer(drawer, l, this.getResources());

        //linlay.addView(gameView);
        this.mHandler = new Handler(Looper.getMainLooper());
        //this.addContentView(gameView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT) );
        //LinearLayout lay =(LinearLayout)findViewById(R.id.layout1);
        //gameView.draw();
        this.tbuilder = new TermBuilder(this, l);
       // gameR.drawBG();
        //gameR.drawPlayer();
        setmWebView();


    }

    public void setmWebView () {
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.setWebChromeClient(new CustomWebChromeClient(this));
        mWebView.getSettings().setJavaScriptEnabled(true);
        String blocks = createToolBox(l.getAuthorizedBlocks());
        mWebView.loadUrl("file:///android_asset/blockly/index.html?blocks=" + blocks + "=" + l.getNbBlocks());
        //createToolBox(l.getAuthorizedBlocks());
        mWebView.addJavascriptInterface(this.tbuilder , "JavaTermBuilder");
        mWebView.addJavascriptInterface(this , "JavaMainActivity");
    }
    public void setLevel (Level lv) {
        this.l = lv; firsTime=true;
        gameR.drawBG();
        gameR.drawChili();
    }


    @Override
    protected void onResume() {
         super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    /*public void winWindow(){
        firsTime=true;
        Intent intent = new Intent(getApplicationContext(), WinActivity.class);
        startActivityForResult(intent, 0);
    }
*/
    public void notify(boolean fin, String id, final boolean resetLevel, final int mv, final boolean pDestroying) {
        final String id2 = id;
        final boolean fin2 =fin;
        final boolean resetLevel2 = resetLevel;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (resetLevel2) {restartLevel(); }
                    if (fin2){backToMenu();}
                    else if (id2==null) {restartLevel(); Toast.makeText(getApplicationContext(), R.string.essaie_encore, Toast.LENGTH_SHORT).show();}
                    else {
                        mWebView.loadUrl("javascript:highlightBlockById('" + id2 + "')");
                        if(pDestroying) gameR.drawPDestroying();
                        else if(mv==0)gameR.drawPlayer();
                        else {l.getPlayer().setMotion(mv);
                            gameR.drawMvingPlayer(mv);
                        }
                        if(l.getPlayer().hasChili())gameR.eraseChili();
                        if(l.getbWall()!=null &&!l.getbWall().getState()){ gameR.erasebWall();}
                        if(l.getbWall()!=null &&l.getbWall().getState()){ gameR.drawBreakableWall();}
                    }
                }
            });
    }


    /**** Blocks operations ****/


    public String createToolBox(ArrayList<String> blocks) {

        String arg = "";

        for (int i = 0; i < blocks.size() - 1; i++) {
            arg +=   blocks.get(i) + ",";
        }

        if (blocks.size() != 0) {
            arg +=  blocks.get(blocks.size() - 1);
        }

        if( Values.DEBUG_MODE) System.out.println("tableau : "+arg);

        return arg;
    }



    /***** Level restart operations *****/

    public void resetButtons() {
        Button b = (Button) findViewById(R.id.button);
        b.setText("");
        b.setBackground(getResources().getDrawable(R.drawable.play));

    }

    public void restartLevel () {
        l.restart();
        gameR.drawBG();
        gameR.drawPlayer();
        resetButtons();
        if(l.getItem()!=null)gameR.drawChili();
        if(l.getbWall()!=null)gameR.drawBreakableWall();

        tbuilder.reset();
        firsTime =true;
    }
    public void restartLevel (View v) {
        l.restart();
        gameR.drawBG();
        gameR.drawPlayer();
        if(l.getItem()!=null)gameR.drawChili();
        if(l.getbWall()!=null)gameR.drawBreakableWall();
        resetButtons();
        tbuilder.reset();
        firsTime =true;
    }


    //changed so the size of the bitmaps is updated when the level is changed
    public void nextLevel() {
        int lvl = l.getLevel();
        if (lvl+1>l.getLevelMax()){
            Toast.makeText(getApplicationContext(), R.string.toast_bravo, Toast.LENGTH_SHORT).show();
            backToMenu();
        } else {
            setLevel(new Level(lvl + 1, this));
            setmWebView();
            firsTime = true;
            resetButtons();
            this.tbuilder = new TermBuilder(this, l);
            mWebView.addJavascriptInterface(tbuilder, "JavaTermBuilder");
            /*SurfaceView sMaze = (SurfaceView) findViewById(R.id.mazeview);
            SurfaceView sPlayer = (SurfaceView) findViewById(R.id.playerview);
            sPlayer.setZOrderOnTop(true);
            SurfaceViewDrawer drawer =
                    new SurfaceViewDrawer(sMaze, sPlayer, (LinearLayout) findViewById(R.id.layout1),
                            l);
            gameR = new GameRenderer(drawer, l, this.getResources());
            */
            //gameR.update(this.l);

          // gameR.drawPlayer();
            //gameR.drawBG();
            //gameR.drawChili();
        }
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

    /****** Button calls *******/


    public void evalBlock(View v) {
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        if (buttonText.equals("")) {
            if (firsTime){
                if(Values.DEBUG_MODE)System.out.println("First Time");
                mWebView.loadUrl("javascript:evalBlock()");
                firsTime=false;
            }
            else{
                if (Values.DEBUG_MODE) System.out.println("Rest");
                mWebView.loadUrl("javascript:evalRestOfBlock()");
            }
            b.setBackground(getResources().getDrawable(R.drawable.pause));
            b.setText(" ");

        } else {
            this.tbuilder.stop();
            b.setBackground(getResources().getDrawable(R.drawable.play));
            b.setText("");
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

    public void backToMenu(View v) {
        setContentView(R.layout.activity_menu);
        Intent intent2 = new Intent(getApplicationContext(), MenuActivity.class);
        //intent2.putExtra("level", level);
        startActivityForResult(intent2, 0);
        finish();


    }

    public void backToMenu() {
        setContentView(R.layout.activity_menu);
        Intent intent2 = new Intent(getApplicationContext(), MenuActivity.class);
        //intent2.putExtra("level", level);
        startActivityForResult(intent2, 0);
        finish();

    }



    /**** For string resources ****/

    @JavascriptInterface
    public String get_tourner_a() {
        return getString(R.string.tourner_a);
    }
    @JavascriptInterface
    public String get_gauche() {
        return getString(R.string.gauche);
    }
    @JavascriptInterface
    public String get_droite() {
        return getString(R.string.droite);
    }
    @JavascriptInterface
    public String get_a_gauche() {
        return getString(R.string.a_gauche);
    }
    @JavascriptInterface
    public String get_a_droite() {
        return getString(R.string.a_droite);
    }
    @JavascriptInterface
    public String get_devant() {
        return getString(R.string.devant) ;
    }
    @JavascriptInterface
    public String get_avancer_case() {
        return getString(R.string.avancer_case);
    }
    @JavascriptInterface
    public String get_tant_que() {
        return getString(R.string.tant_que_niv_pas_fini);
    }
    @JavascriptInterface
    public String get_faire() {
        return getString(R.string.faire);
    }
    @JavascriptInterface
    public String get_si_chemin() {
        return getString(R.string.si_chemin);
    }
    @JavascriptInterface
    public String get_sinon() {
        return getString(R.string.sinon);
    }
    @JavascriptInterface
    public String get_manger() {
        return getString(R.string.manger_piment);
    }
    @JavascriptInterface
    public String get_activer_pouvoir() {
        return getString(R.string.activer_pouvoir);
    }



}