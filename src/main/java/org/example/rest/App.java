package org.example.rest;

import org.example.rest.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Communication communication = context.getBean("communication", Communication.class);
//        System.out.println(communication.getAllEmployees());
//        System.out.println(communication.getEmployee(5));
//        communication.saveEmployee(
//                new Employee("Jack", "London", "Journalistic", 70000));
//        communication.deleteEmployee(3);
    }
}
