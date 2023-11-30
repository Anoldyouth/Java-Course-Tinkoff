package edu.hw8;

import edu.helpers.FileStructureHelper;
import static java.lang.Thread.sleep;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DictionaryClientServerTest {
    private final static String DIRECTORY = "test_dictionary_client_server";
    private final static String PROPERTIES = "test.properties";
    private final static String KEY = "test";
    private final static String VALUE = "test message";

    @BeforeAll
    static void prepare() throws Exception {
        FileStructureHelper.createStructure(DIRECTORY);

        Path properties = Path.of(DIRECTORY, PROPERTIES);
        Files.createFile(properties);
        Files.writeString(properties, KEY + "=" + VALUE);
    }

    @AfterAll
    static void reset() throws Exception {
        FileStructureHelper.deleteStructure(DIRECTORY);
    }

    @Test
    void successAnswers() throws Exception {
        Thread serverThread = new Thread(() -> {
            DictionaryServer server = new DictionaryServer(1, 8080, Path.of(DIRECTORY, PROPERTIES).toString());
            server.serve();
        });

        serverThread.start();

        sleep(500);

        try (DictionaryClient client = new DictionaryClient("localhost", 8080)) {
            assertThat(client.get(KEY)).isEqualTo(VALUE);
            assertThat(client.get("key")).isEqualTo(DictionaryServer.NOT_FOUND);
        }

        serverThread.interrupt();
    }

    @Test
    void waiting() throws Exception {
        Thread serverThread = new Thread(() -> {
            DictionaryServer server = new DictionaryServer(1, 8081, Path.of(DIRECTORY, PROPERTIES).toString());
            server.serve();
        });

        serverThread.start();

        sleep(500);

        try (DictionaryClient client = new DictionaryClient("localhost", 8081)) {
            Thread clientThread = new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    client.get(KEY);
                    try {
                        sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                }
            });

            Thread waitingThread = new Thread(() -> {
                try (DictionaryClient waiting = new DictionaryClient("localhost", 8081)) {
                    waiting.get(KEY);
                } catch (Exception ignored) {
                }
            });

            clientThread.start();

            sleep(500);

            waitingThread.start();

            sleep(1000);

            assertThat(waitingThread.isAlive()).isTrue();

            clientThread.interrupt();
            waitingThread.interrupt();
        }

        serverThread.interrupt();
    }
}
