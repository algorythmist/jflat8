package com.tecacet.jflat8.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Load a resource from file or classpath
 * 
 * @author dimitri
 *
 */
public class ResourceLoader {

    public InputStream loadResource(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            return new FileInputStream(file);
        }
        InputStream is = ClassLoader.getSystemResourceAsStream(path);
        if (is == null) {
            throw new IOException("Resource does not exist: " + path);
        }
        return is;
    }
}
