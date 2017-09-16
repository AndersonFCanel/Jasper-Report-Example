package com.hemendra.datasource.dynamicquery;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import com.hemendra.util.ExportFile;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Hemendra
 */
public class DynamicReportBuilder {
    private static final String SQL_QUERY = "SELECT actor_id, first_name, last_name, last_update FROM actor";
    public static void main(String[] args) throws ClassNotFoundException, JRException, IOException {
        FastReportBuilder fastReportBuilder = new FastReportBuilder();

        DynamicReport dynamicReport = fastReportBuilder
                .addColumn("actor_id", "actor_id", String.class.getName(), 30)
                .addColumn("first_name", "first_name", String.class.getName(), 30) // title, property to show, class of the property, width
                .addColumn("last_name", "last_name", String.class.getName(), 50)
                .addColumn("last_update", "last_update", String.class.getName(), 50)
                .addGroups(2)   // Group by the first two columns
                .setQuery(SQL_QUERY, "SQL")
                .setTitle("16th September sales report")
                .setSubtitle("This report was generateed at" + new Date())
                .setUseFullPageWidth(true) // //make colums to fill the page width
                .build();

        //Get the database connection
        Connection connection = prepareDatabseConnection();

        JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), connection, new HashMap<String, Object>());
        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        JasperViewer.viewReport(jasperPrint);
        System.out.println(bytes.length);
        ExportFile.exportFile(bytes, "Dynamic-Report.pdf");


    }

    private static Connection prepareDatabseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dvdrental", "postgres", "postgres");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
