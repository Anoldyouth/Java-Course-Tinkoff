package edu.hw8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DictionaryServer {
    private final static Logger LOGGER = LogManager.getLogger();
    public final static String NOT_FOUND = "Извините, цитата по Вашему запросу не найдена";
    private final Dictionary dictionary;
    private final int maxConnections;
    private final int port;

    public DictionaryServer(int maxConnections, int port) {
        dictionary = new PropertiesDictionary();
        this.maxConnections = maxConnections;
        this.port = port;
    }

    public DictionaryServer(int maxConnections, int port, String properties) {
        dictionary = new PropertiesDictionary(properties);
        this.maxConnections = maxConnections;
        this.port = port;
    }

    public void serve() {
        try (ExecutorService executorService = Executors.newFixedThreadPool(maxConnections);
             ServerSocket serverSocket = new ServerSocket(port)
        ) {
            while (!(Thread.currentThread().isInterrupted())) {
                Socket clientSocket = serverSocket.accept();

                executorService.submit(() -> process(clientSocket));
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void process(Socket clientSocket) {
        LOGGER.info("connected");

        try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream outputStream = clientSocket.getOutputStream()) {

            while (!clientSocket.isClosed()) {
                String request = inputStream.readLine();

                if (request.isEmpty()) {
                    break;
                }

                if (request.equals("exit")) {
                    outputStream.write("До встречи!".getBytes());
                    break;
                }

                String answer = dictionary.search(request);
                if (answer == null) {
                    answer = NOT_FOUND;
                }

                outputStream.write(answer.getBytes());
            }

            clientSocket.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.info("disconnected");
    }
}
