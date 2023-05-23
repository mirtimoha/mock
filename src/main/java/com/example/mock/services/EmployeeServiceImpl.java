package com.example.mock.services;

import com.example.mock.exceptions.EmployeeAlreadyAddedException;
import com.example.mock.exceptions.EmployeeNotFoundException;
import com.example.mock.exceptions.InvalidInputException;
import com.example.mock.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employeeMap;

    public EmployeeServiceImpl() {
        this.employeeMap = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) {

        validateInput(firstName, lastName);

        if (employeeMap.containsKey(firstName + lastName)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
        }
        employeeMap.put((firstName + lastName),new Employee(firstName, lastName, salary, department));
        return employeeMap.get(firstName + lastName);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {

        validateInput(firstName, lastName);

        if (employeeMap.containsKey(firstName + lastName)) {
            return employeeMap.get(firstName + lastName);
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    @Override
    public Collection<Employee> findAllEmployees() {
        return Collections.unmodifiableCollection(employeeMap.values());
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {


        validateInput(firstName, lastName);

        findEmployee(firstName,lastName);
        employeeMap.remove(firstName + lastName);
        return new Employee(firstName,lastName);
    }

    private void validateInput(String firsName, String lastName) {
        if(!(isAlpha(firsName)&&isAlpha(lastName))){
            throw new InvalidInputException("incorrect name or surname");
        }
    }


}