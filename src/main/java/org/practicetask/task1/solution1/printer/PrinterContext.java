package org.practicetask.task1.solution1.printer;

public class PrinterContext {

    public NumberPrinter getNumberPrinter(String typeOfNumber, int maxValue, SharedResource lock) {
        if (typeOfNumber.equals("odd")) {
            return new OddNumberPrinter(maxValue, lock);
        }
        else if (typeOfNumber.equals("even")) {
            return new EvenNumberPrinter(maxValue, lock);
        }

        throw new IllegalArgumentException("Type of number doesn't exist");
    }
}
