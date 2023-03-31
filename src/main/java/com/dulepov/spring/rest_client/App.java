package com.dulepov.spring.rest_client;

import com.dulepov.spring.rest_client.configuration.MyConfig;
import com.dulepov.spring.rest_client.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        //получение контекста
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext (MyConfig.class);
        //получение бина CommunicationService
        CommunicationService сommunicationService=context.getBean("communicationService", CommunicationService.class);

        //READ ALL
        List<Employee> emps=сommunicationService.getAllEmployees();
        //можем как то использовать полученные обьекты
        System.out.println(emps);   //выводим не json, метод toString у ОБЬЕКТОВ!
    }





}
