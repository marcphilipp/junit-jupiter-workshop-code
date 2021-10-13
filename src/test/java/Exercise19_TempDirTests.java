import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class Exercise19_TempDirTests {

    private Path tempDir;

    @BeforeEach
    void createTempDir() throws IOException {
        tempDir = Files.createTempDirectory("junit-");
    }

    @AfterEach
    void deleteTempDir() throws IOException {
        Files.walkFileTree(tempDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Test
    void writeAndReadFile() throws Exception {
        Path testFile = tempDir.resolve("test.txt");

        Files.write(testFile, asList("foo", "bar"));

        List<String> actualLines = Files.readAllLines(testFile);
        assertIterableEquals(asList("foo", "bar"), actualLines);
    }
}
