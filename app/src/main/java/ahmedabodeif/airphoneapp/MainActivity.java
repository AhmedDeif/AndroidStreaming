package ahmedabodeif.airphoneapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity //implements CameraBridgeViewBase.CvCameraViewListener2{
{

    Button connectBuuton;
    public TextView statusText;
    private CameraBridgeViewBase mOpenCvCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectBuuton = (Button) findViewById(R.id.connectButton);
        connectBuuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //statusText = (TextView) findViewById(R.id.statusText);
                //statusText.setText("Attempting to Connect to Server.");
                //new ServerConnection().execute();
                startActivity();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*
        if (!OpenCVLoader.initDebug()) {
            Log.e(this.getClass().getSimpleName(), "  OpenCVLoader.initDebug(), not working.");
        } else {
            Log.d(this.getClass().getSimpleName(), "  OpenCVLoader.initDebug(), working.");
        }

        Log.i("OpenCv", "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.HelloOpenCvView);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
        */

    }

    public void startActivity()
    {
        Intent intent = new Intent(this ,VideoViewingActivity.class);
        startActivity(intent);
    }

    /*
    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    public void onCameraViewStarted(int width, int height) {
    }

    public void onCameraViewStopped() {
    }

    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        return inputFrame.rgba();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    */


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i("OpencvTag", "OpenCV loaded successfully");
                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };*/

    @Override
    public void onResume()
    {
        super.onResume();
        //OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_6, this, mLoaderCallback);
    }


    public void updateText(String s)
    {
        statusText.setText("Message from Server: " + s);
    }

    private class ServerConnection extends AsyncTask<Void, Void, Bitmap>
    {
        // each pixel is rep by 8 bits
        Bitmap.Config bmpConfig = Bitmap.Config.ARGB_8888;
        int width = 720;
        int heigth = 480;
        int numberOfBytes = 864000;
        Bitmap result = Bitmap.createBitmap(width,heigth, bmpConfig);
        byte imgData[] = new byte[numberOfBytes];

        char imgDataCar[] = new char[numberOfBytes];

        BufferedInputStream serverStreamRedaer;
        ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
        BufferedOutputStream out;
        Socket sock;
        String URL = "10.0.2.2";
        int portNum = 444;
        String NetworkTag = "SETTING UP NETWORK CONNECTION";
        String SUCCESS = "CONNECTED TO SERVER";
        BufferedReader inFromServer;
        String msgFromServer = "Empty";


        @Override
        protected Bitmap doInBackground(Void... voids) {
            setConnection();
            getImageStream();

                /*
                try
                {
                    Log.e(NetworkTag, "Curenntly in ..... doInBackground");
                    msgFromServer = inFromServer.readLine();
                }
                catch(IOException e)
                {
                    Log.e(NetworkTag, "IO Exception occured");
                }
                */
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }



        public int[] ConvertByteArrayToPixelArray(byte[] bytes)
        {
            int res[] = new int[bytes.length];
            for(int i=0; i<bytes.length; i++)
            {
                //int tmp =
            }
            return null;
        }

        public void getImageStream()
        {
            try
            {

                InputStream in = sock.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte buffer[] = new byte[1024];
                int remainingBytes = numberOfBytes;
                while(remainingBytes > 0)
                {
                    int bytesRead = in.read(buffer);
                    if(bytesRead < 0)
                    {
                        throw new IOException("unexpected end of data");
                    }
                    baos.write(buffer,0,bytesRead);
                    remainingBytes -= bytesRead;
                }
                in.close();
                imgData = baos.toByteArray();
                baos.close();

                int numberOfPixels = numberOfBytes/3;
                int pixels[] = new int [numberOfPixels];
                for(int i=0; i<numberOfPixels; i++)
                {
                    int b = imgData[i*3];
                    int g = imgData[i*3 + 1];
                    int r = imgData[i*3 + 2];
                    if(r < 0)
                        r += 256;
                    if(g < 0)
                        g += 256;
                    if(b < 0 )
                        b += 256;
                    pixels[i] = Color.rgb(
                            r,g,b);

                }
                Bitmap bmp = Bitmap.createBitmap(pixels,720,400, Bitmap.Config.ARGB_8888);

                //BufferedInputStream inp = new BufferedInputStream(sock.getInputStream(),numberOfBytes);
                //inp.read(imgData,0,numberOfBytes);

                Log.e(NetworkTag, "I should have the imgStream filled by now");
                //  convert the vytes to pixels;


            }
            catch (IOException e)
            {
                Log.e(NetworkTag, "IO Exception thrown during receiving image byte stream.");
            }
            catch(Exception e)
            {
                Log.e(NetworkTag, "Uknown Exception thrown during receiving image byte stream.");
            }

        }

        public void setConnection()
        {
            try
            {
                InetAddress address = InetAddress.getByName(URL);
                sock = new Socket(address,portNum);
                if(sock.isConnected())
                    Log.e(NetworkTag, "It got connected");
                else
                    Log.e(NetworkTag, "Not connected");

                /*
                serverStreamRedaer = new BufferedInputStream(sock.getInputStream(),numberOfBytes);
                //out = new BufferedOutputStream(dataStream,numberOfBytes);
                serverStreamRedaer.read(imgData);
                ByteBuffer imgeByteBuffer = ByteBuffer.allocate(numberOfBytes);
                imgeByteBuffer.put(imgData,0,numberOfBytes);
                BitmapFactory.Options bfo = new BitmapFactory.Options();

                // setting bitmap parameters
                result.copyPixelsFromBuffer(imgeByteBuffer);

                //copy(serverStreamRedaer,out);
                //inFromServer = new BufferedReader(new InputStreamReader((sock.getInputStream())));
                // Should be connected by now
                */
            }
            catch (UnknownHostException e) {
                Log.e(NetworkTag, "Could not connect to host");
                e.printStackTrace();
            }
            catch (IOException e) {
                Log.e(NetworkTag, "IOException");
                e.printStackTrace();
            }
            catch(Exception e) {
                Log.e(NetworkTag, "Unknown Exception Thrown, perhaps", e );
            }
        }
    }

}
