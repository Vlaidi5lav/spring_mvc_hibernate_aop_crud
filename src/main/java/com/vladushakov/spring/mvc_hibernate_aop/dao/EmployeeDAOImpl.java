package com.vladushakov.spring.mvc_hibernate_aop.dao;

import com.vladushakov.spring.mvc_hibernate_aop.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Employee", Employee.class)
                .getResultList();

    }

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);
        session.delete(employee);
        //        ИЛИ
//        Query<Employee> query = session.createQuery("delete from employee" +
//        " where id=:employeeId");
//        query.setParameter("employeeId", id);
//        query.executeUpdate();
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);

    }
}
