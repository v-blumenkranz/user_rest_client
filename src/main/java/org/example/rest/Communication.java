package org.example.rest;

import org.example.rest.model.Employee;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    private RestTemplate restTemplate;
    private String URL = "http://localhost:8080/api/employees";

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Employee> getAllEmployees() {
        ResponseEntity<List<Employee>> entity = restTemplate.exchange(
                URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Employee>>() {});
        List<Employee> allEmployees = entity.getBody();
        return allEmployees;
    }

    public Employee getEmployee(int id) {
        Employee employee = restTemplate.getForObject(
                URL + "/" + id, Employee.class);
        return employee;
    }

    public void saveEmployee(Employee employee) {
        int id = employee.getId();

        if (id == 0) {
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(URL, employee, String.class);
            System.out.println(responseEntity.getBody() + "was added to the database");
        } else {
            restTemplate.put(URL, employee);
            System.out.println(String.format("Employee with ID %d was updated", id));
        }
    }

    public void deleteEmployee(int id) {
        restTemplate.delete(URL + "/" + id);
        System.out.println(String.format("Employee with ID %d was deleted from database", id));
    }
}
