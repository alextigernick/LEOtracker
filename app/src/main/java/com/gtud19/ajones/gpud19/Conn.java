package com.gtud19.ajones.gpud19;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by ajones on 4/30/2017.
 *
 */
public class Conn extends AsyncTask<String,Void,Conn> {
    public Socket sock = null;
    public OutputStream out = null;
    public Conn doInBackground(String... args){
        boolean blan = true;

        int q  = 100;
        while(blan){

            try{
                if(q>10){
                    q=2;
                }

                sock = new Socket("192.168.2."+q, 99);
                blan = false;
                out = sock.getOutputStream();
            }
            catch(Exception e){
                q++;
            }}
        return this;
    }

}
