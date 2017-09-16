package com.hemendra.datasource.dynamicquery;

import com.hemendra.util.ExportFile;
import com.hemendra.util.ImportJRXMLFile;
import com.hemendra.util.JasperReportUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Hemendra
 */
public class DynamicSQLQuery {
    private static final String JRXML_FILE= "jasper-dynamic-query.jrxml";
    private static final String SQL_QUERY = "SELECT actor_id, first_name, last_name, last_update FROM actor";
    public static void main(String[] args) throws IOException, JRException, SQLException {

        //Get the File
        InputStream inputStream = DynamicSQLQuery.class.getClassLoader().getResource(JRXML_FILE).openStream();

        //Design phase -LifeCycle:1
        JasperDesign jrxmlDesign = JRXmlLoader.load(inputStream);

        //Set Query to the JRXML
        JRDesignQuery jrDesignQuery = generateJasperQuery(SQL_QUERY);
        jrxmlDesign.setQuery(jrDesignQuery);

        //Compile the report - Lifecycle:2
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlDesign);

        //Get the database connection
        Connection connection = prepareDatabseConnection();

        //Fill the report Lifecycle: 3
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String, Object>(), connection);

        //close database connection
        connection.close();

        //Export the report
        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

        //My utility method which creates file from byte content
        ExportFile.exportFile(bytes, "dynamic-sql-query-example.pdf");


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

    private static JRDesignQuery generateJasperQuery(String sqlQuery) {
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText(sqlQuery);
        return jrDesignQuery;
    }
}
