package org.example.rest;

import org.example.rest.config.AppConfig;
import org.example.rest.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Communication communication = context.getBean("communication", Communication.class);
        System.out.println(communication.getAllUsers());
        System.out.println(communication.saveUser(new User(3L, "James", "Brown", (byte) 17)));
        System.out.println(communication.updateUser(new User(3L, "Thomas", "Shelby", (byte) 17)));
        System.out.println(communication.deleteUser());
    }
}
