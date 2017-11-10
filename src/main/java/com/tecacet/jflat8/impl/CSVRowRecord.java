package com.tecacet.jflat8.impl;

import org.apache.commons.csv.CSVRecord;

import com.tecacet.jflat8.RowRecord;

/**
 * RowRecord implementation for Commons CSV
 * 
 * @author dimitri
 *
 */
public class CSVRowRecord implements RowRecord {

    private final CSVRecord csvRecord;

    public CSVRowRecord(CSVRecord csvRecord) {
        super();
        this.csvRecord = csvRecord;
    }

    @Override
    public String get(int index) {
        return csvRecord.get(index);
    }

    @Override
    public String get(String name) {
        if (csvRecord.isSet(name)) {
            return csvRecord.get(name);
        }
        return null;
    }

    @Override
    public int size() {
        return csvRecord.size();
    }

    @Override
    public long getRowNumber() {
        return csvRecord.getRecordNumber();
    }

}
