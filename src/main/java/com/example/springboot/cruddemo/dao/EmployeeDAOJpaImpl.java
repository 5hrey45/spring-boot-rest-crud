package com.example.springboot.cruddemo.dao;

import com.example.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
        List<Employee> employeeData = theQuery.getResultList();
        return employeeData;
    }

    @Override
    public Employee findById(int id) {
        Employee theEmployee =  entityManager.find(Employee.class, id);
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        // save the update
        Employee dbEmployee = entityManager.merge(theEmployee);

        // return the updated employee (dbEmployee)
        return dbEmployee;
    }

    @Override
    public void deleteByID(int id) {
        // find the employee by id
        Employee theEmployee = entityManager.find(Employee.class, id);

        // delete employee
        entityManager.remove(theEmployee);
    }
}
