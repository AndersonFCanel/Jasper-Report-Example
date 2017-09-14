package com.hemendra.datasource.connection;

import com.hemendra.util.ExportFile;
import com.hemendra.util.JasperCustomReport;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ConnectionDataSourceWithParameterTest {
    private static final String CONNCTION_JRXML = "connection-datasource-with-parameter.jrxml";
    private static final Logger LOGGER = Logger.getLogger(ConnectionDataSourceWithParameterTest.class.getSimpleName());
    public static void main(String[] args) throws JRException {
        //Send parameters to jasper report
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put("taskId", "52ea1ed1-54dd-11e7-9d24-eca86bf08c22");

        //Pdf generation
        JasperCustomReport jasperCustomReport = new JasperCustomReport();
        byte[] bytes = jasperCustomReport.exportReportToPdf(reportParameters, CONNCTION_JRXML, null);

        try {
            ExportFile.exportFile(bytes, "connection-with-parameter-example.pdf");
            LOGGER.info("File Successfully Generated");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
