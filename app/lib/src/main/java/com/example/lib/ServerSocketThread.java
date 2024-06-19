package com.example.lib;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

public class ServerSocketThread extends Thread{
    private BufferedReader in;
    private PrintWriter pw;
    private Socket socket;

    private int user;

    private static int score1,score2;
    private static int alive1=1,alive2=1;

    public static int count;

    public ServerSocketThread(Socket socket,int user){
        this.socket = socket;
        this.user = user;
    }

    @Override
    public void run(){
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            pw = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);

            String content;

            while (count != 2)
            {
                //System.out.println("user"+user+" "+count);
                sleep(100);
            }
            pw.println("start");
            System.out.println("user"+user+" start!");
            //开始游戏
            while (true)
            {
//                if (user==1)
//                {
//                    System.out.println("user1"+score1+""+alive1);
//                }
//                if (user==2)
//                {
//                    System.out.println("user2"+score2+""+alive2);
//                }
                //读
                //System.out.println("read");
                if((content = in.readLine()) != null) {
                    //System.out.println("read000");
                    String[] a = content.split(",");
                    int score = Integer.parseInt(a[0]);
                    //System.out.println("read  "+score);
                    int alive = Integer.parseInt(a[1]);
                    //4.和客户端通信
                    if (user==1)
                    {
                        score1 = score;
                        alive1 =alive;
                    }
                    if (user==2)
                    {
                        score2 = score;
                        alive2 =alive;
                    }
                }
                //写
                //System.out.println("write");
                if (user==1)
                {
                    pw.println(score2+","+alive2);
                    //System.out.println("write1");
                    sleep(50);
                }
                if (user==2)
                {
                    pw.println(score1+","+alive1);
                    System.out.println(score1);
                    sleep(50);
                }

//                if (alive2==0 && alive1==0)
//                {
//                    count=0;
//                    break;
//                }
            }
        } catch (IOException e) {

            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
