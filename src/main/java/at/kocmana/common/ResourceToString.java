package at.kocmana.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceToString {

    private ResourceToString() {}


    private static final String BASE_PATH = "src/main/resources";

    public static String from(String path, String fileName) {
        var file = Paths.get(BASE_PATH, path, fileName);

        try {
            return Files.readString(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
