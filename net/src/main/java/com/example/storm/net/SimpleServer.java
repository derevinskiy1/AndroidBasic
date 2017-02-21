package com.example.storm.net;


import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket  服务端
 */
public class SimpleServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            while (true) {
                Socket socket = serverSocket.accept();
                OutputStream os = socket.getOutputStream();
                System.out.println(socket.getInetAddress());
                os.write("您好,你收到了服务器的新祝福!\n".getBytes("utf-8"));
                os.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
