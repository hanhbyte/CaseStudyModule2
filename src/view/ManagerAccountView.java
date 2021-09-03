package view;

import controller.ManagerAccountController;
import model.Employee;
import writeFile.FileEmployee;

import java.util.List;

public class ManagerAccountView {
    private List<Employee> employeeList = FileEmployee.readFile("employee.txt");
    private List<Employee> managerAccountList = FileEmployee.readFile("account.txt");
    private ManagerAccountController managerAccountController = new ManagerAccountController(managerAccountList);
    private Validate validate = new Validate();

    public int getIndexOfEmployee(String useName, int password){
        int index = -1;
        for (int i = 0; i < employeeList.size(); i++) {
            Employee employee = employeeList.get(i);
            if (employee.getUseName().equals(useName) && employee.getPassword() == password){
                index = i;
                break;
            }
        }
        return index;
    }

    public Employee newManagerAccount(){
        int index = 0;
        while (true){
            String useName = checkInputUseName();
            System.out.println("Enter password");
            int password = validate.inputInterger();
            index = getIndexOfEmployee(useName, password);
            if (index != -1){
                break;
            }else
                System.out.println("employee does not exist or entered wrong username and password!!");
        }
        return employeeList.get(index);
    }

    public void addManagerAccount(){
        managerAccountController.add(newManagerAccount());
    }

    public void deleteManagerAccount(){
        System.out.println("Enter the username of the account you want to delete");
        String useName = checkUseName();
        managerAccountController.delete(useName);
    }

    public String checkInputUseName(){
        while (true){
            System.out.println("Enter username: ");
            String usename = validate.checkEmpty();
            if (managerAccountController.getIndexOfUseName(usename)== -1)
                return usename;
            else
                System.out.println("username already exists!!");
        }
    }

    public String checkUseName(){
        while (true){
            System.out.println("Enter username");
            String username = validate.checkEmpty();
            if (managerAccountController.getIndexOfUseName(username)== -1)
                return username;
            else
                System.out.println("username does not exist!!");
        }
    }

    public void showMAnagerAccount(String username, int password){
        List<Employee> managerAccountList = managerAccountController.getManagerAccountList();
        for (Employee employee : managerAccountList){
            if (employee.getUseName().equals(username) && employee.getPassword() == password){
                System.out.println("account information:\n" +employee+" salary income"+employee.calculationSalary());
            }
        }
    }

    public void showManagerAccountList(){
        List<Employee> managerAccountList = managerAccountController.getManagerAccountList();
        for (Employee employee : managerAccountList){
            System.out.println(employee);
        }
    }

    public boolean checkLogin(String userName, int password){
        List<Employee> managerAccountList = managerAccountController.getManagerAccountList();
        for (Employee employee : managerAccountList){
            if (employee.getUseName().equals(userName)&& employee.getPassword() == password){
                return true;
            }
        }
        return false;
    }

    public void adminMenu(){
        int choice = -1;
        while (choice != 0){
            System.out.println("Press 1 to add an employee management account. " +
                    "\n press 2 to delete the employee management account. " +
                    "\n press 3 to display the user management login accounts. " +
                    "\n press 0 to exit.\n");
            choice = validate.inputInterger();
            switch (choice){
                case 1:
                    System.out.println("Enter the username and password of the employee you want to add to the management position");
                    addManagerAccount();
                    System.out.println("added account login successful !!");
                    break;
                case 2:
                    deleteManagerAccount();
                    break;
                case 3:
                    System.out.println("display a list of management login account information");
                    showManagerAccountList();
                    break;
            }
        }
    }
}
