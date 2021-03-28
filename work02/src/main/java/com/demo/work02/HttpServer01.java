package com.demo.work02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * socket模拟
 * @author dengchao
 */
public class HttpServer01 {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 4,
                Runtime.getRuntime().availableProcessors() * 4,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        final ServerSocket serverSocket =  new ServerSocket(8081);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                executorService.execute(() -> service(socket) );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void service(Socket socket) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello.nio";
            printWriter.println("Content-length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
