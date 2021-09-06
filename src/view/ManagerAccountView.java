package view;

import controller.ManagerAccountController;
import model.Employee;
import writeFile.FileEmployee;

import java.util.List;

public class ManagerAccountView {
    private List<Employee> employeeList = FileEmployee.readFile("employee.txt");
    private List<Employee> managerAccountList = FileEmployee.readFile("account.txt");
    private ManagerAccountController managerAccountController = new ManagerAccountController(managerAccountList);
    private Validate validateManager = new Validate();

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
            System.out.println("Nhập mật khẩu: ");
            int password = validateManager.inputInterger();
            index = getIndexOfEmployee(useName, password);
            if (index != -1){
                break;
            }else
                System.out.println("Nhân viên không tồn tại hoặc nhập sai tên người dùng và mật khẩu !!");
        }
        return employeeList.get(index);
    }

    public void addManagerAccount(){
        managerAccountController.add(newManagerAccount());
    }

    public void deleteManagerAccount(){
        System.out.println("Nhập tên người dùng của tài khoản bạn muốn xóa: ");
        String useName = checkUseName();
        managerAccountController.delete(useName);
    }

    public String checkInputUseName(){
        while (true){
            System.out.println("Nhập tên đăng nhập: ");
            String usename = validateManager.checkEmpty();
            if (managerAccountController.getIndexOfUseName(usename)== -1)
                return usename;
            else
                System.out.println("Tên đăng kí đã được sử dụng!!");
        }
    }

    public String checkUseName(){
        while (true){
            System.out.println("Nhập tên đăng nhập");
            String username = validateManager.checkEmpty();
            if (managerAccountController.getIndexOfUseName(username)== -1)
                return username;
            else
                System.out.println("Tên đăng nhập không tồn tại!!");
        }
    }

    public void showMAnagerAccount(String username, int password){
        List<Employee> managerAccountList = managerAccountController.getManagerAccountList();
        for (Employee employee : managerAccountList){
            if (employee.getUseName().equals(username) && employee.getPassword() == password){
                System.out.println("thông tin tài khoản\n" +employee+" lương là:"+employee.calculationSalary());
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
            System.out.println("\n Nhấn phím 1 để thêm tài khoản quản lý nhân viên." +
                    "\n Nhấn phím 2 để xóa tài khoản quản lý nhân viên." +
                    "\n Nhấn phím 3 để hiển thị tài khoản đăng nhập quản lý người dùng." +
                    "\n Nhấn phím 0 để thoát.");
            choice = validateManager.inputInterger();
            switch (choice){
                case 1:
                    System.out.println("Nhập tên người dùng và mật khẩu của nhân viên bạn muốn thêm vào vị trí quản lý");
                    addManagerAccount();
                    System.out.println("Đã thêm tài khoản thành công");
                    break;
                case 2:
                    deleteManagerAccount();
                    break;
                case 3:
                    System.out.println("\n" +
                            "hiển thị danh sách thông tin tài khoản đăng nhập quản lý");
                    showManagerAccountList();
                    break;
            }
        }
    }
}
