package edu.hw8;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DictionaryClient implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int BUF_SIZE = 1024;
    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    @Override
    public void close() {
        try (inputStream; outputStream; socket) {
            socket.getOutputStream().write("exit\n".getBytes());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public DictionaryClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    public String get(String keyword) {
        try {
            outputStream.write((keyword + "\n").getBytes());

            byte[] buffer = new byte[BUF_SIZE];
            int bytesRead = inputStream.read(buffer);
            return new String(buffer, 0, bytesRead);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());

            return "Ошибка подключения, повторите позже";
        }
    }
}
