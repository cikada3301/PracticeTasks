package org.firsttask.writer;

import org.firsttask.exception.BusinessObjectsNullException;
import org.firsttask.writer.annotations.CsvField;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class CsvWriter {

    private final String separator = "-";

    public void writeToFile(List<?> objects, String filePath) {

        if (objects == null) {
            throw new BusinessObjectsNullException("List of BusinessObjects could not be null");
        }

        int indexToGetHeaders = 0;

        try (FileWriter fileWriter = new FileWriter(filePath)) {

            writeHeaders(objects.get(indexToGetHeaders), fileWriter);

            for (Object object : objects) {
                writeRow(object, fileWriter);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeHeaders(Object object, FileWriter fileWriter) throws IOException {

        Field[] fields = object.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {

            if (fields[i].isAnnotationPresent(CsvField.class)) {

                String csvFieldName = fields[i].getAnnotation(CsvField.class).name();

                StringBuilder nameField = new StringBuilder(csvFieldName.isEmpty() ? fields[i].getName() : csvFieldName);

                if (i < fields.length - 1) {
                    nameField.append(separator);
                }

                fileWriter.append(nameField);
            }

        }

        fileWriter.append("\n");
    }

    private void writeRow(Object object, FileWriter fileWriter) throws IOException {

        Field[] fields = object.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(CsvField.class)) {

                fields[i].setAccessible(true);

                try {
                    Object value = fields[i].get(object);

                    if (value == null) {
                        fileWriter.append(" ");
                    } else {
                        fileWriter.append(i < fields.length - 1 ? value.toString().concat(separator) : value.toString());
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        fileWriter.append("\n");
    }
}
