/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c18_hibernate;

/**
 *
 * @author dvd
 */
public class Employee  implements java.io.Serializable {


    private Integer id;
    private String firstName;
    private String lastName;
    private Integer salary;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Integer salary) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.salary = salary;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Integer getSalary() {
        return this.salary;
    }
    
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
