import java.lang.reflect.Method;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.JavaClass;

public class injection {
    
    public static void main(String[] args) {
        
        try {
            JavaClass cls = Repository.lookupClass("Es");
            printCode(cls.getMethods());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }
    private static void printCode(org.apache.bcel.classfile.Method[] methods) {
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i]);
            
            Code code = methods[i].getCode();
            if (code != null) // Non-abstract method
            System.out.println(code);
        }
    }
    
}
