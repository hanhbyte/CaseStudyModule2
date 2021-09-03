package view;

import controller.EmployeeController;
import model.Employee;
import model.EmployeeFullTime;
import model.EmployeePartTime;
import writeFile.FileEmployee;

import java.util.List;

public class EmployeeView {
    private List<Employee> employeeList = FileEmployee.readFile("employee.txt");
    private EmployeeController manager = new EmployeeController("Manager", employeeList);
    private Validate validate = new Validate();

    public Employee newEmployeeFullTime(){
        System.out.println("Input name: ");
        String name = validate.checkEmpty();
        String id = checkInputId();
        System.out.println("Enter age: ");
        int age = validate.inputInterger();
        System.out.println("Enter address: ");
        String address = validate.checkEmpty();
        System.out.println("Enter your phone number: ");
        int phoneNumber = validate.inputInterger();
        System.out.println("Enter status: ");
        String status = validate.checkEmpty();
        String useName = checkInputUseName();
        System.out.println("Enter password: ");
        int password = validate.inputInterger();
        System.out.println("Enter salary: ");
        int salary = validate.inputInterger();
        System.out.println("Enter bonus: ");
        int bonus = validate.inputInterger();
        System.out.println("Enter fine: ");
        int fine = validate.inputInterger();
        Employee employeeFullTime = new EmployeeFullTime(name, id, age, address, phoneNumber, status, useName, password, salary, bonus, fine);
        return newEmployeeFullTime();
    }

    public Employee newEmployeePartTime(){
        System.out.println("Input name: ");
        String name = validate.checkEmpty();
        String id = checkInputId();
        System.out.println("Enter age: ");
        int age = validate.inputInterger();
        System.out.println("Enter address: ");
        String address = validate.checkEmpty();
        System.out.println("Enter your phone number: ");
        int phoneNumber = validate.inputInterger();
        System.out.println("Enter status: ");
        String status = validate.checkEmpty();
        String useName = checkInputUseName();
        System.out.println("Enter password: ");
        int password = validate.inputInterger();
        System.out.println("Enter salaryTime: ");
        int salaryTime = validate.inputInterger();
        System.out.println("enter wordtime: ");
        int wordTime = validate.inputInterger();
        Employee employeePartTime = new EmployeePartTime(name, id, age, address, phoneNumber, status, useName, password, salaryTime, wordTime);
        return newEmployeePartTime();
    }

    public void addEmployee(){
        System.out.println("Enter the number of employees you want to add: ");
        int number = validate.inputInterger();
        int i = 0;
        while (i < number){
            System.out.println("Press 1 to add a FullTime employee");
            System.out.println("Press 2 to add a PartTime employee");
            int choice = validate.inputInterger();
            switch (choice){
                case 1:
                    System.out.println("Enter employee information : "+(i + 1));
                    manager.addEmployee(newEmployeeFullTime());
                    break;
                case 2:
                    System.out.println("Enter employee information : "+(i + 1));
                    manager.addEmployee(newEmployeePartTime());
                    break;
            }
            i++;
        }
    }

    public void editEmployee(){
        List<Employee> employeeList = manager.getEmployeeList();
        System.out.println("Enter the id of the employee to be edited : ");
        String id = checkId();
        int index = manager.getIndexOfId(id);
        if (employeeList.get(index).getStatus() == null){
            System.out.println("Press 1 to edit the FullTime staff bar:");
            System.out.println("Press 2 to edit the PartTime staff bar:");
            int choice = validate.inputInterger();
            switch (choice){
                case 1:
                    System.out.println("Enter information: ");
                    manager.edit(id, newEmployeeFullTime());
                    break;
                case 2:
                    System.out.println("Enter information: ");
                    manager.edit(id, newEmployeePartTime());
                    break;
            }
        }else if (employeeList.get(index) instanceof EmployeeFullTime){
            manager.edit(id, newEmployeeFullTime());
        }else manager.edit(id, newEmployeePartTime());
    }

    public void deleteEmployee(){
        System.out.println("Enter the employee id you want to delete: ");
        String id = checkId();
        manager.delete(id);
    }

    public void showEmployee(){
        for (Employee employee : manager.getEmployeeList()){
            System.out.println(employee);
        }
    }

    public void findByName(){
        System.out.println("Enter the name to search: ");
        String name = checkName();
        for (Employee employee : manager.findByName(name)){
            System.out.println(employee);
        }
    }

    public void calculationSalaryEmployee(){
        System.out.println("Enter the employee id you want to calculate salary: ");
        String id = checkId();
        int salary = manager.calculationSalary(id);
        System.out.println("Salary of employee with id :"+id+" is "+salary);
    }

    public void showEmployeeStatus(String status){

        for (Employee employee : manager.getEmployeeStatus(status)){
            System.out.println(employee);
        }
    }

