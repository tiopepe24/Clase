/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c13;

/**
 *
 * @author dvd
 */
import java.util.*; 
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageEmployee { 
    private static SessionFactory factory;
    
    public static void main(String[] args) {
        try{ 
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) { 
        System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }
        ManageEmployee ME = new ManageEmployee();
        Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
        Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
        Integer empID3 = ME.addEmployee("John", "Paul", 10000);
        ME.listEmployees();
        ME.updateEmployee(empID1, 5000);
        ME.listEmployees();
        ME.updateEmployee(empID2, 3000);
        ME.listEmployees();
        ME.deleteEmployee(empID1);
        ME.deleteEmployee(empID2);
        ME.deleteEmployee(empID3);
       factory.close();
    }
    /* Method to CREATE an employee in the database */
    public Integer addEmployee(String fname, String lname, int salary){ 
        Session session = factory.openSession();
        Transaction tx = null; Integer employeeID = null;
        try{
            tx = session.beginTransaction(); 
            Employee employee = new Employee(fname, lname, salary);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        }catch (HibernateException e) {
        if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally { 
            session.close(); 
        }
        return employeeID; 
    }
    /* Method to READ all the employees */
    public void listEmployees( ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
        for (Iterator iterator = employees.iterator();iterator.hasNext();){ 
            Employee employee = (Employee) iterator.next();
            System.out.print("First Name: " + employee.getFirstName());
            System.out.print(" Last Name: " + employee.getLastName());
            System.out.println(" Salary: " + employee.getSalary());
        }
            tx.commit();
        }catch (HibernateException e) { 
            if (tx!=null)
                tx.rollback();
                e.printStackTrace();
        }finally {
            session.close(); 
        } 
    }
    /* Method to UPDATE salary for an employee */
    public void updateEmployee(Integer EmployeeID, int salary ){
        Session session = factory.openSession();
        try{ 
            String hql = "UPDATE Employee set salary = "+salary + "WHERE id = "+EmployeeID;
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
        }catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close(); 
        }
    }
    /* Method to DELETE an employee from the records */
    public void deleteEmployee(Integer EmployeeID){
        Session session = factory.openSession();
        Transaction tx = null;
        try{ 
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
            session.delete(employee); tx.commit();
        }catch (HibernateException e) { 
            if (tx!=null) tx.rollback();
                e.printStackTrace();
        }finally { 
            session.close();
        }
    }
}
