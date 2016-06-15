package fr.upsud.sushi.laby.mainactivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.calculus.TermBuilder;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Constants;
import fr.upsud.sushi.laby.utils.IndexEditor;
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
    private GameView gameView;
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

        //IndexEditor ie = new IndexEditor(l);
        //Intent intent2 = new Intent(getApplicationContext(), MenuActivity.class);
        //startActivity(intent2);
        //SurfaceView v =  (SurfaceView) findViewById(R.id.surfaceView);
        gameView = new GameView(this);
        LinearLayout linlay= (LinearLayout) findViewById(R.id.linlayout);
        linlay.addView(gameView);
        this.mHandler = new Handler(Looper.getMainLooper());
        //this.addContentView(gameView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT) );
        //LinearLayout lay =(LinearLayout)findViewById(R.id.layout1);

        //gameView.draw();
        this.tbuilder = new TermBuilder(this, l);
        //lay.addView(gameView);


        gameView.draw();
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
        String blocks = createToolBox(l.getAuthorizedBlocks());
        mWebView.loadUrl("file:///android_asset/blockly/index.html?blocks=" + blocks);
        //createToolBox(l.getAuthorizedBlocks());
        //file:///android_asset/blockly/index.html
        mWebView.addJavascriptInterface(this.tbuilder , "JavaTermBuilder");
    }

    public void setLevel (Level lv) {
        this.l = lv; firsTime=true;
    }


    class GameView extends SurfaceView implements Runnable {

        // This is our thread
        Thread gameThread = null;

        //The current level
        //Level l;

        private int size;

        /*this should depend on the screen*/
        private int scale;

        // This is new. We need a SurfaceHolder
        // When we use Paint and Canvas in a thread
        // We will see it in action in the draw method soon.
        SurfaceHolder ourHolder;

        // A boolean which we will set and unset
        // when the game is running- or not.
        volatile boolean playing;

        // A Canvas and a Paint object
        Canvas canvas;
        Paint paint;

        // This variable tracks the game frame rate
        long fps;

        // This is used to help calculate the fps
        private long timeThisFrame;

        // Declare an object of type Bitmap
        Bitmap bitmapWall;
        Bitmap bitmapStart;
        Bitmap bitmapPlayerN;
        Bitmap bitmapPlayerS;
        Bitmap bitmapPlayerE;
        Bitmap bitmapPlayerW;
        Bitmap bitmapEnd;


        public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
            int width = bm.getWidth();
            int height = bm.getHeight();
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            // CREATE A MATRIX FOR THE MANIPULATION
            Matrix matrix = new Matrix();
            // RESIZE THE BIT MAP
            matrix.postScale(scaleWidth, scaleHeight);

            // "RECREATE" THE NEW BITMAP
            Bitmap resizedBitmap = Bitmap.createBitmap(
                    bm, 0, 0, width, height, matrix, true);
            bm.recycle();
            return resizedBitmap;
        }

        public  Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight) {
            Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

            float scaleX = newWidth / (float) bitmap.getWidth();
            float scaleY = newHeight / (float) bitmap.getHeight();
            float pivotX = 0;
            float pivotY = 0;

            Matrix scaleMatrix = new Matrix();
            scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY);

            Canvas canvas = new Canvas(scaledBitmap);
            canvas.setMatrix(scaleMatrix);
            canvas.drawBitmap(bitmap, 0, 0, new Paint(Paint.FILTER_BITMAP_FLAG));

            return scaledBitmap;
        }

        public Bitmap BITMAP_RESIZER(Bitmap bitmap,int newWidth,int newHeight) {
            Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

            float ratioX = newWidth / (float) bitmap.getWidth();
            float ratioY = newHeight / (float) bitmap.getHeight();
            float middleX = newWidth / 2.0f;
            float middleY = newHeight / 2.0f;

            Matrix scaleMatrix = new Matrix();
            scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

            Canvas canvas = new Canvas(scaledBitmap);
            canvas.setMatrix(scaleMatrix);
            canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

            return scaledBitmap;

        }




        // New variables for the sprite sheet animation

        // When the we initialize (call new()) on gameView
        // This special constructor method runs
        public GameView(Context context) {
            // The next line of code asks the
            // SurfaceView class to set up our object.
            // How kind.
            super(context);

            // Initialize ourHolder and paint objects

            ourHolder = getHolder();

            paint =  new Paint(Paint.FILTER_BITMAP_FLAG |
                    Paint.DITHER_FLAG |
                    Paint.ANTI_ALIAS_FLAG);
            //l = new Level (0);
            this.scale = Constants.SCALE;

            //To get a good quality
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            // Load Bob from his .png file
            bitmapWall = BitmapFactory.decodeResource(this.getResources(), R.drawable.mini_mur2, options);
           // bitmapStart = BitmapFactory.decodeResource(this.getResources(), R.drawable.mini_mur2, options);
            bitmapPlayerN = BitmapFactory.decodeResource(this.getResources(), R.drawable.mini_canard_dos, options);
            bitmapPlayerS = BitmapFactory.decodeResource(this.getResources(), R.drawable.mini_canard_face, options);
            bitmapPlayerE = BitmapFactory.decodeResource(this.getResources(), R.drawable.bigduck_d, options);
            bitmapPlayerW = BitmapFactory.decodeResource(this.getResources(), R.drawable.mini_canard_g, options);
            bitmapEnd = BitmapFactory.decodeResource(this.getResources(), R.drawable.arrivee, options);

            /*Resizing the bitmaps*/

            int width = bitmapWall.getWidth()*scale;
            int height = bitmapWall.getHeight()*scale;
            size = width;


            bitmapWall = getResizedBitmap(bitmapWall, width, height);
           // bitmapStart = getResizedBitmap(bitmapStart, width, height);
            bitmapPlayerN = getResizedBitmap(bitmapPlayerN, width, height);
            bitmapPlayerS = getResizedBitmap(bitmapPlayerS, width, height);
            bitmapPlayerE = getResizedBitmap(bitmapPlayerE, width, height);
            bitmapPlayerW =getResizedBitmap(bitmapPlayerW, width, height);
            bitmapEnd =getResizedBitmap(bitmapEnd, width, height);
            draw();

        }

        @Override
        public void run() {
            while (playing) {

                // Capture the current time in milliseconds in startFrameTime
                long startFrameTime = System.currentTimeMillis();

                // Update the frame
                update(l);

                // Draw the frame
                draw();

                // Calculate the fps this frame
                // We can then use the result to
                // time animations and more.
                timeThisFrame = System.currentTimeMillis() - startFrameTime;
                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame;
                }

            }

        }

        public void update(Level ll) {

            l=ll;

        }

        // Draw the newly updated scene
        public void draw() {

            // Make sure our drawing surface is valid or we crash
            if (ourHolder.getSurface().isValid()) {

                // Lock the canvas ready to draw
                canvas = ourHolder.lockCanvas();

                // Draw the background color
                canvas.drawColor(Color.argb(255, 255, 255, 255));

                // Choose the brush color for drawing
                paint.setColor(Color.argb(255,  249, 129, 0));


                // Draw bob at bobXPosition, 200 pixels
                //canvas.drawBitmap(bitmapWall, bobXPosition, 200, paint);
                for (int i = 0; i< l.getCells().length; i++) {
                    for (int j = 0; j< l.getCells()[i].length; j++) {
                        if (l.getCells()[i][j] == null ) {
                        } else {
                            switch (l.getCells()[i][j].getType()) {
                                /*case START :
                                    canvas.drawBitmap(bitmapStart, i*size, j*size, paint);
                                    break;*/
                                case END :
                                    canvas.drawBitmap(bitmapEnd, i*size, j*size, paint);
                                    break;
                                default :
                                    canvas.drawBitmap(bitmapWall, i*size, j*size, paint);
                                    break;
                            }
                        }
                        if (i == l.getPlayer().getX() && j == l.getPlayer().getY()) {
                            switch (l.getPlayer().getDir()) {
                                case S:
                                    canvas.drawBitmap(bitmapPlayerS, i * size, j * size, paint);
                                    break;
                                case E:
                                    canvas.drawBitmap(bitmapPlayerE, i * size, j * size, paint);
                                    break;
                                case W:
                                    canvas.drawBitmap(bitmapPlayerW, i * size, j * size, paint);
                                    break;
                                default:
                                    canvas.drawBitmap(bitmapPlayerN, i * size, j * size, paint);
                                    break;

                            }
                        }

                    }
                }

                // New drawing code goes here

                // Draw everything to the screen
                ourHolder.unlockCanvasAndPost(canvas);
            }


        }

        // If SimpleGameEngine Activity is paused/stopped
        // shutdown our thread.
        public void pause() {
            playing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("Error:", "joining thread");
            }

        }

        // If SimpleGameEngine Activity is started theb
        // start our thread.
        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

    }



    @Override
    protected void onResume() {
        super.onResume();

        // Tell the gameView resume method to execute
        gameView.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the gameView pause method to execute
        gameView.pause();
    }

    public void winWindow(){
        firsTime=true;
        Intent intent = new Intent(getApplicationContext(), WinActivity.class);
        startActivity(intent);

    }

    public void notify(boolean fin, String id, final boolean resetLevel) {
        final String id2 = id;
        final boolean fin2 =fin;
        final boolean resetLevel2 = resetLevel;


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                /*TextView t = (TextView) findViewById(R.id.print);
                t.clearComposingText();
                t.setText("");//l.printMaze());
                *///title.clearComposingText();//not useful

                    if (resetLevel2) {restartLevel (gameView); }
                    if (fin2){winWindow();nextLevel();}
                    else if (id2==null) {Toast.makeText(getApplicationContext(), "Tu n'es pas allé jusqu'au bout, réessaie !", Toast.LENGTH_SHORT).show();}
                    else {

                        gameView.update(l);
                        gameView.draw();

                        mWebView.loadUrl("javascript:highlightBlockById('" + id2 +
                                "')");

                    }
                }
            });



    }

    //////////
    public void evalBlock(View v) {
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        if (buttonText.equals(getResources().getText(R.string.play))) {
            System.out.println("COUCOU");
            if (firsTime){
                System.out.println("First Time");
                mWebView.loadUrl("javascript:evalBlock()");
                firsTime=false;
            }
            else{
                System.out.println("Rest");
                mWebView.loadUrl("javascript:evalRestOfBlock()");
            }
            b.setText(getResources().getText(R.string.stop));

        } else {
            this.tbuilder.stop();
            b.setText(getResources().getText(R.string.play));
        }


    }

  /* public void  highlightBlockById(String id) {
        mWebView.loadUrl("javascript:highlightBlockById('" + id +
                "')");
    }
*/
  public void highlightBlockById(final String id)
    {
  //Déposer le Runnable dans la file d'attente de l'UI thread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //code exécuté par l'UI thread
                mWebView.loadUrl("javascript:highlightBlockById('" + id +
                        "')");
                            }
        });

    }

    public String createToolBox(ArrayList<String> blocks) {

        String arg = "";

        for (int i = 0; i < blocks.size() - 1; i++) {
            arg +=   blocks.get(i) + ",";
        }

        if (blocks.size() != 0) {
            arg +=  blocks.get(blocks.size() - 1);
        }

        System.out.println("tableau : "+arg);
        return arg;
    }

    //////new
    public void prevStep(View v) {
        this.tbuilder.prevStep();
        firsTime=false;
        //mWebView.loadUrl("javascript:prevStep()");
    }


    public void stop(View v) {
        this.tbuilder.stop();
        //mWebView.loadUrl("javascript:prevStep()");
    }
    /////Changed
    public void nextStep(View v) {
       // if (this.tbuilder.getGameStates().size() == this.tbuilder.getnStep()) {
            firsTime=false;
            mWebView.loadUrl("javascript:nextStep()");
        //} else
       // {
         //   tbuilder.nextStepBis();
       // }
    }

    public void resetButtons() {
        Button b = (Button) findViewById(R.id.button);
        b.setText(getResources().getText(R.string.play));

    }



    public void restartLevel (View  v) {
        //TextView t = (TextView) findViewById(R.id.print);
        l.restart();
        resetButtons();
        gameView.draw();
        tbuilder.reset();
        firsTime =true;
        //t.setText("");//l.printMaze());
    }

    public void nextLevel() {
        int lvl = l.getLevel();
        setLevel(new Level(lvl+1, this));
        setmWebView ();
        firsTime=true;
        resetButtons();
        this.tbuilder= new TermBuilder(this, l);
        mWebView.addJavascriptInterface(tbuilder, "JavaTermBuilder");
        //l = new Level(lvl+1);

        gameView.draw();
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
