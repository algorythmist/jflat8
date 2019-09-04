package com.tecacet.jflat8;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import jodd.bean.BeanUtil;

// TODO extract interface
public class CSVWriter<T> {

    private final BeanUtil beanUtil = BeanUtil.declaredForcedSilent;
    private final String[] header;
    private final String[] properties;
    private final CSVFormat csvFormat;

    // TODO allow class inheritance in conversion
    private Map<Class<?>, Function<? extends Object, String>> converters = new HashMap<Class<?>, Function<?, String>>();

    public CSVWriter(String[] properties) {
        this(null, properties);
    }

    public CSVWriter(String[] header, String[] properties) {
        this(header, properties,  CSVFormat.DEFAULT);
    }

    public CSVWriter(String[] header, String[] properties, CSVFormat csvFormat) {
        super();
        this.header = header;
        this.properties = properties;
        this.csvFormat = csvFormat;
    }

    /**
     * Write a bean to an open Appendable. The user is responsible for closing the Appendable.
     * 
     * @param appendable
     * @param bean
     * @throws IOException
     */
    public void write(Appendable appendable, T bean) throws IOException {
        CSVPrinter csvPrinter = new CSVPrinter(appendable, csvFormat);
        String[] tokens = tokenize(bean);
        csvPrinter.printRecord(tokens);
    }

    /**
     * Write a collection to an open Appendable. The user is responsible for closing the Appendable.
     * 
     * @param appendable
     * @param beans
     * @throws IOException
     */
    public void write(Appendable appendable, Collection<T> beans) throws IOException {
        CSVPrinter csvPrinter = new CSVPrinter(appendable, csvFormat);
        for (T bean : beans) {
            String[] tokens = tokenize(bean);
            csvPrinter.printRecord(tokens);
        }
    }

    private void writeHeader(Appendable appendable) throws IOException {
        if (header != null) {
            CSVPrinter csvPrinter = new CSVPrinter(appendable, csvFormat);
            csvPrinter.printRecord(header);
        }
    }

    public void writeToFile(String filename, Collection<T> beans) throws IOException {
        FileWriter fw = new FileWriter(filename);
        writeHeader(fw);
        write(fw, beans);
        fw.close();
    }

    public <C> void registerConverter(Class<C> type, Function<C, String> converter) {
        converters.put(type, converter);
    }

    private String[] tokenize(T bean) {
        return IntStream.range(0, properties.length).mapToObj(i -> beanUtil.getProperty(bean, properties[i]))
                .map(v -> toString(v)).toArray(String[]::new);
    }

    private String toString(Object value) {
        if (value == null) {
            return null;
        }
        // TODO question marks?
        Function converter = converters.get(value.getClass());
        if (converter == null) {
            return value.toString();
        }
        return (String) converter.apply(value);
    }
}
