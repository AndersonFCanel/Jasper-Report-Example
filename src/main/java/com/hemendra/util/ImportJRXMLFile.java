package com.hemendra.util;

import java.io.FileNotFoundException;
import java.net.URL;

/**
 * @author Hemendra
 */
public class ImportJRXMLFile {
    public static URL getJRXMLReportUrl(String reportFilePath) throws FileNotFoundException {
        URL reportPath = JasperReportUtil.class.getClassLoader().getResource(reportFilePath);
        //URL reportPath = ResourceUtil.findClasspathResource(reportFilePath);
        if (reportPath == null) {
            throw new FileNotFoundException("File(" + reportFilePath + ") is not found.");
        }
        return reportPath;
    }
}
