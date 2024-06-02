package org.oceanobe.utils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

@UtilityClass
public class FileUtils {

    public static void writeToFile(Path path, String sequence) throws Exception{

        Files.writeString(path, sequence);
    }

    public static String readFile(Path path, Charset charset) throws IOException {
        return Files.readString(path, charset);
    }

    public static Long copyFile(Path path, OutputStream outputStream) throws IOException {
        return Files.copy(path, outputStream);
    }

    public static Long copyFile(Path path, InputStream inputStream) throws IOException {
        return Files.copy(inputStream, path);
    }
}
