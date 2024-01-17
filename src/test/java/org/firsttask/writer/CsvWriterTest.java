package org.firsttask.writer;


import org.firsttask.exception.BusinessObjectsNullException;
import org.firsttask.model.BusinessObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CsvWriterTest {

    private final List<BusinessObject> businessObjects = List.of(
            new BusinessObject(1L, "asd", "dfgdfgdfg"),
            new BusinessObject(2L, null, "dasasdsa")
    );

    private final String filePath = "test.csv";

    private final CsvWriter csvWriter = new CsvWriter();

    @Test
    public void testWriteToFileOnCountLines() throws IOException {

        Path path = Path.of(filePath);

        csvWriter.writeToFile(businessObjects, filePath);

        int expectedLines = 3;

        List<String> lines = Files.readAllLines(path);

        Assertions.assertEquals(expectedLines, lines.size());

        Files.deleteIfExists(path);
    }

    @Test
    public void testWriteToFileOnData() throws IOException {

        Path path = Path.of(filePath);

        csvWriter.writeToFile(businessObjects, filePath);

        List<String> lines = Files.readAllLines(path);

        Assertions.assertEquals("id-name-lastname", lines.get(0));
        Assertions.assertEquals("1-asd-dfgdfgdfg", lines.get(1));
        Assertions.assertEquals("2- dasasdsa", lines.get(2));

        Files.deleteIfExists(path);
    }

    @Test
    public void testWriteToFileOnFail() {
        Assertions.assertThrows(BusinessObjectsNullException.class, () -> csvWriter.writeToFile(null, filePath));
    }
}