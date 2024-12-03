package ru.otus.server;

import ru.otus.server.methods.DispatcherServlet;
import ru.otus.server.methods.HttpRequests;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server extends Thread {
    private final byte[] buffer = new byte[8192];
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    private int port;
    private DispatcherServlet dispatcherServlet;
    private volatile boolean running = true;

    public Server(int port) {
        this.port = port;
    }


    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            this.dispatcherServlet = new DispatcherServlet();
            while (running) {
                try {
                    Socket socket = serverSocket.accept();
                    executorService.submit(() -> {
                        try {
                            handleRequest(socket);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (!running) {
                            executorService.shutdownNow();
                            try {
                                serverSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });

                } catch (SocketException e) {
                    System.out.println("Соединение закрыто");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void handleRequest(Socket socket) throws IOException {
        try (socket) {
            int n = socket.getInputStream().read(buffer);
            if (n > 0) {
                String rawRequest = new String(buffer, 0, n);
                HttpRequests request = new HttpRequests(rawRequest);
                request.info();
                if (request.getRouteKey().equals("GET /shutdown")) {
                    dispatcherServlet.execute(request, socket.getOutputStream());
                    running = false;
                    return;
                }
                if (!request.getRouteKey().isEmpty()) {
                    dispatcherServlet.execute(request, socket.getOutputStream());
                } else {
                    sendErrorResponse(socket.getOutputStream(), 500);
                }
            }

        }

    }


    private void sendErrorResponse(OutputStream outputStream, int statusCode) throws IOException {
        String response = "HTTP/1.1 " + statusCode + " Internal Server Error\r\n\r\n";
        outputStream.write(response.getBytes());
    }

}
