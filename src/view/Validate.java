package view;

import java.util.Scanner;

public class Validate {
    Scanner scanner = new Scanner(System.in);

    public int inputInterger(){
        int number = 0;
        while (true){
            try {
                number = Integer.parseInt(scanner.nextLine());
                break;
            }catch (Exception e){
                System.out.println("Phải là số!!\nNhập lại: ");
            }
        }
        return number;
    }

    public String checkEmpty(){
        while (true){
            String s = scanner.nextLine();
            if (s.trim().isEmpty()!= true){
                return s;
            }else {
                System.out.println("Dữ liệu trống, vui lòng nhập lại\nNhập lại : ");
            }
        }
    }
}
