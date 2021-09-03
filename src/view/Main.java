package view;

public class Main {
    static EmployeeView employeeView = new EmployeeView();
    static ManagerAccountView managerAccountView = new ManagerAccountView();
    static Validate validate = new Validate();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("Press 1 to log in. " +
                    "\n Press 2 to register a new account. " +
                    "\n Press 0 to exit");
            choice = validate.inputInterger();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Please enter the following information");
                    employeeView.addNewMember();
                    System.out.println("Successful account registration!");
                    break;
            }
        }
    }

    public static boolean checkLoginAdmin(String username, int password) {
        if (username.equals("admin") && password == 123456)
            return true;
        else
            return false;
    }

    public static void login() {
        int i = 0;
        while (i < 3) {
            System.out.println("Enter username: ");
            String username = validate.checkEmpty();
            System.out.println("Enter password: ");
            int password = validate.inputInterger();
            if (checkLoginAdmin(username, password)) {
                int choice = -1;
                while (choice != 0) {
                    System.out.println("Press 1 to access employee management. " +
                            "\n Press 2 to access employee account management. " +
                            "\n Press 0 to exit");
                    choice = validate.inputInterger();
                    switch (choice) {
                        case 1:
                            employeeManagerMenu(username, password);
                            break;
                        case 2:
                            managerAccountView.adminMenu();
                            break;
                    }
                }
                break;
            } else if (managerAccountView.checkLogin(username, password)) {
                employeeManagerMenu(username, password);
                break;
            } else if (employeeView.checkLogin(username, password)) {
                employeeView.employeeMenu(username, password);
                break;
            } else
                System.out.println("username or password is incorrect " + (i + 1));
            if (i == 2)
                System.out.println("You entered the wrong input more than 3 times, go back to the menu");
            i++;
        }
    }

    public static void employeeManagerMenu(String username, int password) {
        int choice = -1;
        while (choice != 9) {
            System.out.println("MENU: " +
                    "\n Press 1 to add staff. " +
                    "\n Press 2 to edit employee information. " +
                    "\n Press 3 to display, " +
                    "\n Press 4 to search for employees. " +
                    "\n Press 5 to check employee status. " +
                    "\n Press 6 to change employee status. " +
                    "\n Press 7 to delete the employee. " +
                    "\n Press 8 to view account information. " +
                    "\n Press 9 to log out.");
            choice = validate.inputInterger();
            switch (choice) {
                case 1:
                    employeeView.addEmployee();
                    break;
                case 2:
                    employeeView.editEmployee();
                    break;
                case 3:
                    employeeView.choiceShow();
                    break;
                case 4:
                    employeeView.findByName();
                    break;
                case 5:
                    employeeView.checkStatus();
                    break;
                case 6:
                    employeeView.updateStatus();
                    break;
                case 7:
                    employeeView.deleteEmployee();
                    break;
                case 8:
                    managerAccountView.showMAnagerAccount(username, password);
                    break;
            }
        }
    }
}
