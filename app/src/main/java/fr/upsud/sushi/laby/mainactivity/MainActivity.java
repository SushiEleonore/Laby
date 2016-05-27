package fr.upsud.sushi.laby.mainactivity;

import android.content.Context;
import android.content.DialogInterface;
//import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import fr.upsud.sushi.laby.calculus.TermBuilder;
import fr.upsud.sushi.laby.utils.Observer;
import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.maze.Level;

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
    //TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = new Level(1);
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.setWebChromeClient(new CustomWebChromeClient(this));
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/blockly/index.html");
        mWebView.addJavascriptInterface(new TermBuilder(this, l), "JavaTermBuilder");

        //l.getPlayer().move(l);


        TextView t = (TextView) findViewById(R.id.print);
        String hello = l.printMaze();
        t.setText( hello);

    }

    public void notify(String data) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                TextView t = (TextView) findViewById(R.id.print);
                t.clearComposingText();
                t.setText(l.printMaze());
                //title.clearComposingText();//not useful

            }
        });



    }

    public void evalBlock(View v) {
        mWebView.loadUrl("javascript:evalBlock()");


    }

    public void restartLevel (View  v) {
        TextView t = (TextView) findViewById(R.id.print);
        l.restart();
        t.setText(l.printMaze());
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

/*
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

/*


//package fr.upsud.sushi.laby.mainactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import fr.upsud.sushi.laby.PrintMazeTest;
import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.maze.Level;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private PrintMazeTest mView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Level l = new Level(1);

        mView = new PrintMazeTest(this, l);
        l.getPlayer().move(l);
       // try {
       //     Thread.sleep(2000);
       // } catch (Exception e) {}
        mView = new PrintMazeTest(this, l);
        setContentView(mView);

    }

}

/*
@Override
protected void onCreate savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Level l = new Level(1);
    l.getPlayer().move(l);

    TextView t=new TextView(this);
    t = (TextView) findViewById(R.id.print);
    String hello = l.printMaze();
    t.setText( hello);
}*/