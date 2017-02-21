package com.example.storm.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;

class ServerThread implements Runnable {
    public Socket socket;
    public BufferedReader br = null;


    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        //初始化该Socket对应的输入流
        br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
    }

    @Override
    public void run() {
        try {
            String content;
            while ((content = readFromClient()) != null) {
                for (Iterator<Socket> iterator = MyServer.sockets.iterator(); iterator.hasNext(); ) {
                    Socket socket = iterator.next();
                    try {
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write((content + "\n").getBytes("utf-8"));
                    } catch (SocketException e) {
                        iterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String readFromClient() {
        try {
            return br.readLine();
            //如果捕获到异常，表明改Socket对应的客户端已经关闭
        } catch (IOException e) {
            e.printStackTrace();
            MyServer.sockets.remove(socket);

        }
        return null;
    }
}

public class MyServer {
    public static ArrayList<Socket> sockets = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            while (true) {
                Socket socket = serverSocket.accept();  //此代码会阻塞,将一直等待别人的连接
                sockets.add(socket);
                new Thread(new ServerThread(socket)).start();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
