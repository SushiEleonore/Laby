package fr.upsud.sushi.laby.mainactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Constants;

/**
 * Created by proval on 6/14/16.
 */

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




    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////NEEEEEEEEEEW !
    // Declare an object of type Bitmap
    Bitmap bitmapBob;

    // Bob starts off not moving
    boolean isMoving = false;

    // He can walk at 150 pixels per second
    float walkSpeedPerSecond = 250;

    // He starts 10 pixels from the left
    float bobXPosition = 10;

    // New for the sprite sheet animation

    // These next two values can be anything you like
    // As long as the ratio doesn't distort the sprite too much
    private int frameWidth = 100;
    private int frameHeight = 50;

    // How many frames are there on the sprite sheet?
    private int frameCount = 3;

    // Start at the first frame - where else?
    private int currentFrame = 0;

    // What time was it when we last changed frames
    private long lastFrameChangeTime = 0;

    // How long should each frame last
    private int frameLengthInMilliseconds = 100;

    // A rectangle to define an area of the
    // sprite sheet that represents 1 frame
    private Rect frameToDraw = new Rect(
            0,
            0,
            frameWidth,
            frameHeight);

    // A rect that defines an area of the screen
    // on which to draw
    RectF whereToDraw = new RectF(
            bobXPosition,                0,
            bobXPosition + frameWidth,
            frameHeight);


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // Declare an object of type Bitmap
    Bitmap bitmapWall;
    Bitmap bitmapStart;
    Bitmap bitmapPlayerN;
    Bitmap bitmapPlayerS;
    Bitmap bitmapPlayerE;
    Bitmap bitmapPlayerW;
    Bitmap bitmapEnd;
    Level l;

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


    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }

    public Bitmap myBitmapResizer(Bitmap b, int scale) {

            /*byte[] array = getBytesFromBitmap(b);
            System.out.println("taille array"+array.length);
            byte[] pic = new byte[scale* array.length];*/
        int width = b.getWidth();
        int height = b.getHeight();

        int sz = b.getRowBytes() * b.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(sz);
        b.copyPixelsToBuffer(byteBuffer);
        byte[] byteArray = byteBuffer.array();

        byte[] pic = new byte[(byteArray.length*scale)];
        for (int i=0; i<byteArray.length; i++ ){
            for (int j =0; j<scale; j++){
                pic[i*scale+j] = byteArray[i];
                System.out.println("Truc : "+ byteArray[i]);
            }
        }
        Bitmap.Config configBmp = Bitmap.Config.valueOf(b.getConfig().name());
        Bitmap bitmap_tmp = Bitmap.createBitmap(width*scale, height*scale, configBmp);
        ByteBuffer buffer = ByteBuffer.wrap(pic);
        bitmap_tmp.copyPixelsFromBuffer(buffer);
/*
            Bitmap map = BitmapFactory.decodeByteArray(pic , 0, pic.length);
            map = BitmapFactory.decodeByteArray(byteArray , 0, byteArray.length);
*/
        return bitmap_tmp;
    }



    // New variables for the sprite sheet animation

    // When the we initialize (call new()) on gameView
    // This special constructor method runs
    public GameView(Context context, Level l) {
        // The next line of code asks the
        // SurfaceView class to set up our object.
        // How kind.
        super(context);
        this.l=l;
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


        // recreate the new Bitmap
           /*bitmapWall = myBitmapResizer(bitmapWall, scale);
            bitmapStart = myBitmapResizer(bitmapStart, scale);
            bitmapPlayerN = myBitmapResizer(bitmapPlayerN, scale);
            bitmapPlayerS = myBitmapResizer(bitmapPlayerS, scale);
            bitmapPlayerE = myBitmapResizer(bitmapPlayerE, scale);
            bitmapPlayerW =myBitmapResizer(bitmapPlayerW, scale);
            bitmapEnd =myBitmapResizer(bitmapEnd, scale);*/


        bitmapWall = getResizedBitmap(bitmapWall, width, height);
        // bitmapStart = getResizedBitmap(bitmapStart, width, height);
        bitmapPlayerN = getResizedBitmap(bitmapPlayerN, width, height);
        bitmapPlayerS = getResizedBitmap(bitmapPlayerS, width, height);
        bitmapPlayerE = getResizedBitmap(bitmapPlayerE, width, height);
        bitmapPlayerW =getResizedBitmap(bitmapPlayerW, width, height);
        bitmapEnd =getResizedBitmap(bitmapEnd, width, height);
        draw();




        /////////////////////////////////////////////////////////////NEW

        // Load Bob from his .png file
        bitmapBob = BitmapFactory.decodeResource(this.getResources(), R.drawable.bebezilla);

        // Scale the bitmap to the correct size
        // We need to do this because Android automatically
        // scales bitmaps based on screen density
        bitmapBob = Bitmap.createScaledBitmap(bitmapBob,
                frameWidth * frameCount,
                frameHeight,
                false);
        /////////////////////////////////////////////////////////////////////////
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
    //////////////////////////////////////////////////////new
    public void getCurrentFrame(){

        long time  = System.currentTimeMillis();

        if ( time > lastFrameChangeTime + frameLengthInMilliseconds) {
            lastFrameChangeTime = time;
            currentFrame++;
            if (currentFrame >= frameCount) {

                currentFrame = 0;
            }
        }

        //update the left and right values of the source of
        //the next frame on the spritesheet
        frameToDraw.left = currentFrame * frameWidth;
        frameToDraw.right = frameToDraw.left + frameWidth;

    }
    /////////////////////////////////////////////////////////


    public void update(Level ll) {
        /////////////////////////////////////////////////////////////////////:new
        if(isMoving){bobXPosition = bobXPosition + (walkSpeedPerSecond / fps);}
        //////////////////////////////////////////////////////////////////////
        l=ll;}

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
                        ///////////////////////////////////////////////////////new

                        /////////////////////////////////////////////////////

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
                                /*frameToDraw.left = currentFrame * frameWidth;
                                frameToDraw.right = frameToDraw.left + frameWidth;
                                whereToDraw.set((int)bobXPosition+i*size,
                                        j*size,
                                        (int)bobXPosition + frameWidth+i*size,
                                        frameHeight+j*size);

                                getCurrentFrame();

                                canvas.drawBitmap(bitmapBob,
                                        frameToDraw,
                                        whereToDraw, paint);
*/
                                /*getCurrentFrame();
                                for(int k =0; k<3; k++){
                                   try{

                                       frameToDraw.left = currentFrame * frameWidth;
                                       frameToDraw.right = frameToDraw.left + frameWidth;
                                       whereToDraw.set((int)bobXPosition+i*size,
                                               0,
                                               (int)bobXPosition + frameWidth+i*size,
                                               frameHeight);
                                       canvas.drawBitmap(bitmapBob,
                                           frameToDraw,
                                           whereToDraw, paint);
                                       Thread.sleep(300);
                                       }
                                   catch(Exception e){}
                                }
                                */
                                //canvas.drawBitmap(bitmapPlayerS, i * size, j * size, paint);
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

    public void setLevel(Level le){this.l=le;}
    // If SimpleGameEngine Activity is started theb
    // start our thread.
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


}
