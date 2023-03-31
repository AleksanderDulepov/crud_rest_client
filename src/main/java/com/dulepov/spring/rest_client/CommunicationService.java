package com.dulepov.spring.rest_client;

import com.dulepov.spring.rest_client.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CommunicationService {

    @Autowired    //доступ к бину restTemplate из MyConfig
    private RestTemplate restTemplate;

    //неизменная часть url
    private final String URL = "http://localhost:8080/api/employees";

    //READ ALL
    public List<Employee> getAllEmployees(){

		//[схема: делаем http-запрос на REST Server->получаем ответ в json->прописываем в обьекте ответа,
        // к чему привести данные из json(к обьектам Employees){десериализация происходит автоматически}
        // ->достаем обьекты из тела ответа и используем их]

        //обьект http-ответа на http-запрос
        ResponseEntity<List<Employee>> responseEntity=restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {});

        //получение тела http-ответа [здесь важно, что в теле будут уже десериализованные обьекты, а не json данные]
        List<Employee> emps=responseEntity.getBody();

        return emps;

    }

    //READ ONE
    public Employee getCurrentEmployee(int empID){

        return null;
    }

    //CREATE
    public void createNewEmployee(Employee employee){
    }

    //UPDATE
    public void updateEmployee(int empID, Employee employee){


    }

    //PARTIAL_UPDATE


    //DELETE
    public void deleteEmployee(int empID){


    }


}
