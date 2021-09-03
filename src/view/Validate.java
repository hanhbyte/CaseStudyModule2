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
                System.out.println("Keep input must be numeric\nretype : ");
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
                System.out.println("Data is blank, please re-enter\nretype : ");
            }
        }
    }
}
