
import java.lang.reflect.Method;

public class DumpMethod {
    public static void main(String[] args) {

        // this class find all interfaces that the target class have

        try {
            Class<?>[] ilist = getInterface(Class.forName("TestClass"));
            // This is using args
            // Class<?>[] ilist = getInterface(Class.forName(args[0]));
            for (Class<?> iface : ilist) {
                Method[] methods = iface.getMethods();
                for (Method method : methods) {
                    System.out.println(method.getName());
                }
            }
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    public static Class<?>[] getInterface(Class<?> cls) {
        System.out.println("Class name : " + cls);
        System.out.println("the interface he got is : " + cls.getInterfaces().length);
        return cls.getInterfaces();
    }
}
