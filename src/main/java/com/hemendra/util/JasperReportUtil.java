package com.hemendra.util;

/**
 * Created by BIP034 on 7/19/2017.
 */

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class JasperReportUtil {

    public static JasperReport getCompiledReport(String reportFilePath) throws JRException, IOException
    {
        URL reportPath = ImportJRXMLFile.getJRXMLReportUrl(reportFilePath);
        return JasperCompileManager.compileReport( reportPath.openStream());
    }



    public static JasperDesign getJRXMLDesign(String reportFilePath) throws FileNotFoundException, JRException {
        URL reportUrl = ImportJRXMLFile.getJRXMLReportUrl(reportFilePath);
        JasperDesign jasperDesign = JRXmlLoader.load(reportUrl.toString());
        return jasperDesign;
    }

}
