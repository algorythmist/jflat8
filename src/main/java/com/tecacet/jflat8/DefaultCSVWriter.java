package com.tecacet.jflat8;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;

public class DefaultCSVWriter {

    private final CSVPrinter csvPrinter;

    public DefaultCSVWriter(FileWriter fw) throws IOException {
        csvPrinter = new CSVPrinter(fw, CSVFormat.DEFAULT);
    }

    public void writeNext(String[] tokens) throws IOException {
        csvPrinter.printRecord(tokens);
    }

    public void close() throws IOException {
        csvPrinter.close();
    }
}
