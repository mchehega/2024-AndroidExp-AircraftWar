package com.example.aircarftwar2024.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.example.aircarftwar2024.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    boolean music;
    private Handler handler;
    public static String fromserver;
    public static PrintWriter writer;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, OfflineActivity.class);
        Intent online = new Intent(MainActivity.this, OnlinePlay.class);

        Button on_audio = (Button) findViewById(R.id.on_audio);
        Button off_audio = (Button) findViewById(R.id.off_audio);
        Button start = (Button) findViewById(R.id.start);
        Button coop = (Button) findViewById(R.id.coop);
        ActivityManager.getActivityManager().addActivity(this);
        handler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                if (msg.what == 1){
                    if (msg.obj.equals("start")){
                        dialog.dismiss();
                        System.out.println("start");
                        startActivity(online);
                    }
                }
            }
        };
        on_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music = true;
            }
        });

        off_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music = false;
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("music",music);
                startActivity(intent);
            }
        });
        
        coop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waiting();
                new Thread(new NetConn(handler)).start();
            }
        });
    }

    private void waiting(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("匹配中...请等待");
        dialog = builder.create();
        dialog.show();
        return;
    }

    protected class NetConn extends Thread{
        private BufferedReader in;
        private Handler toClientHandler;
        public NetConn(Handler handler){
            this.toClientHandler = handler;
        }
        @Override
        public void run(){
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress("10.0.2.2", 9999), 5000);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
                writer = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream(),"utf-8")),true);
                Thread receiveServerMsg = new Thread(){
                    @Override
                    public void run(){
                        try {
                            while (true)
                            {
                                if ((fromserver = in.readLine()) != null){
                                    Message msg = new Message();
                                    msg.what = 1;
                                    msg.obj = fromserver;
                                    toClientHandler.sendMessage(msg);
                                }
                                sleep(250);
                            }
                        } catch(IOException ex){
                            ex.printStackTrace();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                };
                receiveServerMsg.start();
            } catch(UnknownHostException ex){
                ex.printStackTrace();
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
}