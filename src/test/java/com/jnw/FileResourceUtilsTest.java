package com.jnw;

import com.jnw.exception.JnwIllegalArgumentException;
import lombok.extern.java.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static com.jnw.FileResourceUtils.*;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

@Log
public class FileResourceUtilsTest {

    @Test
    @DisplayName("getFileFromResource() -> Test null fileName")
    public void givenNullFileName_whenGetFileFromResource_thenExceptionIsThrown() {

        var exception = assertThrows(JnwIllegalArgumentException.class,
                () -> getFileFromResource(null)
        );

        assertEquals("fileName field cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("getFileFromResource() -> Test empty fileName")
    public void givenEmptyFileName_whenGetFileFromResource_thenExceptionIsThrown() {

        var exception = assertThrows(JnwIllegalArgumentException.class,
                () -> getFileFromResource("")
        );

        assertEquals("fileName field cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("getFileFromResource() -> Test file not present in resource folder")
    public void givenWrongFileName_whenGetFileFromResource_thenExceptionIsThrown() {

        var exception = assertThrows(JnwIllegalArgumentException.class,
                () -> getFileFromResource("data/non_existing_file.txt")
        );

        assertEquals("File not found: 'data/non_existing_file.txt'", exception.getMessage());
    }

    @Test
    @DisplayName("getFileFromResource() -> Test successful file retrieval from resource folder")
    public void givenCorrectFileName_whenGetFileFromResource_thenFileIsRetrievedSuccessfully() {

        try {
            var existingFile = getFileFromResource("data/a.txt");
            assertTrue(existingFile.exists());

        } catch (URISyntaxException ex) {
            log.severe(ex.getMessage());
        }
    }

    @Test
    @DisplayName("writeItemOnEachLine() -> Test null listOfItems")
    public void givenNullListOfItems_whenWriteItemOnEachLine_thenExceptionIsThrown() {

        var exception = assertThrows(JnwIllegalArgumentException.class,
                () -> writeItemOnEachLine(null, true)
        );

        assertEquals("listOfItems cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("writeItemOnEachLine() -> Test empty listOfItems")
    public void givenEmptyListOfItems_whenWriteItemOnEachLine_thenExceptionIsThrown() {

        var exception = assertThrows(JnwIllegalArgumentException.class,
                () -> writeItemOnEachLine(emptyList(), true)
        );

        assertEquals("listOfItems cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("writeItemOnEachLine() -> Test items has been written in file")
    public void givenListOfItems_whenWriteItemOnEachLine_thenItemsAreWrittenOnEachLine() throws URISyntaxException {

        writeItemOnEachLine(NameWriter.NAMES, false);

        var aFile = getFileFromResource(FILE_A);

        assertTrue(aFile.exists());
        assertTrue(aFile.length() > 0);
    }

    @Test
    @DisplayName("writeObjectFieldsAsCsvOnEachLine() -> Test null listOfCsvWritable")
    public void givenNullListOfItems_whenWriteObjectFieldsAsCsvOnEachLine_thenExceptionIsThrown() {

        var exception = assertThrows(JnwIllegalArgumentException.class,
                () -> writeObjectFieldsAsCsvOnEachLine(null, true)
        );

        assertEquals("listOfCsvWritable cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("writeObjectFieldsAsCsvOnEachLine() -> Test empty listOfCsvWritable")
    public void givenEmptyListOfItems_whenWriteObjectFieldsAsCsvOnEachLine_thenExceptionIsThrown() {

        var exception = assertThrows(JnwIllegalArgumentException.class,
                () -> writeObjectFieldsAsCsvOnEachLine(emptyList(), true)
        );

        assertEquals("listOfCsvWritable cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("writeObjectFieldsAsCsvOnEachLine() -> Test items has been written in file")
    public void givenListOfItems_whenWriteObjectFieldsAsCsvOnEachLine_thenItemsAreWrittenOnEachLine() throws URISyntaxException {

        writeObjectFieldsAsCsvOnEachLine(NameWriter.PERSONS, false);

        var bFile = getFileFromResource(FILE_B);

        assertTrue(bFile.exists());
        assertTrue(bFile.length() > 0);
    }
}
