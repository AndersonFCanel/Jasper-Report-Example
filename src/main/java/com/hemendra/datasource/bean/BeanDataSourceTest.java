package com.hemendra.datasource.bean;

import com.hemendra.Student;
import com.hemendra.util.ExportFile;
import com.hemendra.util.JasperCustomReport;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanDataSourceTest {
    private static final String BEAN_DATASOURCE_JRXML = "bean-datasource.jrxml";
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

        parameters.put("reportTitle", "This is the Example Of Bean DataSource!!");
        parameters.put("beanDataSource", studentList);

        JasperCustomReport jasperCustomReport = new JasperCustomReport();
        byte[] anythings = jasperCustomReport.exportReportToPdf(parameters, BEAN_DATASOURCE_JRXML, "beanDataSource", true);

        ExportFile.exportFile(anythings, "bean-datasource-example.pdf");

        System.out.println(anythings);
    }
}
