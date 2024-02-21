package io.kowsu.dp.structural;

import java.util.ArrayList;
import java.util.List;

public class CompositePattern {

    public static void main(String[] args) {
        IEmployee employee = new Employee("RAM", 12312);
        IEmployee manager = new Manager("RAMA", 1233);
        manager.add(employee);
        manager.print();
        IEmployee generalManager = new GeneralManager("RAMANJI", 1234);
        generalManager.add(manager);
        generalManager.print();
    }

}
interface IEmployee {
    void add(IEmployee employee);
    void delete(IEmployee employee);
    void print();
    String getName();
    double getSalary();
}

class Manager implements IEmployee {
    List<IEmployee> employees = new ArrayList<>();

    private String name;
    private double salary;

    public Manager(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    @Override
    public void add(IEmployee employee) {
        employees.add(employee);
    }

    @Override
    public void delete(IEmployee employee) {
        employees.remove(employee);
    }

    @Override
    public void print() {
        System.out.println("Manager sal " + getSalary() + " name " + getName() + " reporting employees " );
        employees.forEach(IEmployee::print);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "employees=" + employees +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

class Employee implements  IEmployee {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void add(IEmployee employee) {
        throw new UnsupportedOperationException("not supported.");
    }

    @Override
    public void delete(IEmployee employee) {
        throw new UnsupportedOperationException("not supported.");
    }

    @Override
    public void print() {
        System.out.println("Employee sal "+ getSalary() + " name " + getName());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        return salary;
    }
}

class GeneralManager implements IEmployee {

    private String name;
    private double salary;

    List<IEmployee> employees = new ArrayList<>();

    public GeneralManager(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void add(IEmployee employee) {
        employees.add(employee);
    }

    @Override
    public void delete(IEmployee employee) {
        employees.remove(employee);
    }

    @Override
    public void print() {
        System.out.println("General manager sal :" + getSalary() + " name " + getName() + " reporting employees ");
        employees.forEach(IEmployee::print);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "GeneralManager{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", employees=" + employees +
                '}';
    }
}



