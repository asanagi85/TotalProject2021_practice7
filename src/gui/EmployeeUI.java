package gui;

import service.EmployeeManager;
import service.Manager;
import vo.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeUI implements UI{
    Scanner scanner;
    Manager manager;

    public EmployeeUI() {
        scanner = new Scanner(System.in);
        manager = new EmployeeManager();

        String select;

        while (true) {
            printMainMenu();
            select = scanner.nextLine();

            switch (select){
                case "1":
                    insertEmployee();
                    break;
                case "2":
                    findEmployee();
                    break;
                case "3":
                    deleteEmployee();
                    break;
                case "4":
                    manager.showAll();
                    break;
                case "9":
                    System.out.println("exit this app.");
                    System.out.println("data will be saved the place.");
                    manager.saveFile();
                    System.exit(0);
            }
        }
    }

    @Override
    public void printMainMenu() {
        System.out.println("=============================");
        System.out.println("====[kita emp manage app]====");
        System.out.println("=============================");
        System.out.println("1. add new emp");
        System.out.println("2. find emp");
        System.out.println("3. delete emp");
        System.out.println("4. show all emp");
        System.out.println("9. exit");
        System.out.println("=============================");
        System.out.println("select the menu : ");
    }

    @Override
    public void insertEmployee() {
        System.out.println("=============================");
        System.out.println("=====[add new employee]=====");
        System.out.println("=============================");
        System.out.println("1. empID : " + (Employee.serial + 1));
        System.out.println("2. emp name : ");
        String name = scanner.nextLine();

        System.out.println("3. emp salary : ");
        int salary = scanner.nextInt();

        List<String> license = new ArrayList<String >();
        while (true){
            System.out.println("4.emp license : ");
            Scanner scnForLicense = new Scanner(System.in);
            String temp = scnForLicense.nextLine();

            if(temp.length() == 0){
                break;
            }//if
            license.add(temp);
        }//while
        Employee employee = new Employee(name, salary, license);
        manager.insertEmployee(employee);
    }

    @Override
    public void findEmployee() {
        System.out.println("input eid want to find : ");
        String eid = scanner.nextLine();
        Employee emp = manager.findEmployee(eid);

        if(emp != null){
            System.out.println(emp.toString());
        }
        else{
            System.out.println("there is no emp who you find.");
        }
    }

    @Override
    public void deleteEmployee() {
        System.out.println("input eid want to delete : ");
        String eid = scanner.nextLine();
        Employee emp = manager.findEmployee(eid);

        if(emp == null){
            System.out.println("there is no emp who you find.");
        }
        else{
            System.out.println("are you sure delete this emp?(Y/N)");
            String asnwer = scanner.nextLine();
            if(asnwer.equalsIgnoreCase("y")){
                manager.deleteEmployee(eid);
            }
            else{
                System.out.println("delete emp process canceled.");
            }
        }

    }
}
