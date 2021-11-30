
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DumpMethod {

    public static void main(String[] args) {

        // this class find all interfaces and superclass that the target class have

        try {
            Class<?> cls = Class.forName("TestClass");
            Class<?>[] ilist = getInterface(cls);
            System.out.println(cls.getSuperclass().getPermittedSubclasses().getClass().getName());
            // This is using args
            // Class<?>[] ilist = getInterface(Class.forName(args[0]));

            for (Class<?> iface : ilist) {
                Method[] methods = iface.getMethods();
                Field[] fields = iface.getDeclaredFields();
                System.out.println("With method " + methods.length + " and field " + fields.length);
                for (Method method : methods) {
                    System.out.println("with method" + method.getName());
                }
                for (var field : fields) {
                    System.out.println("with fields " + field.getName());
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

    static boolean isSubClass(Class<?> cls) {
        if (cls.getSuperclass().equals(Object.class)) {
            return false;
        }
        return true;
    }

    static boolean isSuperClass(Class<?> cls) {
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