    public void showClassEmployee(String classEmployee){
        for (Employee employee : manager.getClassEmployee(classEmployee)){
            System.out.println(employee);
        }
    }

    public void updateStatus(){
        System.out.println("Enter the id of the employee who wants to update the status: ");
        String id = checkId();
        manager.updateStatus(id);
    }

    public void checkStatus(){
        System.out.println("Enter the name to check the status:");
        String name = checkName();
        List<Employee> employees = manager.findByName(name);
        for (int i = 0; i < employeeList.size(); i++) {
            System.out.println("Employee: "+name+", "+"have id: "+employeeList.get(i).getId()+", status: "+employeeList.get(i).getStatus());
        }
    }

    public String checkId(){
        while (true){
            System.out.println("Enter id :");
            String id = validate.checkEmpty();
            if (manager.getIndexOfId(id) !=1){
                return id;
            }else System.out.println("The id entered is not in the employee list");
        }
    }

    public String checkName(){
        while (true){
            System.out.println("Enter name : ");
            String name = validate.checkEmpty();
            for (Employee employee : manager.getEmployeeList()){
                if (employee.getName().equals(name))
                    return name;
            }
            System.out.println("The entered name is not in the list.");
        }
    }

    public String checkInputId(){
        while (true){
            System.out.println("Enter id: ");
            String id = validate.checkEmpty();
            if (manager.getIndexOfId(id)!=1){
                return id;
            }else {
                System.out.println("Id already in the list.");
            }
        }
    }

    public String checkInputUseName(){
        while (true){
            System.out.println("Enter name : ");
            String nameUse = validate.checkEmpty();
            if (manager.getIndexOfUserName(nameUse) == -1){
                return nameUse;
            }else {
                System.out.println("The entered name already exists.");
            }
        }
    }

    public boolean checkLogin(String useName, int password){
        if (manager.getIndexOfUserNamePassword(useName, password)!= -1)
            return true;
        else return false;
    }

    public void choiceShow(){
        int choice = -1;
        while (choice != 0){
            System.out.println("Press 1 to view all employees. " +
                    "\n Press 2 to display the employee's salary. " +
                    "\n Press 3 to display FullTime employees. " +
                    "\n Press 4 to display PartTime employees. " +
                    "\n Press 5 to display the list of active employees. " +
                    "\n press 6 to show the employee is on leave. " +
                    "\n press 0 to go back.");

            choice = validate.inputInterger();
            switch (choice){
                case 1:
                    System.out.println("List of employee.");
                    showEmployee();
                    break;
                case 2:
                    calculationSalaryEmployee();
                    break;
                case 3:
                    System.out.println("Full-Time staff list: ");
                    String classFullTime = "EmployeeFullTime";
                    showClassEmployee(classFullTime);
                    break;
                case 4:
                    System.out.println("Part-Time staff list: ");
                    String classPartTime = "EmployeePartTime";
                    showClassEmployee(classPartTime);
                    break;
                case 5:
                    System.out.println("List of employees working: ");
                    String statusDoing = "doing";
                    showEmployeeStatus(statusDoing);
                    break;
                case 6:
                    System.out.println("List of employees who have left: ");
                    String statusNotDoing = "took a break";
                    showEmployeeStatus(statusNotDoing);
                    break;
            }
        }
    }

    public void showEmployee(String useName, int password){
        int index = manager.getIndexOfUserNamePassword(useName, password);
        if (index != 1){
            Employee employee = manager.getEmployeeList().get(index);
            System.out.println(employee+"\nsalary income: "+employee.calculationSalary());
            if (employee.getStatus().equals("have not received a job")){
                System.out.println("Please register as a Full-Time employee " +
                        "\n or a Part-Time employee with your manager");
            }
        }
    }
    public void employeeMenu(String useName, int password){
        int choice = -1;
        while (choice != 0){
            System.out.println("Press 1 to view your information. " +
                    "\n press 0 to exit");
            choice = validate.inputInterger();
            if (choice == 1){
                showEmployee(useName, password);
            }
        }
    }

    public Employee newMember(){
        System.out.println("Enter name: ");
        String name = validate.checkEmpty();
        String id = checkId();
        System.out.println("Enter age: ");
        int age = validate.inputInterger();
        System.out.println("Enter address: ");
        String address = validate.checkEmpty();
        System.out.println("Enter your phone number: ");
        int phoneNumber = validate.inputInterger();
        String status = "Haven't received a job yet";
        String useName= checkInputUseName();
        System.out.println("Enter password");
        int password = validate.inputInterger();
        Employee member = new Employee(name, id, age, address, phoneNumber, status, useName, password);
        return member;
    }

    public void addNewMember(){
        manager.addEmployee(newMember());
    }
}
