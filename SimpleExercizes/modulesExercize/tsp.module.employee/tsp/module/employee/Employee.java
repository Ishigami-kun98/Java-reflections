import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;

public class Employee implements SmartFieldAccess{
    private final Lookup lookup;
    public Lookup getEmployeLookup(){ return this.lookup; }
    private String name; private String surname;
    public Employee(String n, String s){
        lookup = MethodHandles.lookup(); name = n; surname = s;
    }
}
