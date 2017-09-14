package com.hemendra.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ExportFile {
    public static void exportFile(byte[] content, String fileName) throws IOException {
        OutputStream out = new FileOutputStream(fileName);
        out.write(content);
        out.close();
    }
}
