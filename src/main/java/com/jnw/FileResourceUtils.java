package com.jnw;

import com.jnw.dto.FieldsAsCsvWritable;
import com.jnw.exception.JnwIllegalArgumentException;
import lombok.extern.java.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.Objects.isNull;

@Log
public class FileResourceUtils {

    public static final String FILE_A = "data/a.txt";
    public static final String FILE_B = "data/b.txt";

    public static File getFileFromResource(String fileName) throws URISyntaxException {

        if (isNull(fileName) || fileName.isEmpty()) {
            throw new JnwIllegalArgumentException("fileName field cannot be null or empty");
        }

        var resourceFile = Thread.currentThread()
                .getContextClassLoader()
                .getResource(fileName);

        if (isNull(resourceFile)) {
            throw new JnwIllegalArgumentException(String.format("File not found: '%s'", fileName));
        }

        return new File(resourceFile.toURI());
    }

    public static void writeItemOnEachLine(List<String> listOfItems, boolean append) {

        if (isNull(listOfItems) || listOfItems.isEmpty()) {
            throw new JnwIllegalArgumentException("listOfItems cannot be null or empty");
        }

        try (var fileWriter = new BufferedWriter(new FileWriter(getFileFromResource(FILE_A), append))) {

            listOfItems.forEach(item -> {
                try {
                    fileWriter.write(item);
                    fileWriter.newLine();
                } catch (IOException ex) {
                    log.severe(ex.getMessage());
                }
            });
            fileWriter.newLine();

        } catch (IOException | URISyntaxException ex) {
            log.severe(ex.getMessage());
        }
    }

    public static void writeObjectFieldsAsCsvOnEachLine(List<? extends FieldsAsCsvWritable> listOfCsvWritable, boolean append) {

        if (isNull(listOfCsvWritable) || listOfCsvWritable.isEmpty()) {
            throw new JnwIllegalArgumentException("listOfCsvWritable cannot be null or empty");
        }

        try (var fileWriter = new BufferedWriter(new FileWriter(getFileFromResource(FILE_B), append))) {

            listOfCsvWritable.forEach(listItem -> {
                try {
                    fileWriter.write(
                            String.join(",", listItem.getAllFieldsAsList())
                    );
                    fileWriter.newLine();
                } catch (IOException ex) {
                    log.severe(ex.getMessage());
                }
            });
            fileWriter.newLine();

        } catch (IOException | URISyntaxException ex) {
            log.severe(ex.getMessage());
        }
    }
}
