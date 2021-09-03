package model;

public class EmployeePartTime extends Employee{
    private int salaryTime ;
    private int workTime;

    public EmployeePartTime() {
    }

    public EmployeePartTime(int salaryTime, int workTime) {
        this.salaryTime = salaryTime;
        this.workTime = workTime;
    }

    public EmployeePartTime(String id, String name, int age, String address, int phone, String status, String useName, int passwork, int salaryTime, int workTime) {
        super(id, name, age, address, phone, status, useName, passwork);
        this.salaryTime = salaryTime;
        this.workTime = workTime;
    }

    public int getSalaryTime() {
        return salaryTime;
    }

    public void setSalaryTime(int salaryTime) {
        this.salaryTime = salaryTime;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    @Override
    public String toString() {
        return "EmployeeParttime{" +
                "salaryTime=" + salaryTime +
                ", workTime=" + workTime +
                '}';
    }

    @Override
    public int calculationSalary(){
        return salaryTime * workTime;
    }
}
