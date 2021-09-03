package writeFile;

import model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileEmployee {
    public static void writeFile(List<Employee> employeeList, String fileName) {
        File file = new File(fileName);
        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(employeeList);
            objectOut.close();
            fileOut.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static List<Employee> readFile(String fileName){
        List<Employee> employees = new ArrayList<>();
        File file = new File(fileName);
        if (file.length()>0){
            try {
                FileInputStream fileInput = new FileInputStream(file);
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                employees = (List<Employee>) objectInput.readObject();
            }catch (IOException | ClassNotFoundException exception){
                exception.printStackTrace();
            }
        }
        return employees;
    }
}
