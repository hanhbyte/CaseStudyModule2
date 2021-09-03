package controller;

import model.Employee;
import writeFile.FileEmployee;

import java.util.ArrayList;
import java.util.List;

public class ManagerAccountController {
    List<Employee> managerAccountList = new ArrayList<>();

    public ManagerAccountController(List<Employee> managerAccountList) {
        this.managerAccountList = managerAccountList;
    }

    public void add(Employee employee){
        managerAccountList.add(employee);
        FileEmployee.writeFile(managerAccountList, "account.txt");
    }

    public void delete(String useName){
        managerAccountList.remove(getIndexOfUseName(useName));
        FileEmployee.writeFile(managerAccountList, "account.txt");
    }

    public int getIndexOfUseName(String useName){
        int index = -1;
        for (int i = 0; i < managerAccountList.size(); i++) {
            if (managerAccountList.get(i).getUseName().equals(useName)){
                index = i;
                break;
            }
        }
        return index;
    }

    public List<Employee> getManagerAccountList(){
        return managerAccountList;
    }

    public void setManagerAccountList(List<Employee> managerAccountList){
        this.managerAccountList = managerAccountList;
    }
}
