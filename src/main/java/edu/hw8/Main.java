package edu.hw8;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> servers = Stream
                .generate(() -> new Thread(() -> new DictionaryServer(2, 8080).serve()))
                .limit(1)
                .toList();
        servers.forEach(Thread::start);

        List<Thread> clients = Stream
                .generate(() -> new Thread(() -> {
                    try (DictionaryClient client = new DictionaryClient("localhost", 8080);) {
                        for (int i = 0; i < 5; i++) {
                            System.out.println(client.get("test"));
                            sleep(1000);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }))
                .limit(3)
                .toList();
        clients.forEach(Thread::start);

        for (Thread thread: servers) {
            thread.join();
        }
    }
}
