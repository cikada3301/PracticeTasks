package org.firsttask;

import org.firsttask.model.BusinessObject;
import org.firsttask.writer.CsvWriter;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<BusinessObject> businessObjects = List.of(
                new BusinessObject(1L, "asd", "dfgdfgdfg"),
                new BusinessObject(2L, null, "dasasdsa")
        );

        new CsvWriter().writeToFile(businessObjects, "test.csv");
    }
}
