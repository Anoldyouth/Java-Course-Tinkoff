package edu.project1.dictionary;

import edu.project1.exceptions.EmptyFileException;
import edu.project1.exceptions.OpeningFileException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class FileDictionary implements Dictionary {
    private final String filepath;

    public FileDictionary(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public @NotNull String randomWord() throws Exception {
        try {
            ArrayList<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            if (lines.isEmpty()) {
                throw new EmptyFileException(filepath);
            }

            Random random = new Random();

            return lines.get(random.nextInt(lines.size()));
        } catch (IOException e) {
            throw new OpeningFileException(filepath);
        }
    }
}
