package controller;

import model.Employee;
import model.EmployeeFullTime;
import model.EmployeePartTime;
import writeFile.FileEmployee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
    private String name;
    List<Employee> employeeList = new ArrayList<>();

    public EmployeeController() {
    }

    public EmployeeController(String name, List<Employee> employeeList) {
        this.name = name;
        this.employeeList = employeeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        FileEmployee.writeFile(employeeList, "employee.txt");
    }

    public void edit(String id, Employee employee) {
        int index = getIndexOfId(id);
        employeeList.set(index, employee);
        FileEmployee.writeFile(employeeList, "employee.txt");
    }

    public void delete(String id) {
        int index = getIndexOfId(id);
        employeeList.remove(id);
        FileEmployee.writeFile(employeeList, "employee.txt");
    }

    public List<Employee> findByName(String name) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getName().equals(name)) {
                employees.add(employeeList.get(i));
            }
        }
        return employees;
    }

    public int calculationSalary(String id) {
        int index = getIndexOfId(id);
        Employee employee = employeeList.get(index);
        if (employee instanceof EmployeeFullTime) {
            return employee.calculationSalary();
        } else return employee.calculationSalary();
    }

    public int getIndexOfUserName(String userName) {
        int index = -1;
        for (int i = 0; i < employeeList.size(); i++) {
            Employee employee = employeeList.get(i);
            if (employee.getUseName().equals(userName)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int getIndexOfUserNamePassword(String name, int password) {
        int index = -1;
        for (int i = 0; i < employeeList.size(); i++) {
            Employee employee = employeeList.get(i);
            if (employee.getUseName().equals(name) && employee.getPassword() == password) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int getIndexOfId(String id) {
        int index = -1;
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public List<Employee> getEmployeeStatus(String status) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getStatus().equals(status)) {
                employees.add(employeeList.get(i));
            }
        }
        return employees;
    }

    public void updateStatus(String id) {
        int index = getIndexOfId(id);
        if (employeeList.get(index).getStatus().equals("Đang làm")) {
            employeeList.get(index).setStatus("Đã nghỉ");
        } else employeeList.get(index).setStatus("Đang làm");
        FileEmployee.writeFile(employeeList, "employee.txt");
    }

    public List<Employee> getClassEmployee(String classEmployee) {
        List<Employee> classEmployees = new ArrayList<>();
        if (classEmployee.equals("EmployeeFullTime")) {
            for (int i = 0; i < employeeList.size(); i++) {
                if (employeeList.get(i) instanceof EmployeeFullTime) classEmployees.add(employeeList.get(i));
            }
            return classEmployees;
        } else if (classEmployee.equals("EmployeePartTime")) {
            for (int i = 0; i < employeeList.size(); i++) {
                if (employeeList.get(i) instanceof EmployeePartTime) classEmployees.add(employeeList.get(i));
            }
            return classEmployees;
        }
        return null;
    }
}
