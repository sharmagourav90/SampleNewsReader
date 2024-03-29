package com.sample.news.testutil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ResponseGenerator {
    private String basePath;
    private File file;

    public ResponseGenerator(String pathUnderFixturesDirectory) {
        this.basePath = "src/test/assets/";
        this.file = new File(this.basePath, pathUnderFixturesDirectory);
    }

    public String readAll() throws IOException {
        char[] contents = new char[(int) this.file.length()];
        final int read = new FileReader(file).read(contents);
        return new String(contents);
    }
}
