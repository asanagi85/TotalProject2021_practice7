package service;

import vo.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager implements Manager{
    BufferedReader br;
    BufferedWriter bw;
    FileReader fr;
    FileWriter fw;
    File file;
    List<Employee> eList;

    public EmployeeManager() {
        file = new File("D:\\test text\\employee7.txt");

        if(file.exists()){
            eList = new ArrayList<>();
            getFile();
        }
        else{
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void getFile() {
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String temp;
            while (true) {
                temp = br.readLine();

                if (temp == null) {
                    break;
                }

                String[] data = temp.split(",");
                Employee emp = new Employee();
                List<String> license = new ArrayList<String>();

                emp.serial = Integer.parseInt(data[0]);
                emp.setEid(data[0]);
                emp.setName(data[1]);
                emp.setSalary(Integer.parseInt(data[2]));

                for(int i = 3; i < data.length; ++i){
                    license.add(data[i]);
                }

                emp.setLicense(license);
                //일단 라이센스가 null 일경우에는 어떻게 되는지 테스트필요있음
                eList.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (fr != null) {
                    fr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void saveFile() {
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            for(Employee e : eList){
                bw.write(e.toString() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean insertEmployee(Employee employee) {
        return eList.add(employee);
    }

    @Override
    public Employee findEmployee(String eid) {
        for(Employee e : eList){
            if(e.getEid().equals(eid)){
                return  e;
            }
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(String eid) {
        Employee resultEmp = findEmployee(eid);

        return eList.remove(resultEmp);
    }

    @Override
    public void showAll() {
        for(Employee e : eList){
            System.out.println(e.toString());
        }
    }
}
