package vo;

import java.util.List;

public class Employee {
    public static int serial;
    private String eid;
    private String name;
    private int salary;
    private List<String> license;

    public Employee() {
    }

    public Employee(String name, int salary, List<String> license) {
        this.serial++;
        this.eid = serial + "";
        this.name = name;
        this.salary = salary;
        this.license = license;
    }

    public static int getSerial() {
        return serial;
    }

    public static void setSerial(int serial) {
        Employee.serial = serial;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<String> getLicense() {
        return license;
    }

    public void setLicense(List<String> license) {
        this.license = license;
    }

    @Override
    public String toString() {
        String temp = "";
        if(license != null){
            for(String e : license){
                temp += "," + e;
            }
        }
        return eid + ","+ name + ","+ salary + temp;
    }
}
