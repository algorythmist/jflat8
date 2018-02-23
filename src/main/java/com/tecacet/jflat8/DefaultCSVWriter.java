package com.tecacet.jflat8;

import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class DefaultCSVWriter {

    private final CSVPrinter csvPrinter;

    public DefaultCSVWriter(Appendable appendable) throws IOException {
        csvPrinter = new CSVPrinter(appendable, CSVFormat.DEFAULT);
    }

    public void writeNext(String[] tokens) throws IOException {
        csvPrinter.printRecord(tokens);
    }

    public void close() throws IOException {
        csvPrinter.close();
    }
}
