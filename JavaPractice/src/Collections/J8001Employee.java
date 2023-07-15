package Collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class J8001Employee {
    int EmpId;
    String EmpName;

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int empId) {
        EmpId = empId;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public J8001Employee(int empId, String empName) {
        EmpId = empId;
        EmpName = empName;
    }

    public static void main(String[] args) {
        List<J8001Employee> list = new ArrayList<J8001Employee>();
        J8001Employee j8001Employee1 = new J8001Employee(101,"Raunak");
        J8001Employee j8001Employee2 = new J8001Employee(102,"Mahi");
        list.add(j8001Employee1);
        list.add(j8001Employee2);

        for(J8001Employee employee:list)
            System.out.println(employee.getEmpName());

        Iterator<J8001Employee> iterator = list.iterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next().getEmpName());
        }
    }
}
