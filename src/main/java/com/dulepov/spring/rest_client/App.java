package com.dulepov.spring.rest_client;

import com.dulepov.spring.rest_client.configuration.MyConfig;
import com.dulepov.spring.rest_client.entity.Employee;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
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
//        List<Employee> emps=сommunicationService.getAllEmployees();
        //можем как то использовать полученные обьекты
//        System.out.println(emps);   //выводим не json, метод toString у ОБЬЕКТОВ!

//        //READ ONE
//        Employee empById=сommunicationService.getCurrentEmployee(1);
//        System.out.println(empById);


//        //CREATE
//        Employee employee=new Employee("Ivan", "Sidorov", "HR", 1500, "q@mail.ru", "111-22-33");
//
//        //возвращаем json
//        //System.out.println(сommunicationService.createNewEmployee(employee));
//
//        //возвращаем обьект Employee
//        Employee createdEmp=сommunicationService.createNewEmployee(employee);
//        if (createdEmp!=null){
//            System.out.println("Пользователь был успешно добавлен\n"+createdEmp);
//        } else {
//            System.out.println("Нельзя передавать ID. Добавление зафейлино");
//        }


//        //UPDATE (обновленный обьект Employee ответ)
//        Employee employee=new Employee("Ivan", "Sidorov", "HR", 1500, "q@mail.ru", "111-22-33");
//        Employee updatedEmp=сommunicationService.updateEmployee(1, employee);
//
//        if (updatedEmp!=null){
//            System.out.println("Пользователь был успешно обновлен\n"+updatedEmp);
//        } else {
//            System.out.println("Ошибка обновления");
//        }

//        //UPDATE (вывод json)
//        Employee employee=new Employee("Ivan", "Sidorovich", "HR", 1500, "q@mail.ru", "111-22-33");
//        String updatedEmp=сommunicationService.updateEmployee(1, employee);
//
//        if (updatedEmp!=null){
//            System.out.println("Пользователь был успешно обновлен\n"+updatedEmp);
//        } else {
//            System.out.println("Ошибка обновления");
//        }

        //PARTIAL_UPDATE

        //для patch метода необходимо добавить зависимость org.apache.httpcomponents.client5 версии от 5 (см. pom.xml)

        //создание стринга по формату json
        String json= new JSONObject().put("email","123@mail.ru").toString();

        Employee updatedEmp=сommunicationService.partialUpdateEmployee(1, json);

        if (updatedEmp!=null){
            System.out.println("Пользователь был успешно обновлен\n"+updatedEmp);
        } else {
            System.out.println("Ошибка обновления");
        }

    }






}
