package edu.project4;

import edu.helpers.FileStructureHelper;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenerateImageActionTest {
    private final static String DIRECTORY = "images";

    @BeforeEach
    @AfterEach
    void reset() throws Exception {
        FileStructureHelper.deleteStructure(DIRECTORY);
    }

    @Test
    void success() {
        assertDoesNotThrow(() -> GenerateImageAction.generate("src/test/java/edu/project4/config.json"));
    }
}
