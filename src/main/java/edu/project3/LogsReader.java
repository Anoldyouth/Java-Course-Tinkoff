package edu.project3;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import static java.net.http.HttpClient.newHttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

public class LogsReader {
    public static Stream<LogString> readLogsFromUrl(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            var response = newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString()).body();

            return Arrays.stream(response.split("\n"))
                    .map(LogsParser::parseString);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            return Stream.empty();
        }
    }

    public static Stream<LogString> readLogsFromFiles(String glob) {

    }
}
