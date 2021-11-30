
import java.lang.reflect.Method;

public class DumpMethod {

    public static void main(String[] args) {

        // this class find all interfaces and superclass that the target class have

        try {
            Class<?> cls = Class.forName("TestClass");
            Class<?>[] ilist = getInterface(cls);
            Class<?>[] scls = cls.getClasses();

            for (Class<?> sclsp : scls) {
                if (!sclsp.isInterface()) {
                    // we are in class
                    System.out.println("Class " + sclsp.getName());
                    if (isSuperClass(sclsp)) {
                        // is a super class
                        System.out.println("Is a super class")
                    }
                }
            }
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

    static boolean isSuperClass(Class<?> cls) {
        if (cls.getSuperclass().equals(Object.class)) {
            return false;
        }
        return true;
    }

    /*
     * static Class<?> getSuperClasses(Class<?> cls) {
     * if (cls.equals(Object.class)) {
     * System.out.println("finish");
     * return cls;
     * }
     * System.out.println(
     * "The className is " + cls.getName() + " \n it's super class is " +
     * cls.getSuperclass().getName());
     * return getSuperClasses(cls.getSuperclass());
     * }
     */
}
