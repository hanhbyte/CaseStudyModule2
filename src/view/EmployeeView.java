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
    private Validate validateEmployee = new Validate();

    public Employee newEmployeeFullTime(){
        System.out.println("Nhập tên: ");
        String name = validateEmployee.checkEmpty();
        String id = checkInputId();
        System.out.println("Nhập tuổi: ");
        int age = validateEmployee.inputInterger();
        System.out.println("Nhập quê quán: ");
        String address = validateEmployee.checkEmpty();
        System.out.println("Nhập số điện thoại: ");
        int phoneNumber = validateEmployee.inputInterger();
        System.out.println("Nhập trạng thái: ");
        String status = validateEmployee.checkEmpty();
        String useName = checkInputUseName();
        System.out.println("nhập mật khẩu: ");
        int password = validateEmployee.inputInterger();
        System.out.println("Nhập tiền lương : ");
        int salary = validateEmployee.inputInterger();
        System.out.println("Nhập tiền thưởng: ");
        int bonus = validateEmployee.inputInterger();
        System.out.println("Nhập tiền phạt: ");
        int fine = validateEmployee.inputInterger();
        Employee employeeFullTime = new EmployeeFullTime(name, id, age, address, phoneNumber, status, useName, password, salary, bonus, fine);
        return employeeFullTime;
    }

    public Employee newEmployeePartTime(){
        System.out.println("Nhập tên: ");
        String name = validateEmployee.checkEmpty();
        String id = checkInputId();
        System.out.println("Nhập tuổi: ");
        int age = validateEmployee.inputInterger();
        System.out.println("Nhập quê quán: ");
        String address = validateEmployee.checkEmpty();
        System.out.println("nhập số điện thoại: ");
        int phoneNumber = validateEmployee.inputInterger();
        System.out.println("Nhập trạng thái: ");
        String status = validateEmployee.checkEmpty();
        String useName = checkInputUseName();
        System.out.println("Nhập mật khẩu: ");
        int password = validateEmployee.inputInterger();
        System.out.println("Nhập lương tính theo thời gian: ");
        int salaryTime = validateEmployee.inputInterger();
        System.out.println("Nhập thòi gian làm: ");
        int wordTime = validateEmployee.inputInterger();
        Employee employeePartTime = new EmployeePartTime(name, id, age, address, phoneNumber, status, useName, password, salaryTime, wordTime);
        return employeePartTime;
    }

    public void addEmployee(){
        System.out.println("Nhập số lượng nhân viên bạn muốn thêm: ");
        int number = validateEmployee.inputInterger();
        int i = 0;
        while (i < number){
            System.out.println("Ấn 1 để thêm nhân viên Full-Time");
            System.out.println("Ấn 2 để thêm nhân viên Part-TIme");
            int choice = validateEmployee.inputInterger();
            switch (choice){
                case 1:
                    System.out.println("Nhập thông tin nhân viên : "+(i + 1));
                    manager.addEmployee(newEmployeeFullTime());
                    break;
                case 2:
                    System.out.println("Nhập thông tin nhân viên: "+(i + 1));
                    manager.addEmployee(newEmployeePartTime());
                    break;
            }
            i++;
        }
    }

    public void editEmployee(){
        List<Employee> employeeList = manager.getEmployeeList();
        System.out.println("Nhập id của nhân viên cần chỉnh sửa: ");
        String id = checkId();
        int index = manager.getIndexOfId(id);
        if (employeeList.get(index).getStatus() == null){
            System.out.println("Nhấn phím 1 để chỉnh nhân viên Full-Time:");
            System.out.println("Nhấn phím 2 để chỉnh nhân viên Part-Time:");
            int choice = validateEmployee.inputInterger();
            switch (choice){
                case 1:
                    System.out.println("Nhập thông tin: ");
                    manager.edit(id, newEmployeeFullTime());
                    break;
                case 2:
                    System.out.println("Nhập thông tin:");
                    manager.edit(id, newEmployeePartTime());
                    break;
            }
        }else if (employeeList.get(index) instanceof EmployeeFullTime){
            manager.edit(id, newEmployeeFullTime());
        }else manager.edit(id, newEmployeePartTime());
    }

    public void deleteEmployee(){
        System.out.println("Nhập id nhân viên bạn muốn xóa: ");
        String id = checkId();
        manager.delete(id);
    }

    public void showEmployee(){
        for (Employee employee : manager.getEmployeeList()){
            System.out.println(employee);
        }
    }

    public void findByName(){
        System.out.println(" Nhập tên để tìm kiếm:");
        String name = checkName();
        for (Employee employee : manager.findByName(name)){
            System.out.println(employee);
        }
    }

    public void calculationSalaryEmployee(){
        System.out.println("Nhập id nhân viên bạn muốn tính lương:");
        String id = checkId();
        int salary = manager.calculationSalary(id);
        System.out.println("Mức lương của nhân viên có id "+id+" là "+salary);
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
        System.out.println("Nhập id của nhân viên muốn cập nhật trạng thái: ");
        String id = checkId();
        manager.updateStatus(id);
    }

    public void checkStatus(){
        System.out.println("Nhập tên để kiểm tra trạng thái:");
        String name = checkName();
        List<Employee> employees = manager.findByName(name);
        for (int i = 0; i < employeeList.size(); i++) {
            System.out.println("Nhân viên "+name+", "+" có id là  "+employeeList.get(i).getId()+", trạng thái hiện tại "+employeeList.get(i).getStatus());
        }
    }

    public String checkId(){
        while (true){
            System.out.println("Nhập id :");
            String id = validateEmployee.checkEmpty();
            if (manager.getIndexOfId(id) !=1){
                return id;
            }else System.out.println("Id đã nhập không có trong danh sách nhân viên");
        }
    }

    public String checkName(){
        while (true){
            System.out.println("Nhập tên: ");
            String name = validateEmployee.checkEmpty();
            for (Employee employee : manager.getEmployeeList()){
                if (employee.getName().equals(name))
                    return name;
            }
            System.out.println("Tên đã nhập không có trong danh sách.");
        }
    }

    public String checkInputId(){
        while (true){
            System.out.println("Nhập id ");
            String id = validateEmployee.checkEmpty();
            if (manager.getIndexOfId(id)!=1){
                return id;
            }else {
                System.out.println("Id đã có trong danh sách.");
            }
        }
    }

    public String checkInputUseName(){
        while (true){
            System.out.println("Nhập tên : ");
            String nameUse = validateEmployee.checkEmpty();
            if (manager.getIndexOfUserName(nameUse) == -1){
                return nameUse;
            }else {
                System.out.println("Tên đã nhập đã tồn tại.");
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
            System.out.println("\n Nhấn phím 1 để xem tất cả nhân viên." +
                    "\n Nhấn phím 2 để hiển thị lương của nhân viên." +
                    "\n Nhấn phím 3 để hiển thị nhân viên FullTime." +
                    "\n Nhấn phím 4 để hiển thị nhân viên PartTime." +
                    "\n Nhấn phím 5 để hiển thị danh sách nhân viên đang hoạt động." +
                    "\n nhấn phím 6 để hiển thị nhân viên đang nghỉ việc." +
                    "\n nhấn phím 0 để quay lại.");

            choice = validateEmployee.inputInterger();
            switch (choice){
                case 1:
                    System.out.println("Danh sách nhân viên");
                    showEmployee();
                    break;
                case 2:
                    calculationSalaryEmployee();
                    break;
                case 3:
                    System.out.println("Danh sách nhân viên Full-Time ");
                    String classFullTime = "EmployeeFullTime";
                    showClassEmployee(classFullTime);
                    break;
                case 4:
                    System.out.println("Danh sách nhân viên Part-Time");
                    String classPartTime = "EmployeePartTime";
                    showClassEmployee(classPartTime);
                    break;
                case 5:
                    System.out.println("Danh sách nhân viên đang làm việc: ");
                    String statusDoing = "Đang làm";
                    showEmployeeStatus(statusDoing);
                    break;
                case 6:
                    System.out.println("Danh sách nhân viên đã nghỉ việc:");
                    String statusNotDoing = "Đang nghỉ";
                    showEmployeeStatus(statusNotDoing);
                    break;
            }
        }
    }

    public void showEmployee(String useName, int password){
        int index = manager.getIndexOfUserNamePassword(useName, password);
        if (index != 1){
            Employee employee = manager.getEmployeeList().get(index);
            System.out.println(employee+"Số lương là : "+employee.calculationSalary());
            if (employee.getStatus().equals("Chưa được nhận việc")){
                System.out.println("\nĐăng kí làm nhân viên Full-Time" +
                        "\nhoặc đăng kí làm nhân viên PArt-Time");
            }
        }
    }
    public void employeeMenu(String useName, int password){
        int choice = -1;
        while (choice != 0){
            System.out.println("\nNhấn phím 1 để xem thông tin của bạn. " +
                    "\nNhấn 0 để thoát.");
            choice = validateEmployee.inputInterger();
            if (choice == 1){
                showEmployee(useName, password);
            }
        }
    }

    public Employee newMember(){
        System.out.println("Nhập tên: ");
        String name = validateEmployee.checkEmpty();
        String id = checkId();
        System.out.println("Nhập tuổi: ");
        int age = validateEmployee.inputInterger();
        System.out.println("Nhập quê quán: ");
        String address = validateEmployee.checkEmpty();
        System.out.println("Nhập số điện thoại: ");
        int phoneNumber = validateEmployee.inputInterger();
        String status = "Chưa nhận được việc";
        String useName= checkInputUseName();
        System.out.println("Nhập mật khẩu");
        int password = validateEmployee.inputInterger();
        Employee member = new Employee(name, id, age, address, phoneNumber, status, useName, password);
        return member;
    }

    public void addNewMember(){
        manager.addEmployee(newMember());
    }
}
