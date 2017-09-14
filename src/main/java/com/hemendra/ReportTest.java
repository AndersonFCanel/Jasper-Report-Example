package com.hemendra;

import com.hemendra.util.ExportFile;
import com.hemendra.util.JasperCustomReport;
import net.sf.jasperreports.engine.JRException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BIP034 on 7/19/2017.
 */
public class ReportTest {
    public static void main(String[] args) throws JRException, IOException, ClassNotFoundException, SQLException {

        List<Map<String, Object>> strings = new ArrayList<>();
        Map<String, Object> parameters = new HashMap<String, Object>();

        Student student = null;
        List<Student> studentList = new ArrayList<>();
        for (int i=0; i< 100; i++) {
            student = new Student();
            student.setName("A "+ (char)i);
            student.setBranch("Branch-"+  i*10);
            student.setRoll("Roll-"+ i+23*12);
            student.setMob("mobile-"+ i);

            studentList.add(student);
        }

        parameters.put("studentdDataSource", studentList);
        String connectionExampleJRXML = "connection-datasource.jrxml";

        JasperCustomReport jasperCustomReport = new JasperCustomReport();
        byte[] anythings = jasperCustomReport.exportReportToPdf(parameters, "test.jrxml", "studentdDataSource");
        //byte[] anythings = jasperCustomReport.exportReportToXLS(parameters, "test.jrxml", "studentdDataSource");
        //byte[] anythings = jasperCustomReport.exportReportToPdf(parameters, connectionExampleJRXML, null);

        ExportFile.exportFile(anythings, "abc.pdf");

        System.out.println(anythings);
    }

}
