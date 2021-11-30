
public class ReflectiveEmployeeMain {
    public static void main(String[] args) {
        Employee a = new Employee("Davide", "Ielmini");
        System.out.println(a);
        a.instVarAtPut(a.getEmployeeLookup(), "surname", "é gia andato");
        System.out.println(a);
    }
}
