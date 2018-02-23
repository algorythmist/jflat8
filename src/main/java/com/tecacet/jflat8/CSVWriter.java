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

    // TODO allow class inheritance in conversion
    private Map<Class<?>, Function<? extends Object, String>> converters = new HashMap<Class<?>, Function<?, String>>();

    public CSVWriter(String[] properties) {
        this(null, properties);
    }

    public CSVWriter(String[] header, String[] properties) {
        super();
        this.properties = properties;
        this.header = header;
    }

    public void write(Appendable appendable, Collection<T> beans) throws IOException {
        CSVPrinter csvPrinter = new CSVPrinter(appendable, CSVFormat.DEFAULT);
        if (header != null) {
            csvPrinter.printRecord(header);
        }
        for (T bean : beans) {
            String[] tokens = tokenize(bean);
            csvPrinter.printRecord(tokens);
        }
        csvPrinter.close();
    }

    public void writeToFile(String filename, Collection<T> beans) throws IOException {
        FileWriter fw = new FileWriter(filename);
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
