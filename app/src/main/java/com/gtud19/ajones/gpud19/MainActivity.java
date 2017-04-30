package com.gtud19.ajones.gpud19;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.util.Set;
import java.net.Socket;
import java.io.Writer;
import java.lang.Math;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView display;
    private EditText editText;

    private EditText ang1;
    private EditText ang2;

    private Conn c;
    private SensorManager mSensorManager;
    Sensor gyro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        gyro = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mSensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_UI);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = new Conn();
        c.execute("");
        display = (TextView) findViewById(R.id.textView1);
        editText = (EditText) findViewById(R.id.editText1);
        ang1 = (EditText) findViewById(R.id.angle1);
        ang2 = (EditText) findViewById(R.id.angle2);

        Button sendButton = (Button) findViewById(R.id.buttonSend);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")) {
                    String data = editText.getText().toString();
                    try{
                        SendThread q = new SendThread(data,display,c);

                        q.start();
                        display.append(data);
                    }

                    catch(Exception e){display.append("Failure");}
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    public void onAccuracyChanged(Sensor sensor, int accuracy) {  }

    float[] mGravity;
    long time = 0;
    double dir2 = 0;
    double angleSet=Math.PI/2;
    public void onSensorChanged(SensorEvent event) {
        //angleSet = Double.parseDouble(ang1.getText().toString());
        if(time == 0){
            Date d = new Date();
            time = d.getTiu e()/1000;
        }
        if (event.sensor.getType() == Sensor.TYPE_GRAVITY)
            mGravity = event.values;
        double angle = Math.abs(Math.atan2(mGravity[0],mGravity[2]));
        String dir = "1";

        if(angle>angleSet){
            dir = "0";
        }
        int str = (int)((Math.abs(angle-angleSet)/(angleSet*2.0/3.0))*7);
        if(str>9){str=9;};
        if(str<5){str*=1.5;}
        dir = dir+Integer.toString(str);

        editText.setText(dir+"000");
        Date d = new Date();
        if(d.getTime()/1000-time>1){
            angleSet+=Math.PI/12;
            time = d.getTime()/1000;

            SendThread q = new SendThread(dir+"000",display,c);

            q.start();
            display.append(dir+"000");
        }
    }

    public void compass(View view){
        startActivity(new Intent(MainActivity.this, CompassActivity.class));

    }
    class SendThread extends Thread {
        long minPrime;
        Conn c;
        TextView d;
        String str;

        SendThread(String dat, TextView dis, Conn q) {
            c = q;
            d = dis;
            str = dat;
        }

        public void run() {
            try{
                c.out.write(str.getBytes(Charset.forName("UTF-8")));
                //d.append(str);
            }catch(Exception E){
                //d.append("sadness");
            }
        }
    }

    /*
     * This handler will be passed to UsbService. Data received from serial port is displayed through this handler
     */


}