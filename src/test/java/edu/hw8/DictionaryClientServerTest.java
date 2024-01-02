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
    private static final String DIRECTORY = "test_dictionary_client_server";
    private static final String PROPERTIES = "test.properties";
    private static final String KEY = "test";
    private static final String VALUE = "test message";

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
        // given
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

            // when
            clientThread.start();

            sleep(500);

            waitingThread.start();

            sleep(1000);

            // then
            assertThat(waitingThread.isAlive()).isTrue();

            // clear after all
            clientThread.interrupt();
            waitingThread.interrupt();
        }

        serverThread.interrupt();
    }
}
