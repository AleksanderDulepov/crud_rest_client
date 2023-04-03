package com.dulepov.spring.rest_client;

import com.dulepov.spring.rest_client.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
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

        //http-запрос
        Employee emp=restTemplate.getForObject(URL+"/"+empID
							, Employee.class);

        return emp;
    }


//    //CREATE    (json ответ)
//    public String createNewEmployee(Employee employee){
//
//        //проверяем, чтобы не было передано id, иначе будет попытка перезаписать существующий обьект
//        int id=employee.getId();
//
//        if (id==0){
//
//            //если надо вернуть json ответ
//            ResponseEntity<String> responseEntity=restTemplate.postForEntity(URL, employee, String.class);
//            return ("Пользователь был успешно добавлен\n"+responseEntity.getBody());		// json
//        }
//        return ("Нельзя передавать ID. Добавление зафейлино");
//    }

    //CREATE    (обьект Employee ответ)
    public Employee createNewEmployee(Employee employee){

        //проверяем, чтобы не было передано id, иначе будет попытка перезаписать существующий обьект
        int id=employee.getId();

        if (id==0){

            //если надо вернуть добавленный обьект Employee
            ResponseEntity<Employee> responseEntity=restTemplate.postForEntity(URL, employee, Employee.class);
            return (responseEntity.getBody());		//вывод не json, метода toString у ОБЬЕКТА!
        }
        return null;
    }

//    //UPDATE    (обновленный обьект Employee ответ)
//    public Employee updateEmployee(int empID, Employee employee) {
//
//        //присваиваем обьекту значение id из сегмента (если уже был передан-перезаписываем)
//        employee.setId(empID);
//
//        HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
//
//        //обработка ошибки валидации (api возвращает BadRequest)
//        try {
//            ResponseEntity<Employee> responseEntity=restTemplate.exchange(URL+"/"+empID
//                    , HttpMethod.PUT, request, Employee.class);
//            return responseEntity.getBody();
//        } catch (HttpClientErrorException.BadRequest e){
//            return null;
//        }
//    }


    //UPDATE    (вывод json)
    public String updateEmployee(int empID, Employee employee) {

        //присваиваем обьекту значение id из сегмента (если уже был передан-перезаписываем)
        employee.setId(empID);

        HttpEntity<Employee> request = new HttpEntity<Employee>(employee);

        //обработка ошибки валидации (api возвращает BadRequest)
        try {
            ResponseEntity<String> responseEntity=restTemplate.exchange(URL+"/"+empID
                    , HttpMethod.PUT, request, String.class);
            return responseEntity.getBody();
        } catch (HttpClientErrorException.BadRequest e){
            return null;
        }
    }



    //PARTIAL_UPDATE
        public Employee partialUpdateEmployee(int empID, String json) {

            //заголовки для обьекта реквеста для передачи content-type
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<String>(json,headers);

            //обработка ошибки валидации (api возвращает BadRequest)
            try {
                Employee updatedEmp=restTemplate.patchForObject(URL+"/"+empID, request, Employee.class);
                return updatedEmp;
            } catch (HttpClientErrorException.BadRequest e){
                return null;
            }
    }



    //DELETE
    public String deleteEmployee(int empID){

        try {
            restTemplate.delete(URL+"/"+empID);
            return "Успешное удаление";
        } catch (HttpClientErrorException.NotFound e)
        {
            return "Такого пользователя в базе не существует";
        }

    }


}
