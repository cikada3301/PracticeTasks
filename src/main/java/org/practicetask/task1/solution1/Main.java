package org.practicetask.task1.solution1;

import org.practicetask.task1.solution1.printer.PrinterContext;
import org.practicetask.task1.solution1.printer.SharedResource;

public class Main {

    public static void main(String[] args) {
        PrinterContext printerContext = new PrinterContext();

        final SharedResource lock = new SharedResource(3);

        Thread thread1 = new Thread(printerContext.getNumberPrinter("even", 15, lock), "EvenPrinter");
        Thread thread2 = new Thread(printerContext.getNumberPrinter("odd", 15, lock), "OddPrinter");

        thread1.start();
        thread2.start();
    }
}